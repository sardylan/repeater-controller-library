package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

public class MeteoRequestCommand extends AbstractRequestCommand {

    public MeteoRequestCommand() {
        super(CommandType.Meteo);
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[0];
    }
}
