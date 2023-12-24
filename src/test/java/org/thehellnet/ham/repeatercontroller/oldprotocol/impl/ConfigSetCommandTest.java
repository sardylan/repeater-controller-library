package org.thehellnet.ham.repeatercontroller.oldprotocol.impl;

class ConfigSetCommandTest {

//    @Test
//    void serializeArgsMainVoltageOff() {
//        ConfigSetCommand input = new ConfigSetCommand(CommandType.Request);
//        input.setConfigParam(ConfigParam.MainVoltageOff);
//        input.setValue(12.5f);
//        byte[] expected = new byte[]{(byte) 0x6f, (byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00};
//
//        byte[] actual = input.serializeArgs();
//
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void serializeArgsMainVoltageOn() {
//        ConfigSetCommand input = new ConfigSetCommand(CommandType.Request);
//        input.setConfigParam(ConfigParam.MainVoltageOn);
//        input.setValue(14.6f);
//        byte[] expected = new byte[]{(byte) 0x4f, (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a};
//
//        byte[] actual = input.serializeArgs();
//
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsMainVoltageOff() {
//        byte[] input = new byte[]{(byte) 0x6f, (byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00};
//        ConfigSetCommand expected = new ConfigSetCommand(CommandType.Response);
//        expected.setConfigParam(ConfigParam.MainVoltageOff);
//        expected.setValue(12.5f);
//
//        ConfigSetCommand actual = new ConfigSetCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsMainVoltageOn() {
//        byte[] input = new byte[]{(byte) 0x4f, (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a};
//        ConfigSetCommand expected = new ConfigSetCommand(CommandType.Response);
//        expected.setConfigParam(ConfigParam.MainVoltageOn);
//        expected.setValue(14.6f);
//
//        ConfigSetCommand actual = new ConfigSetCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
}