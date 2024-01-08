package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class StatusRequestCommand extends AbstractRequestCommand {

    public StatusRequestCommand() {
        super(CommandType.Status);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }
}
