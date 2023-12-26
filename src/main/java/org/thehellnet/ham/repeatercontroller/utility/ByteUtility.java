package org.thehellnet.ham.repeatercontroller.utility;

import org.thehellnet.ham.repeatercontroller.exception.ConversionException;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

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

    public static byte[] uint32ToBytesBE(long value) {
        if (value < 0 || value > 4294967295L) {
            throw new ConversionException("Value is out of Integer limits");
        }

        byte[] array = ByteBuffer
                .allocate(8)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(value & 0xffffffffL)
                .array();
        return Arrays.copyOfRange(array, 4, 8);
    }

    public static long bytesBEToUInt32(byte[] value) {
        if (value == null || value.length != 4) {
            throw new ConversionException("Invalid array");
        }

        byte[] array = new byte[8];
        System.arraycopy(value, 0, array, 4, 4);
        long longValue = ByteBuffer
                .wrap(array)
                .order(ByteOrder.BIG_ENDIAN)
                .getLong();
        return longValue & 0xffffffffL;
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

    public static LocalDateTime bytesToDateTime(byte[] value) {
        if (value == null || value.length != 4) {
            throw new ConversionException("Invalid array");
        }

        long unixTs = ByteUtility.bytesBEToUInt32(value);
        return Instant.ofEpochSecond(unixTs).atZone(ZoneId.of("UTC")).toLocalDateTime();
    }
}
