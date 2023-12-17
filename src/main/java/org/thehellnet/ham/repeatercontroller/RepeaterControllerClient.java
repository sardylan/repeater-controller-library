package org.thehellnet.ham.repeatercontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.exception.SocketClientException;
import org.thehellnet.ham.repeatercontroller.protocol.Command;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;
import org.thehellnet.ham.repeatercontroller.protocol.impl.*;
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

    public PingCommand ping() {
        PingCommand request = new PingCommand(CommandType.Request);
        return (PingCommand) sendCommand(request);
    }

    public ResetCommand reset() {
        ResetCommand request = new ResetCommand(CommandType.Request);
        return (ResetCommand) sendCommand(request);
    }

    public TelemetryCommand telemetry() {
        TelemetryCommand request = new TelemetryCommand(CommandType.Request);
        return (TelemetryCommand) sendCommand(request);
    }

    public RTCReadCommand rtcRead() {
        RTCReadCommand request = new RTCReadCommand(CommandType.Request);
        return (RTCReadCommand) sendCommand(request);
    }

    public RTCSetCommand rtcSet() {
        RTCSetCommand request = new RTCSetCommand(CommandType.Request);
        request.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        return (RTCSetCommand) sendCommand(request);
    }

    public ConfigReadCommand configRead(ConfigParam configParam) {
        ConfigReadCommand request = new ConfigReadCommand(CommandType.Request);
        request.setConfigParam(configParam);
        return (ConfigReadCommand) sendCommand(request);
    }

    public ConfigSetCommand configSet(ConfigParam configParam, Object value) {
        ConfigSetCommand request = new ConfigSetCommand(CommandType.Request);
        request.setConfigParam(configParam);
        request.setValue(value);
        return (ConfigSetCommand) sendCommand(request);
    }

    public OutputReadCommand outputRead(int outputNumber) {
        OutputReadCommand request = new OutputReadCommand(CommandType.Request);
        request.setOutputNumber(outputNumber);
        return (OutputReadCommand) sendCommand(request);
    }

    public OutputSetCommand outputSet(int outputNumber, boolean status) {
        OutputSetCommand request = new OutputSetCommand(CommandType.Request);
        request.setOutputNumber(outputNumber);
        request.setStatus(status);
        return (OutputSetCommand) sendCommand(request);
    }

    private Command sendCommand(Command requestCommand) {
        byte[] requestPayload = CommandFactory.serializePayload(requestCommand);

        byte[] responsePayload;
        synchronized (SYNC) {
            if (socketClient == null) {
                throw new SocketClientException("Client not started");
            }

            responsePayload = socketClient.send(requestPayload);
        }

        Command responseCommand = CommandFactory.parsePayload(responsePayload);
        if (responseCommand.getCommandByte() != requestCommand.getCommandByte()) {
            throw new ProtocolException("Invalid response command");
        }

        if (responseCommand.getCommandType() != CommandType.Response) {
            throw new ProtocolException("Response is not a response command");
        }

        return responseCommand;
    }
}
