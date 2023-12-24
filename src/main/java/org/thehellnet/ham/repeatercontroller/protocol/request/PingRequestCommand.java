package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class PingRequestCommand extends AbstractRequestCommand {

    public PingRequestCommand() {
        super(CommandType.Ping);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }
}
