package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

public enum ResponseType {
    Ack((byte) 'A'),
    Nack((byte) 'N');

    private final byte b;

    ResponseType(byte b) {
        this.b = b;
    }

    public byte serialize() {
        return b;
    }

    public static ResponseType parse(byte b) {
        for (ResponseType responseType : ResponseType.values()) {
            if (responseType.serialize() == b) {
                return responseType;
            }
        }

        throw new ProtocolException(String.format("Byte 0x%02x not recognized", b));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
