package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

public class OutputSetResponseCommand extends OutputReadResponseCommand {

    public OutputSetResponseCommand(ResponseType responseType) {
        super(CommandType.OutputSet, responseType);
    }
}
