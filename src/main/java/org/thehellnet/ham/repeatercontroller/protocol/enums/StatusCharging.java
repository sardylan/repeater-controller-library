package org.thehellnet.ham.repeatercontroller.protocol.enums;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum StatusCharging {
    NoCharging((byte) 0x00),
    Float((byte) 0x01),
    Boost((byte) 0x02),
    Equalization((byte) 0x03);

    private final byte b;

    StatusCharging(byte b) {
        this.b = b;
    }

    public static StatusCharging parse(byte b) {
        for (StatusCharging charging : StatusCharging.values()) {
            if (charging.serialize() == b) {
                return charging;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    public byte serialize() {
        return b;
    }
}
