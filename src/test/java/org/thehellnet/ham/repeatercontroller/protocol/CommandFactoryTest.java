package org.thehellnet.ham.repeatercontroller.protocol;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.request.*;
import org.thehellnet.ham.repeatercontroller.protocol.response.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void serializeRequestPing() {
        PingRequestCommand input = new PingRequestCommand();
        byte[] expected = new byte[]{(byte) 'p'};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeRequestReset() {
        ResetRequestCommand input = new ResetRequestCommand();
        byte[] expected = new byte[]{(byte) 'X'};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeRequestTelemetry() {
        TelemetryRequestCommand input = new TelemetryRequestCommand();
        byte[] expected = new byte[]{(byte) 't'};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeRequestRTCRead() {
        RTCReadRequestCommand input = new RTCReadRequestCommand();
        byte[] expected = new byte[]{(byte) 'r'};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeRequestRTCSet() {
        RTCSetRequestCommand input = new RTCSetRequestCommand();
        input.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime());
        byte[] expected = new byte[5];
        expected[0] = (byte) 'R';
        byte[] actual = CommandFactory.serializeRequest(input);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected.length, actual.length);
    }

    @Test
    void serializeRequestConfigRead() {
        ConfigReadRequestCommand input = new ConfigReadRequestCommand();
        input.setConfigParam(ConfigParam.MainVoltageOn);
        byte[] expected = new byte[]{(byte) 'c', (byte) 'O'};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeRequestConfigSet() {
        ConfigSetRequestCommand input = new ConfigSetRequestCommand();
        input.setConfigParam(ConfigParam.MainVoltageOn);
        input.setValue(0.0f);
        byte[] expected = new byte[6];
        expected[0] = (byte) 'C';
        expected[1] = (byte) 'O';
        byte[] actual = CommandFactory.serializeRequest(input);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected.length, actual.length);
    }

    @Test
    void serializeRequestOutputRead() {
        OutputReadRequestCommand input = new OutputReadRequestCommand();
        input.setOutputNumber(2);
        byte[] expected = new byte[]{(byte) 'o', (byte) 0x02};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializeRequestOutputSet() {
        OutputSetRequestCommand input = new OutputSetRequestCommand();
        input.setOutputNumber(2);
        input.setStatus(true);
        byte[] expected = new byte[]{(byte) 'O', (byte) 0x02, (byte) 0x01};
        byte[] actual = CommandFactory.serializeRequest(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void parseResponsePing() {
        byte[] payload = new byte[]{CommandType.Ping.serialize(), (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(PingResponseCommand.class, responseCommand);

        PingResponseCommand actual = (PingResponseCommand) responseCommand;
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")), actual.getTimestamp());
    }

    @Test
    void parseResponseReset() {
        byte[] payload = new byte[]{CommandType.Reset.serialize()};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(ResetResponseCommand.class, responseCommand);
    }

    @Test
    void parseResponseTelemetry() {
        byte[] payload = new byte[]{
                CommandType.Telemetry.serialize(),
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00
        };
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(TelemetryResponseCommand.class, responseCommand);

        TelemetryResponseCommand expected = (TelemetryResponseCommand) responseCommand;
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")), expected.getTimestamp());
        assertEquals(0.0f, expected.getBatteryVoltage());
        assertEquals(0.0f, expected.getBatteryChargeCurrent());
        assertEquals(0.0f, expected.getPanelVoltage());
        assertEquals(0.0f, expected.getPanelCurrent());
    }

    @Test
    void parseResponseRTCRead() {
        byte[] payload = new byte[]{CommandType.RTCRead.serialize(), (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(RTCReadResponseCommand.class, responseCommand);

        RTCReadResponseCommand actual = (RTCReadResponseCommand) responseCommand;
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")), actual.getTimestamp());
    }

    @Test
    void parseResponseRTCSet() {
        byte[] payload = new byte[]{CommandType.RTCSet.serialize(), (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(RTCSetResponseCommand.class, responseCommand);

        RTCSetResponseCommand actual = (RTCSetResponseCommand) responseCommand;
        assertEquals(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")), actual.getTimestamp());
    }

    @Test
    void parseResponseConfigRead() {
        byte[] payload = new byte[]{CommandType.ConfigRead.serialize(), ConfigParam.MainVoltageOff.serialize(), (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(ConfigReadResponseCommand.class, responseCommand);

        ConfigReadResponseCommand actual = (ConfigReadResponseCommand) responseCommand;
        assertEquals(ConfigParam.MainVoltageOff, actual.getConfigParam());
        assertEquals(0.0f, actual.getValueFloat());
    }

    @Test
    void parseResponseConfigSet() {
        byte[] payload = new byte[]{CommandType.ConfigSet.serialize(), ConfigParam.MainVoltageOn.serialize(), (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(ConfigSetResponseCommand.class, responseCommand);

        ConfigSetResponseCommand actual = (ConfigSetResponseCommand) responseCommand;
        assertEquals(ConfigParam.MainVoltageOn, actual.getConfigParam());
        assertEquals(0.0f, actual.getValueFloat());
    }

    @Test
    void parseResponseOutputRead() {
        byte[] payload = new byte[]{CommandType.OutputRead.serialize(), (byte) 0x00, (byte) 0x00};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(OutputReadResponseCommand.class, responseCommand);

        OutputReadResponseCommand actual = (OutputReadResponseCommand) responseCommand;
        assertEquals(0, actual.getOutputNumber());
        assertFalse(actual.isStatus());
    }

    @Test
    void parseResponseOutputSet() {
        byte[] payload = new byte[]{CommandType.OutputSet.serialize(), (byte) 0x00, (byte) 0x01};
        ResponseCommand responseCommand = CommandFactory.parseResponse(payload);
        assertInstanceOf(OutputSetResponseCommand.class, responseCommand);

        OutputSetResponseCommand actual = (OutputSetResponseCommand) responseCommand;
        assertEquals(0, actual.getOutputNumber());
        assertTrue(actual.isStatus());
    }
}