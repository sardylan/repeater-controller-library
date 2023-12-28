package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class RTCSetResponseCommand extends RTCReadResponseCommand {

    public RTCSetResponseCommand() {
        super(CommandType.RTCSet);
    }
}
