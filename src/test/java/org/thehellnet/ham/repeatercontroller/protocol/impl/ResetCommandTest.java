package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.*;

class ResetCommandTest {

    @Test
    void serializeArgs() {
        ResetCommand input = new ResetCommand(CommandType.Request);
        byte[] expected = new byte[0];
        byte[] actual = input.serializeArgs();
        assertArrayEquals(expected, actual);
    }

    @Test
    void parseArgs() {
        byte[] input = new byte[]{};
        ResetCommand expected = new ResetCommand(CommandType.Response);

        ResetCommand actual = new ResetCommand(CommandType.Response);
        actual.parseArgs(input);

        assertEquals(expected, actual);
    }
}