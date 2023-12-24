package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

public class ConfigSetResponseCommand extends ConfigReadResponseCommand {

    public ConfigSetResponseCommand(ResponseType responseType) {
        super(CommandType.ConfigSet, responseType);
    }
}
