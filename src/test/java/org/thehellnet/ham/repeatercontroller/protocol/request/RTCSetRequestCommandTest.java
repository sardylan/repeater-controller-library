package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RTCSetRequestCommandTest {

    @Test
    void serializeArgs() {
        RTCSetRequestCommand command = new RTCSetRequestCommand();

        byte[] expected = new byte[5];
        expected[0] = CommandType.RTCSet.serialize();

        command.setTimestamp(LocalDateTime.of(1970,1,1,0,0,0,0));

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }
}