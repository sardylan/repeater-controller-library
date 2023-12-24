package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ConfigReadRequestCommandTest {

    @Test
    void serializeArgs() {
        ConfigReadRequestCommand command = new ConfigReadRequestCommand();

        byte[] expected = new byte[2];
        expected[0] = CommandType.ConfigRead.serialize();

        for (ConfigParam configParam : ConfigParam.values()) {
            command.setConfigParam(configParam);
            expected[1] = configParam.serialize();

            byte[] actual = CommandFactory.serializeRequest(command);
            assertArrayEquals(expected, actual);
        }
    }
}