package org.thehellnet.ham.repeatercontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thehellnet.ham.repeatercontroller.exception.SocketClientException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.enums.ConfigParam;
import org.thehellnet.ham.repeatercontroller.protocol.request.*;
import org.thehellnet.ham.repeatercontroller.protocol.response.*;

import java.io.IOException;
import java.net.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class RepeaterControllerClient {

    private static final Logger logger = LoggerFactory.getLogger(RepeaterControllerClient.class);

    private final Object SYNC = new Object();

    private InetAddress serverAddress = InetAddress.getLoopbackAddress();
    private int serverPort = 8888;

    public InetAddress getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(InetAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void setServerAddress(String serverAddress) throws UnknownHostException {
        this.serverAddress = InetAddress.getByName(serverAddress);
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public PingResponseCommand ping() {
        PingRequestCommand request = new PingRequestCommand();
        return (PingResponseCommand) sendCommand(request);
    }

    public ResetResponseCommand reset() {
        ResetRequestCommand request = new ResetRequestCommand();
        return (ResetResponseCommand) sendCommand(request);
    }

    public TelemetryResponseCommand telemetry() {
        TelemetryRequestCommand request = new TelemetryRequestCommand();
        return (TelemetryResponseCommand) sendCommand(request);
    }

    public RTCReadResponseCommand rtcRead() {
        RTCReadRequestCommand request = new RTCReadRequestCommand();
        return (RTCReadResponseCommand) sendCommand(request);
    }

    public RTCSetResponseCommand rtcSet() {
        RTCSetRequestCommand request = new RTCSetRequestCommand();
        request.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime());
        return (RTCSetResponseCommand) sendCommand(request);
    }

    public ConfigReadResponseCommand configRead(ConfigParam configParam) {
        ConfigReadRequestCommand request = new ConfigReadRequestCommand();
        request.setConfigParam(configParam);
        return (ConfigReadResponseCommand) sendCommand(request);
    }

    public ConfigSetResponseCommand configSet(ConfigParam configParam, Object value) {
        ConfigSetRequestCommand request = new ConfigSetRequestCommand();
        request.setConfigParam(configParam);
        request.setValue(value);
        return (ConfigSetResponseCommand) sendCommand(request);
    }

    public OutputReadResponseCommand outputRead(int outputNumber) {
        OutputReadRequestCommand request = new OutputReadRequestCommand();
        request.setOutputNumber(outputNumber);
        return (OutputReadResponseCommand) sendCommand(request);
    }

    public OutputSetResponseCommand outputSet(int outputNumber, boolean status) {
        OutputSetRequestCommand request = new OutputSetRequestCommand();
        request.setOutputNumber(outputNumber);
        request.setStatus(status);
        return (OutputSetResponseCommand) sendCommand(request);
    }

    private ResponseCommand sendCommand(RequestCommand requestCommand) {
        logger.info("Sending request");

        try (DatagramSocket socket = new DatagramSocket()) {
            logger.debug("Serializing request");
            byte[] requestPayload = CommandFactory.serializeRequest(requestCommand);

            logger.debug("Creating request datagram");
            DatagramPacket requestPacket = new DatagramPacket(requestPayload, requestPayload.length, serverAddress, serverPort);

            logger.debug("Sending request datagram");
            try {
                socket.send(requestPacket);
            } catch (IOException e) {
                logger.error("Unable to send datagram: {}", e.getMessage());
                throw new SocketClientException(e);
            }

            logger.debug("Creating response datagram");
            byte[] buffer = new byte[128];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);

            logger.debug("Receiving response datagram");
            try {
                socket.receive(responsePacket);
            } catch (IOException e) {
                logger.error("Error receiving packet: {}", e.getMessage());
                throw new SocketClientException(e);
            }

            logger.debug("Checking response address and port");
            if (!serverAddress.equals(responsePacket.getAddress()) || serverPort != responsePacket.getPort()) {
                logger.error("Packet received from {}:{} instead of {}:{}", responsePacket.getAddress().getHostAddress(), responsePacket.getPort(), serverAddress.getHostAddress(), serverPort);
                throw new SocketClientException();
            }

            logger.debug("Getting response payload");
            byte[] responsePayload = Arrays.copyOfRange(responsePacket.getData(), 0, responsePacket.getLength());

            logger.debug("Response: {}", responsePayload);

            logger.debug("Parsing response payload");
            return CommandFactory.parseResponse(responsePayload);
        } catch (SocketException e) {
            logger.error("Unable to create socket: {}", e.getMessage());
            throw new SocketClientException(e);
        }
    }
}
