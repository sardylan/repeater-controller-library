package org.thehellnet.ham.repeatercontroller.oldprotocol.impl;

class ConfigReadCommandTest {

//    @Test
//    void serializeArgsMainVoltageOff() {
//        ConfigReadCommand input = new ConfigReadCommand(CommandType.Request);
//        input.setConfigParam(ConfigParam.MainVoltageOff);
//        byte[] expected = new byte[]{(byte) 0x6f};
//        byte[] actual = input.serializeArgs();
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void serializeArgsMainVoltageOn() {
//        ConfigReadCommand input = new ConfigReadCommand(CommandType.Request);
//        input.setConfigParam(ConfigParam.MainVoltageOn);
//        byte[] expected = new byte[]{(byte) 0x4f};
//        byte[] actual = input.serializeArgs();
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsMainVoltageOff() {
//        byte[] input = new byte[]{(byte) 0x6f, (byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00};
//        ConfigReadCommand expected = new ConfigReadCommand(CommandType.Response);
//        expected.setConfigParam(ConfigParam.MainVoltageOff);
//        expected.setValue(12.5f);
//
//        ConfigReadCommand actual = new ConfigReadCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsMainVoltageOn() {
//        byte[] input = new byte[]{(byte) 0x4f, (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a};
//        ConfigReadCommand expected = new ConfigReadCommand(CommandType.Response);
//        expected.setConfigParam(ConfigParam.MainVoltageOn);
//        expected.setValue(14.6f);
//
//        ConfigReadCommand actual = new ConfigReadCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
}