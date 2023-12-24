package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;

import static org.junit.jupiter.api.Assertions.*;

class PingRequestCommandTest {

    @Test
    void serializeArgs() {
        PingRequestCommand command = new PingRequestCommand();

        byte[] expected = new byte[1];
        expected[0] = CommandType.Ping.serialize();

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }
}