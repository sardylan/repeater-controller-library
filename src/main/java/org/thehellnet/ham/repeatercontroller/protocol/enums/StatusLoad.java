package org.thehellnet.ham.repeatercontroller.protocol.enums;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum StatusLoad {
    Light((byte) 0x00),
    Moderate((byte) 0x01),
    Rated((byte) 0x02),
    Overload((byte) 0x03);

    private final byte b;

    StatusLoad(byte b) {
        this.b = b;
    }

    public static StatusLoad parse(byte b) {
        for (StatusLoad load : StatusLoad.values()) {
            if (load.serialize() == b) {
                return load;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    public byte serialize() {
        return b;
    }
}
