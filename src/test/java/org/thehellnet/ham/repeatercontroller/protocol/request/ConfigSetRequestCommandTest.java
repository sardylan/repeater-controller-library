package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandFactory;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;

import static org.junit.jupiter.api.Assertions.*;

class ConfigSetRequestCommandTest {

    @Test
    void serializeArgs() {
        ConfigSetRequestCommand command = new ConfigSetRequestCommand();

        byte[] expected = new byte[6];
        expected[0] = CommandType.ConfigSet.serialize();

        for (ConfigParam configParam : ConfigParam.values()) {
            command.setConfigParam(configParam);
            expected[1] = configParam.serialize();

            command.setValue(14.6f);
            System.arraycopy(new byte[]{(byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a}, 0, expected, 2, 4);

            byte[] actual = CommandFactory.serializeRequest(command);
            assertArrayEquals(expected, actual);
        }
    }
}