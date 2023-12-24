package org.thehellnet.ham.repeatercontroller.oldprotocol.impl;

class OutputSetCommandTest {

//    @Test
//    void setOutputNumber() {
//        OutputSetCommand input = new OutputSetCommand(CommandType.Request);
//
//        assertThrows(ProtocolException.class, () -> input.setOutputNumber(0));
//        assertThrows(ProtocolException.class, () -> input.setOutputNumber(9));
//    }
//
//    @Test
//    void serializeArgs() {
//        OutputSetCommand input = new OutputSetCommand(CommandType.Request);
//        input.setOutputNumber(1);
//        input.setStatus(true);
//        byte[] expected = new byte[]{0x01, 0x01};
//        byte[] actual = input.serializeArgs();
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsActive() {
//        byte[] input = new byte[]{(byte) 0x03, (byte) 0x01};
//        OutputSetCommand expected = new OutputSetCommand(CommandType.Response);
//        expected.setOutputNumber(3);
//        expected.setStatus(true);
//
//        OutputSetCommand actual = new OutputSetCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsDisactive() {
//        byte[] input = new byte[]{(byte) 0x04, (byte) 0x00};
//        OutputSetCommand expected = new OutputSetCommand(CommandType.Response);
//        expected.setOutputNumber(4);
//        expected.setStatus(false);
//
//        OutputSetCommand actual = new OutputSetCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
}