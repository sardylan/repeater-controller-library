package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public interface RequestCommand {

    CommandType getCommandType();

    byte[] serializeArgs();
}
