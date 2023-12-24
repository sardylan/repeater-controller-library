package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RTCReadRequestCommandTest {

    @Test
    void serializeArgs() {
        RTCReadRequestCommand command = new RTCReadRequestCommand();

        byte[] expected = new byte[1];
        expected[0] = CommandType.RTCRead.serialize();

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }
}