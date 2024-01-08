package org.thehellnet.ham.repeatercontroller.protocol.enums;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum StatusArrays {
    Normal((byte) 0x00),
    NoInputPower((byte) 0x01),
    HigherVoltage((byte) 0x02),
    VoltageError((byte) 0x03);

    private final byte b;

    StatusArrays(byte b) {
        this.b = b;
    }

    public static StatusArrays parse(byte b) {
        for (StatusArrays arrays : StatusArrays.values()) {
            if (arrays.serialize() == b) {
                return arrays;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    public byte serialize() {
        return b;
    }
}
