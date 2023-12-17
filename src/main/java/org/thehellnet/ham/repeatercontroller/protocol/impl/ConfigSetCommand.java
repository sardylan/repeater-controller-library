package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigCommand;

public class ConfigSetCommand extends ConfigCommand {

    public ConfigSetCommand(CommandType commandType) {
        super(commandType, CommandByte.ConfigSet);
    }

}
