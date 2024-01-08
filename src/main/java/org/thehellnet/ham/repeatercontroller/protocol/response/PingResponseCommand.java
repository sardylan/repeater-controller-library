package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class PingResponseCommand extends AbstractResponseCommand {

    public PingResponseCommand() {
        super(CommandType.Ping);
    }

    @Override
    public void parseArgs(byte[] args) {
    }

    @Override
    public String toString() {
        return "PingResponseCommand";
    }
}
