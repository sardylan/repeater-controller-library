package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeteoResponseCommandTest {

    @Test
    void parseArgs() {
        byte[] input = new byte[]{
                (byte) 0x41, (byte) 0x69, (byte) 0x99, (byte) 0x9a,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        };

        MeteoResponseCommand meteoCommand = new MeteoResponseCommand();
        meteoCommand.parseArgs(input);

        assertEquals(12.5f, meteoCommand.getPressure());
        assertEquals(0.0f, meteoCommand.getTemperature());
    }
}