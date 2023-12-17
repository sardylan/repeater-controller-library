package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigCommand;

public class ConfigReadCommand extends ConfigCommand {

    public ConfigReadCommand(CommandType commandType) {
        super(commandType, CommandByte.ConfigRead);
    }

    @Override
    public byte[] serializeArgs() {
        if (getCommandType() == CommandType.Request) {
            return new byte[]{configParam.serialize()};
        }

        return super.serializeArgs();
    }

    @Override
    protected void parseCommandArgs(byte[] args) {
        parseConfigParam(args);
        if (getCommandType() == CommandType.Request) {
            return;
        }

        super.parseCommandArgs(args);
    }

    @Override
    protected int argsSize() {
        if (getCommandType() == CommandType.Request) {
            return 1;
        }

        return super.argsSize();
    }
}
