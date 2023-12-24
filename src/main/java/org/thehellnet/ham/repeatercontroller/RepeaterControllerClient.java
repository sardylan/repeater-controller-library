package org.thehellnet.ham.repeatercontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thehellnet.ham.repeatercontroller.exception.SocketClientException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;
import org.thehellnet.ham.repeatercontroller.protocol.request.*;
import org.thehellnet.ham.repeatercontroller.protocol.response.*;
import org.thehellnet.ham.repeatercontroller.socket.SocketClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class RepeaterControllerClient {

    private static final Logger logger = LoggerFactory.getLogger(RepeaterControllerClient.class);

    private final Object SYNC = new Object();

    private InetAddress serverAddress = InetAddress.getLoopbackAddress();
    private int serverPort = 8888;

    private SocketClient socketClient = null;

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

    public void start() {
        synchronized (SYNC) {
            if (socketClient != null) {
                logger.warn("Already started");
                return;
            }

            socketClient = new SocketClient(serverAddress, serverPort);
            socketClient.start();
        }
    }

    public void stop() {
        synchronized (SYNC) {
            if (socketClient == null) {
                logger.warn("Already stopped");
                return;
            }

            socketClient.stop();
            socketClient = null;
        }
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
        byte[] requestPayload = CommandFactory.serializeRequest(requestCommand);

        byte[] responsePayload;
        synchronized (SYNC) {
            if (socketClient == null) {
                throw new SocketClientException("Client not started");
            }

            responsePayload = socketClient.send(requestPayload);
        }

        return CommandFactory.parseResponse(requestCommand.getCommandType(), responsePayload);
    }
}
