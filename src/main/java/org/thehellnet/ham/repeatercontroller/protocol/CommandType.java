package org.thehellnet.ham.repeatercontroller.protocol;

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

    public byte serialize() {
        return b;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
