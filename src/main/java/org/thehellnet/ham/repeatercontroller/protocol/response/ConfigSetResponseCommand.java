package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class ConfigSetResponseCommand extends ConfigReadResponseCommand {

    public ConfigSetResponseCommand() {
        super(CommandType.ConfigSet);
    }
}
