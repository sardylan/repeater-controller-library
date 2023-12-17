package org.thehellnet.ham.repeatercontroller.protocol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigParamTest {

    @Test
    void serialize() {
        assertEquals((byte) 0x6f, ConfigParam.MainVoltageOff.serialize());
        assertEquals((byte) 0x4f, ConfigParam.MainVoltageOn.serialize());
    }

    @Test
    void parse() {
        assertEquals(ConfigParam.MainVoltageOff, ConfigParam.parse((byte) 0x6f));
        assertEquals(ConfigParam.MainVoltageOn, ConfigParam.parse((byte) 0x4f));

        Assertions.assertThrows(ProtocolException.class, () -> ConfigParam.parse((byte) 0x01));
    }

}