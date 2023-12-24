package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

public class ResetResponseCommand extends AbstractResponseCommand {

    public ResetResponseCommand(ResponseType responseType) {
        super(CommandType.Reset, responseType);
    }

    @Override
    public void parseArgs(byte[] args) {

    }
}
