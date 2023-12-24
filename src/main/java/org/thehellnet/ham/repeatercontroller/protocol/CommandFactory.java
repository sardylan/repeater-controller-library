package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.request.RequestCommand;
import org.thehellnet.ham.repeatercontroller.protocol.response.*;

import java.util.Arrays;

public class CommandFactory {

    public static byte[] serializeRequest(RequestCommand command) {
        byte[] args = command.serializeArgs();

        byte[] payload = new byte[args.length + 1];
        payload[0] = command.getCommandType().serialize();
        System.arraycopy(args, 0, payload, 1, args.length);
        return payload;
    }

    public static ResponseCommand parseResponse(CommandType commandType, byte[] payload) {
        ResponseType responseType = ResponseType.parse(payload[0]);

        ResponseCommand command;

        switch (commandType) {
            case Ping:
                command = new PingResponseCommand(responseType);
                break;
            case Reset:
                command = new ResetResponseCommand(responseType);
                break;
            case Telemetry:
                command = new TelemetryResponseCommand(responseType);
                break;
            case RTCRead:
                command = new RTCReadResponseCommand(responseType);
                break;
            case RTCSet:
                command = new RTCSetResponseCommand(responseType);
                break;
            case ConfigRead:
                command = new ConfigReadResponseCommand(responseType);
                break;
            case ConfigSet:
                command = new ConfigSetResponseCommand(responseType);
                break;
            case OutputRead:
                command = new OutputReadResponseCommand(responseType);
                break;
            case OutputSet:
                command = new OutputSetResponseCommand(responseType);
                break;

            default:
                throw new ProtocolException("Response not valid");
        }

        byte[] args = Arrays.copyOfRange(payload, 1, payload.length);
        command.parseArgs(args);

        return command;
    }
}
