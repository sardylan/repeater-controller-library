package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

import static org.junit.jupiter.api.Assertions.*;

class ConfigSetResponseCommandTest {

    @Test
    void parseArgs() {
        byte[] input = new byte[]{(byte) 0x00, (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a};

        for (ConfigParam configParam : ConfigParam.values()) {
            input[0] = configParam.serialize();

            ConfigSetResponseCommand responseCommand = new ConfigSetResponseCommand(ResponseType.Ack);
            responseCommand.parseArgs(input);

            assertEquals(configParam, responseCommand.getConfigParam());
            assertEquals(14.6f, responseCommand.getValueFloat());
        }
    }
}