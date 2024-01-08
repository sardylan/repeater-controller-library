package org.thehellnet.ham.repeatercontroller.protocol.enums;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum StatusBattery {
    Normal((byte) 0x00),
    OverVoltage((byte) 0x01),
    UnderVoltage((byte) 0x02),
    OverDischarge((byte) 0x03),
    Fault((byte) 0x04);

    private final byte b;

    StatusBattery(byte b) {
        this.b = b;
    }

    public static StatusBattery parse(byte b) {
        for (StatusBattery battery : StatusBattery.values()) {
            if (battery.serialize() == b) {
                return battery;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    public byte serialize() {
        return b;
    }
}
