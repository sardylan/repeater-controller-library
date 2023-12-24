package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OutputSetRequestCommandTest {

    @Test
    void serializeArgs() {
        OutputSetRequestCommand command = new OutputSetRequestCommand();

        byte[] expected = new byte[3];
        expected[0] = CommandType.OutputSet.serialize();

        command.setOutputNumber(0);
        // expected[1] = (byte) 0x00;

        command.setStatus(true);
        expected[2] = (byte) 0x01;

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);

        command.setStatus(false);
        expected[2] = (byte) 0x00;

        actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeArgsInvalidOutput() {
        OutputSetRequestCommand command = new OutputSetRequestCommand();
        assertThrows(ProtocolException.class, () -> command.setOutputNumber(-1));
    }
}