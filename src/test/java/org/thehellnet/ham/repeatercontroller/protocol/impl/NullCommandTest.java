package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NullCommandTest {

    @Test
    void serializeArgs() {
        NullCommand input = new NullCommand(CommandType.Request);
        byte[] expected = new byte[0];
        byte[] actual = input.serializeArgs();
        assertArrayEquals(expected, actual);
    }

    @Test
    void parseArgs() {
        byte[] input = new byte[]{};
        NullCommand expected = new NullCommand(CommandType.Response);

        NullCommand actual = new NullCommand(CommandType.Response);
        actual.parseArgs(input);

        assertEquals(expected, actual);
    }
}