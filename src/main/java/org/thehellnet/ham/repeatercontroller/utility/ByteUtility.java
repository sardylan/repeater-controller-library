package org.thehellnet.ham.repeatercontroller.utility;

import org.thehellnet.ham.repeatercontroller.exception.ConversionException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteUtility {

    public static byte[] int32ToBytesBE(int value) {
        return ByteBuffer
                .allocate(4)
                .order(ByteOrder.BIG_ENDIAN)
                .putInt(value)
                .array();
    }

    public static int bytesBEToInt32(byte[] value) {
        if (value == null || value.length != 4) {
            throw new ConversionException("Invalid array");
        }

        return ByteBuffer
                .wrap(value)
                .order(ByteOrder.BIG_ENDIAN)
                .getInt();
    }

    public static byte[] float32ToBytesBE(float value) {
        return ByteBuffer
                .allocate(4)
                .order(ByteOrder.BIG_ENDIAN)
                .putFloat(value)
                .array();
    }

    public static float bytesBEToFloat32(byte[] value) {
        if (value == null || value.length != 4) {
            throw new ConversionException("Invalid array");
        }

        return ByteBuffer
                .wrap(value)
                .order(ByteOrder.BIG_ENDIAN)
                .getFloat();
    }
}
