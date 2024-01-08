package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class OutputSetResponseCommand extends OutputReadResponseCommand {

    public OutputSetResponseCommand() {
        super(CommandType.OutputSet);
    }

    @Override
    public String toString() {
        return "OutputSetResponseCommand";
    }
}
