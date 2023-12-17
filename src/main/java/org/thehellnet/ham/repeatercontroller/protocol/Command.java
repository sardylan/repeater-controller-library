package org.thehellnet.ham.repeatercontroller.protocol;

public interface Command {

    int OUTPUT_MAX_NUMBER = 8;

    byte[] serializeArgs();

    void parseArgs(byte[] args);

    CommandType getCommandType();

    CommandByte getCommandByte();
}
