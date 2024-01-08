package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StatusRequestCommandTest {

    @Test
    void serializeArgs() {
        StatusRequestCommand command = new StatusRequestCommand();

        byte[] expected = new byte[1];
        expected[0] = CommandType.Status.serialize();

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }
}
