package org.thehellnet.ham.repeatercontroller.protocol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CommandByteTest {

    @Test
    void serialize() {
        assertEquals((byte) 0x00, CommandByte.Null.serialize());
        assertEquals((byte) 0x70, CommandByte.Ping.serialize());
        assertEquals((byte) 0x58, CommandByte.Reset.serialize());
        assertEquals((byte) 0x74, CommandByte.Telemetry.serialize());
        assertEquals((byte) 0x72, CommandByte.RTCRead.serialize());
        assertEquals((byte) 0x52, CommandByte.RTCSet.serialize());
        assertEquals((byte) 0x63, CommandByte.ConfigRead.serialize());
        assertEquals((byte) 0x43, CommandByte.ConfigSet.serialize());
        assertEquals((byte) 0x6f, CommandByte.OutputRead.serialize());
        assertEquals((byte) 0x4f, CommandByte.OutputSet.serialize());
    }

    @Test
    void parse() {
        assertEquals(CommandByte.Null, CommandByte.parse((byte) 0x00));
        assertEquals(CommandByte.Ping, CommandByte.parse((byte) 0x70));
        assertEquals(CommandByte.Reset, CommandByte.parse((byte) 0x58));
        assertEquals(CommandByte.Telemetry, CommandByte.parse((byte) 0x74));
        assertEquals(CommandByte.RTCRead, CommandByte.parse((byte) 0x72));
        assertEquals(CommandByte.RTCSet, CommandByte.parse((byte) 0x52));
        assertEquals(CommandByte.ConfigRead, CommandByte.parse((byte) 0x63));
        assertEquals(CommandByte.ConfigSet, CommandByte.parse((byte) 0x43));
        assertEquals(CommandByte.OutputRead, CommandByte.parse((byte) 0x6f));
        assertEquals(CommandByte.OutputSet, CommandByte.parse((byte) 0x4f));

        Assertions.assertThrows(ProtocolException.class, () -> CommandByte.parse((byte) 0x01));
    }
}