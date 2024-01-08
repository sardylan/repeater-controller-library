package org.thehellnet.ham.repeatercontroller.protocol.enums;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum StatusTemperature {
    Normal((byte) 0x00),
    OverTemp((byte) 0x01),
    LowTemp((byte) 0x02);

    private final byte b;

    StatusTemperature(byte b) {
        this.b = b;
    }

    public static StatusTemperature parse(byte b) {
        for (StatusTemperature temperature : StatusTemperature.values()) {
            if (temperature.serialize() == b) {
                return temperature;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    public byte serialize() {
        return b;
    }
}
