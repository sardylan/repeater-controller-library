package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OutputReadRequestCommandTest {

    @Test
    void serializeArgs() {
        OutputReadRequestCommand command = new OutputReadRequestCommand();

        byte[] expected = new byte[2];
        expected[0] = CommandType.OutputRead.serialize();

        command.setOutputNumber(0);
        // expected[1] = (byte) 0x00;

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeArgsInvalidOutput() {
        OutputReadRequestCommand command = new OutputReadRequestCommand();
        assertThrows(ProtocolException.class, () -> command.setOutputNumber(-1));
    }
}