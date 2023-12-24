package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class ResetRequestCommand extends AbstractRequestCommand {

    public ResetRequestCommand() {
        super(CommandType.Reset);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }
}
