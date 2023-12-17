package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.TimestampCommand;

public class PingCommand extends TimestampCommand {

    public PingCommand(CommandType commandType) {
        super(commandType, CommandByte.Ping);
    }

    @Override
    public byte[] serializeArgs() {
        if (getCommandType() == CommandType.Request) {
            return new byte[0];
        }

        return super.serializeArgs();
    }

    @Override
    protected void parseCommandArgs(byte[] args) {
        if (getCommandType() == CommandType.Request) {
            return;
        }

        super.parseCommandArgs(args);
    }

    @Override
    protected int argsSize() {
        if (getCommandType() == CommandType.Request) {
            return 0;
        }

        return super.argsSize();
    }
}
