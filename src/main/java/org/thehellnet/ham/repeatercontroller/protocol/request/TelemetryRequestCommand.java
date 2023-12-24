package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class TelemetryRequestCommand extends AbstractRequestCommand {

    public TelemetryRequestCommand() {
        super(CommandType.Telemetry);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }
}
