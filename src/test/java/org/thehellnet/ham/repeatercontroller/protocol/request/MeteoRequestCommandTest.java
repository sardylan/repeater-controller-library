package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MeteoRequestCommandTest {

    @Test
    void serializeArgs() {
        MeteoRequestCommand command = new MeteoRequestCommand();

        byte[] expected = new byte[1];
        expected[0] = CommandType.Meteo.serialize();

        byte[] actual = CommandFactory.serializeRequest(command);
        assertArrayEquals(expected, actual);
    }
}