package org.thehellnet.ham.repeatercontroller.protocol;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandTypeTest {

    @Test
    void serialize() {
        assertEquals((byte) 0x70, CommandType.Ping.serialize());
        assertEquals((byte) 0x58, CommandType.Reset.serialize());
        assertEquals((byte) 0x74, CommandType.Telemetry.serialize());
        assertEquals((byte) 0x72, CommandType.RTCRead.serialize());
        assertEquals((byte) 0x52, CommandType.RTCSet.serialize());
        assertEquals((byte) 0x63, CommandType.ConfigRead.serialize());
        assertEquals((byte) 0x43, CommandType.ConfigSet.serialize());
        assertEquals((byte) 0x6f, CommandType.OutputRead.serialize());
        assertEquals((byte) 0x4f, CommandType.OutputSet.serialize());
    }
}