package org.thehellnet.ham.repeatercontroller.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thehellnet.ham.repeatercontroller.exception.SocketClientException;
import org.thehellnet.ham.repeatercontroller.utility.LoopThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketClient extends LoopThread {

    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private final Object SYNC = new Object();
    private final Object SYNC_SEND = new Object();

    private final BlockingQueue<byte[]> payloadQueue = new LinkedBlockingQueue<>();

    private final InetAddress serverAddress;
    private final int serverPort;

    private DatagramSocket socket;

    public SocketClient(InetAddress serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public synchronized void start() {
        synchronized (SYNC) {
            logger.info("Starting Socket");

            if (socket != null) {
                logger.warn("Socket already started");
                return;
            }

            logger.debug("Creating Socket");

            try {
                socket = new DatagramSocket();
            } catch (SocketException e) {
                logger.error("Unable to start socket: {}", e.getMessage());
                throw new SocketClientException(e);
            }

            super.start();
        }
    }

    @Override
    public synchronized void stop() {
        synchronized (SYNC) {
            logger.info("Stopping Socket");

            if (socket == null) {
                logger.warn("Socket already stopped");
                return;
            }

            socket.close();
            socket = null;

            super.stop();
        }
    }

    public byte[] send(byte[] payload) {
        synchronized (SYNC_SEND) {
            synchronized (SYNC) {
                if (socket == null) {
                    logger.warn("Socket not started");
                    throw new SocketClientException("Socket not started");
                }

                DatagramPacket packet = new DatagramPacket(payload, payload.length, serverAddress, serverPort);

                try {
                    socket.send(packet);
                } catch (IOException e) {
                    logger.error("Unable to send payload to socket: {}", e.getMessage());
                    throw new SocketClientException(e);
                }
            }

            try {
                return payloadQueue.take();
            } catch (InterruptedException e) {
                logger.error("Unable to take payload from queue: {}", e.getMessage());
                throw new SocketClientException(e);
            }
        }
    }

    @Override
    protected void job() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            synchronized (SYNC) {
                if (socket == null || socket.isClosed()) {
                    return;
                }
            }

            logger.error("Error receiving packet: {}", e.getMessage());
            throw new SocketClientException(e);
        }

        if (!serverAddress.equals(packet.getAddress()) || serverPort != packet.getPort()) {
            logger.warn("Packet received from {}:{} instead of {}:{}", packet.getAddress().getHostAddress(), packet.getPort(), serverAddress.getHostAddress(), serverPort);
            return;
        }

        byte[] payload = Arrays.copyOfRange(packet.getData(), 0, packet.getLength());

        try {
            payloadQueue.put(payload);
        } catch (InterruptedException e) {
            logger.error("Unable to put payload into queue: {}", e.getMessage());
            throw new SocketClientException(e);
        }
    }
}
