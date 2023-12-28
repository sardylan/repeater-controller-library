package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PingResponseCommandTest {

    @Test
    void parseArgs() {
        byte[] input = new byte[]{(byte) 0x65, (byte) 0x7d, (byte) 0xd3, (byte) 0x29};

        PingResponseCommand responseCommand = new PingResponseCommand();
        responseCommand.parseArgs(input);

        assertEquals(LocalDateTime.of(2023, 12, 16, 16, 41, 13, 0), responseCommand.getTimestamp());
    }
}