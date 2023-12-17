package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.protocol.AbstractCommand;
import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class ResetCommand extends AbstractCommand {

    public ResetCommand(CommandType commandType) {
        super(commandType, CommandByte.Reset);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }

    @Override
    protected void parseCommandArgs(byte[] args) {

    }

    @Override
    protected int argsSize() {
        return 0;
    }
}
