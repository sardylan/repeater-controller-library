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

    public static ResponseCommand parseResponse(byte[] payload) {
        CommandType commandType = CommandType.parse(payload[0]);
        ResponseCommand command;

        switch (commandType) {
            case Ping:
                command = new PingResponseCommand();
                break;
            case Reset:
                command = new ResetResponseCommand();
                break;
            case Telemetry:
                command = new TelemetryResponseCommand();
                break;
            case Meteo:
                command = new MeteoResponseCommand();
                break;
            case Status:
                command = new StatusResponseCommand();
                break;
            case RTCRead:
                command = new RTCReadResponseCommand();
                break;
            case RTCSet:
                command = new RTCSetResponseCommand();
                break;
            case ConfigRead:
                command = new ConfigReadResponseCommand();
                break;
            case ConfigSet:
                command = new ConfigSetResponseCommand();
                break;
            case OutputRead:
                command = new OutputReadResponseCommand();
                break;
            case OutputSet:
                command = new OutputSetResponseCommand();
                break;

            default:
                throw new ProtocolException("Response not valid");
        }

        byte[] args = Arrays.copyOfRange(payload, 1, payload.length);
        command.parseArgs(args);

        return command;
    }
}
