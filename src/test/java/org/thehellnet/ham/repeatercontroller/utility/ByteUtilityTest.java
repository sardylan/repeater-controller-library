package org.thehellnet.ham.repeatercontroller.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteUtilityTest {

    @Test
    void int32ToBytesBE() {
        assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}, ByteUtility.int32ToBytesBE(0));
        assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01}, ByteUtility.int32ToBytesBE(1));
        assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01}, ByteUtility.int32ToBytesBE(257));
        assertArrayEquals(new byte[]{(byte) 0x65, (byte) 0x7d, (byte) 0xc3, (byte) 0x97}, ByteUtility.int32ToBytesBE(1702740887));
    }

    @Test
    void bytesBEToInt32() {
        assertEquals(0, ByteUtility.bytesBEToInt32(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}));
        assertEquals(1, ByteUtility.bytesBEToInt32(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01}));
        assertEquals(257, ByteUtility.bytesBEToInt32(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01}));
        assertEquals(1702740887, ByteUtility.bytesBEToInt32(new byte[]{(byte) 0x65, (byte) 0x7d, (byte) 0xc3, (byte) 0x97}));
    }

    @Test
    void float32ToBytesBE() {
        assertArrayEquals(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}, ByteUtility.float32ToBytesBE(0.0f));
        assertArrayEquals(new byte[]{(byte) 0x3f, (byte) 0x80, (byte) 0x00, (byte) 0x00}, ByteUtility.float32ToBytesBE(1.0f));
        assertArrayEquals(new byte[]{(byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00}, ByteUtility.float32ToBytesBE(12.5f));
        assertArrayEquals(new byte[]{(byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a}, ByteUtility.float32ToBytesBE(14.6f));
    }

    @Test
    void bytesBEToFloat32() {
        assertEquals(0, ByteUtility.bytesBEToFloat32(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00}));
        assertEquals(1, ByteUtility.bytesBEToFloat32(new byte[]{(byte) 0x3f, (byte) 0x80, (byte) 0x00, (byte) 0x00}));
        assertEquals(12.5f, ByteUtility.bytesBEToFloat32(new byte[]{(byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00}));
        assertEquals(14.6f, ByteUtility.bytesBEToFloat32(new byte[]{(byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a}));
    }
}
