package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.TimestampCommand;

public class RTCSetCommand extends TimestampCommand {

    public RTCSetCommand(CommandType commandType) {
        super(commandType, CommandByte.RTCSet);
    }
}
