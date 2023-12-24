package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

public class PingResponseCommand extends AbstractResponseCommand {

    public PingResponseCommand(ResponseType responseType) {
        super(CommandType.Ping, responseType);
    }

    @Override
    public void parseArgs(byte[] args) {

    }
}
