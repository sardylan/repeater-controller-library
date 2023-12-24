package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

public class RTCSetResponseCommand extends RTCReadResponseCommand {

    public RTCSetResponseCommand(ResponseType responseType) {
        super(CommandType.RTCSet, responseType);
    }
}
