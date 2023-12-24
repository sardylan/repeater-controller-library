package org.thehellnet.ham.repeatercontroller.oldprotocol.impl;

class OutputReadCommandTest {

//    @Test
//    void setOutputNumber() {
//        OutputReadCommand input = new OutputReadCommand(CommandType.Request);
//
//        assertThrows(ProtocolException.class, () -> input.setOutputNumber(0));
//        assertThrows(ProtocolException.class, () -> input.setOutputNumber(9));
//    }
//
//    @Test
//    void serializeArgs() {
//        OutputReadCommand input = new OutputReadCommand(CommandType.Request);
//        input.setOutputNumber(1);
//        byte[] expected = new byte[]{0x01};
//        byte[] actual = input.serializeArgs();
//        assertArrayEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsActive() {
//        byte[] input = new byte[]{(byte) 0x03, (byte) 0x01};
//        OutputReadCommand expected = new OutputReadCommand(CommandType.Response);
//        expected.setOutputNumber(3);
//        expected.setStatus(true);
//
//        OutputReadCommand actual = new OutputReadCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void parseArgsDisactive() {
//        byte[] input = new byte[]{(byte) 0x04, (byte) 0x00};
//        OutputReadCommand expected = new OutputReadCommand(CommandType.Response);
//        expected.setOutputNumber(4);
//        expected.setStatus(false);
//
//        OutputReadCommand actual = new OutputReadCommand(CommandType.Response);
//        actual.parseArgs(input);
//
//        assertEquals(expected, actual);
//    }
}