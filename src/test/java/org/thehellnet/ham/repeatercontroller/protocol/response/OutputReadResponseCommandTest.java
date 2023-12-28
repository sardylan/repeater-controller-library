package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutputReadResponseCommandTest {

    @Test
    void parseArgs() {
        byte[] input = new byte[]{(byte) 0x00, (byte) 0x00};

        OutputReadResponseCommand responseCommand = new OutputReadResponseCommand();
        responseCommand.parseArgs(input);

        assertEquals(0, responseCommand.getOutputNumber());
        assertFalse(responseCommand.isStatus());

        input = new byte[]{(byte) 0x00, (byte) 0x01};

        responseCommand.parseArgs(input);

        assertEquals(0, responseCommand.getOutputNumber());
        assertTrue(responseCommand.isStatus());
    }
}