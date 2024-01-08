package org.thehellnet.ham.repeatercontroller.protocol.enums;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum ConfigParam {

    MainVoltageOff((byte) 'o'),
    MainVoltageOn((byte) 'O');

    private final byte b;

    ConfigParam(byte b) {
        this.b = b;
    }

    public byte serialize() {
        return b;
    }

    public static ConfigParam parse(byte b) {
        for (ConfigParam configParam : ConfigParam.values()) {
            if (configParam.serialize() == b) {
                return configParam;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
