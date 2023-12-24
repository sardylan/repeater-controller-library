package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.time.LocalDateTime;

public class RTCReadRequestCommand extends AbstractRequestCommand {

    public RTCReadRequestCommand() {
        super(CommandType.RTCRead);
    }

    protected RTCReadRequestCommand(CommandType commandType) {
        super(commandType);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }
}
