package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum CommandByte {
    Null((byte) 0),
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

    CommandByte(byte b) {
        this.b = b;
    }

    public byte serialize() {
        return b;
    }

    public static CommandByte parse(byte b) {
        for (CommandByte commandByte : CommandByte.values()) {
            if (commandByte.serialize() == b) {
                return commandByte;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
