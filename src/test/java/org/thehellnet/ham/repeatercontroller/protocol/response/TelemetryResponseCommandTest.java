package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TelemetryResponseCommandTest {

    @Test
    void parseArgs() {
        byte[] input = new byte[]{
                (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x41, (byte) 0x48, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x01
        };

        TelemetryResponseCommand responseCommand = new TelemetryResponseCommand();
        responseCommand.parseArgs(input);

        assertEquals(12.5f, responseCommand.getBatteryVoltage());
        assertEquals(0.0f, responseCommand.getBatteryChargeCurrent());
        assertEquals(14.6f, responseCommand.getPanelVoltage());
        assertEquals(0.0f, responseCommand.getPanelCurrent());
        assertTrue(responseCommand.isGlobalStatus());
    }
}