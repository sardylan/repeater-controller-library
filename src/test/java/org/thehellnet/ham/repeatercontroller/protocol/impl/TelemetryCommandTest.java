package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TelemetryCommandTest {

    @Test
    void serializeArgs() {
        TelemetryCommand input = new TelemetryCommand(CommandType.Request);
        byte[] expected = new byte[0];
        byte[] actual = input.serializeArgs();
        assertArrayEquals(expected, actual);
    }

    @Test
    void parseCommandArgs() {
        byte[] input = new byte[]{
                (byte) 0x65, (byte) 0x7d, (byte) 0xe2, (byte) 0xb2,
                (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        TelemetryCommand expected = new TelemetryCommand(CommandType.Response);
        expected.setTimestamp(LocalDateTime.of(2023, 12, 16, 17, 47, 30, 0));
        expected.setBatteryVoltage(12.5f);
        expected.setBatteryChargeCurrent(0);
        expected.setPanelVoltage(14.6f);
        expected.setPanelCurrent(0);

        TelemetryCommand actual = new TelemetryCommand(CommandType.Response);
        actual.parseArgs(input);

        assertEquals(expected, actual);
    }
}