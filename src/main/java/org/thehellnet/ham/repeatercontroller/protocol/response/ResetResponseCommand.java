package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class ResetResponseCommand extends AbstractResponseCommand {

    public ResetResponseCommand() {
        super(CommandType.Reset);
    }

    @Override
    public void parseArgs(byte[] args) {
    }

    @Override
    public String toString() {
        return "ResetResponseCommand";
    }
}
