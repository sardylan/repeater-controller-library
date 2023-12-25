package org.thehellnet.ham.repeatercontroller.protocol;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTypeTest {

    @Test
    void serialize() {
        assertEquals((byte) 0x41, ResponseType.Ack.serialize());
        assertEquals((byte) 0x4e, ResponseType.Nack.serialize());
    }

    @Test
    void parse() {
        assertEquals(ResponseType.Ack, ResponseType.parse((byte) 0x41));
        assertEquals(ResponseType.Nack, ResponseType.parse((byte) 0x4e));

        assertThrows(ProtocolException.class, () -> ResponseType.parse((byte) 0x01));
    }
}