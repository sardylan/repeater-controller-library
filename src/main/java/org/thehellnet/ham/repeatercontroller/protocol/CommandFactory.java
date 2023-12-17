package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.impl.*;

import java.util.Arrays;

public class CommandFactory {

    public static byte[] serializePayload(Command command) {
        if (command.getCommandType() != CommandType.Request) {
            throw new ProtocolException("Not a request command");
        }

        byte[] args = command.serializeArgs();
        byte cmd = command.getCommandByte().serialize();

        byte[] payload = new byte[args.length + 1];
        payload[0] = cmd;
        System.arraycopy(args, 0, payload, 1, args.length);

        return payload;
    }

    public static Command parsePayload(byte[] payload) {
        byte firstByte = payload[0];
        byte[] args = Arrays.copyOfRange(payload, 1, payload.length);

        CommandByte commandByte = CommandByte.parse(firstByte);
        Command command = getCommand(commandByte);
        command.parseArgs(args);
        return command;
    }

    static Command getCommand(CommandByte commandByte) {
        switch (commandByte) {
            case Null:
                return new NullCommand(CommandType.Response);
            case Ping:
                return new PingCommand(CommandType.Response);
            case Reset:
                return new ResetCommand(CommandType.Response);
            case Telemetry:
                return new TelemetryCommand(CommandType.Response);
            case RTCRead:
                return new RTCReadCommand(CommandType.Response);
            case RTCSet:
                return new RTCSetCommand(CommandType.Response);
            case ConfigRead:
                return new ConfigReadCommand(CommandType.Response);
            case ConfigSet:
                return new ConfigSetCommand(CommandType.Response);
            case OutputRead:
                return new OutputReadCommand(CommandType.Response);
            case OutputSet:
                return new OutputSetCommand(CommandType.Response);
        }

        throw new ProtocolException("Not implemented");
    }
}
