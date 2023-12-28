package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum CommandType {
    Ping((byte) 'p'),
    Reset((byte) 'X'),
    Telemetry((byte) 't'),
    RTCRead((byte) 'r'),
    RTCSet((byte) 'R'),
    ConfigRead((byte) 'c'),
    ConfigSet((byte) 'C'),
    OutputRead((byte) 'o'),
    OutputSet((byte) 'O');

    private final byte b;

    CommandType(byte b) {
        this.b = b;
    }

    public static CommandType parse(byte b) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.serialize() == b) {
                return commandType;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    public byte serialize() {
        return b;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
