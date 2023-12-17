package org.thehellnet.ham.repeatercontroller.protocol;

import org.junit.jupiter.api.Test;
import org.thehellnet.ham.repeatercontroller.protocol.impl.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void serializePayloadNull() {
        Command input = new NullCommand(CommandType.Request);
        byte[] expected = new byte[]{(byte) '\0'};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadPing() {
        Command input = new PingCommand(CommandType.Request);
        byte[] expected = new byte[]{(byte) 'p'};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadReset() {
        Command input = new ResetCommand(CommandType.Request);
        byte[] expected = new byte[]{(byte) 'X'};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadTelemetry() {
        Command input = new TelemetryCommand(CommandType.Request);
        byte[] expected = new byte[]{(byte) 't'};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadRTCRead() {
        Command input = new RTCReadCommand(CommandType.Request);
        byte[] expected = new byte[]{(byte) 'r'};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadRTCSet() {
        RTCSetCommand input = new RTCSetCommand(CommandType.Request);
        input.setTimestamp(ZonedDateTime.now(ZoneId.of("UTC")));
        byte[] expected = new byte[5];
        expected[0] = (byte) 'R';
        byte[] actual = CommandFactory.serializePayload(input);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected.length, actual.length);
    }

    @Test
    void serializePayloadConfigRead() {
        ConfigReadCommand input = new ConfigReadCommand(CommandType.Request);
        input.setConfigParam(ConfigParam.MainVoltageOn);
        byte[] expected = new byte[]{(byte) 'c', (byte) 'O'};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadConfigSet() {
        ConfigSetCommand input = new ConfigSetCommand(CommandType.Request);
        input.setConfigParam(ConfigParam.MainVoltageOn);
        input.setValue(0.0f);
        byte[] expected = new byte[6];
        expected[0] = (byte) 'C';
        expected[1] = (byte) 'O';
        byte[] actual = CommandFactory.serializePayload(input);
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected.length, actual.length);
    }

    @Test
    void serializePayloadOutputRead() {
        OutputReadCommand input = new OutputReadCommand(CommandType.Request);
        input.setOutputNumber(2);
        byte[] expected = new byte[]{(byte) 'o', (byte) 0x02};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void serializePayloadOutputSet() {
        OutputSetCommand input = new OutputSetCommand(CommandType.Request);
        input.setOutputNumber(2);
        input.setStatus(true);
        byte[] expected = new byte[]{(byte) 'O', (byte) 0x02, (byte) 0x01};
        byte[] actual = CommandFactory.serializePayload(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void parsePayloadNull() {
        byte[] input = new byte[]{(byte) 0x00};
        NullCommand expected = new NullCommand(CommandType.Response);
        NullCommand actual = (NullCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadPing() {
        byte[] input = new byte[]{(byte) 'p', (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        PingCommand expected = new PingCommand(CommandType.Response);
        expected.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")));
        PingCommand actual = (PingCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadReset() {
        byte[] input = new byte[]{(byte) 'X'};
        ResetCommand expected = new ResetCommand(CommandType.Response);
        ResetCommand actual = (ResetCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadTelemetry() {
        byte[] input = new byte[]{
                (byte) 't',
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };
        TelemetryCommand expected = new TelemetryCommand(CommandType.Response);
        expected.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")));
        expected.setBatteryVoltage(0.0f);
        expected.setBatteryChargeCurrent(0.0f);
        expected.setPanelVoltage(0.0f);
        expected.setPanelCurrent(0.0f);
        TelemetryCommand actual = (TelemetryCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadRTCRead() {
        byte[] input = new byte[]{
                (byte) 'r',
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };
        RTCReadCommand expected = new RTCReadCommand(CommandType.Response);
        expected.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")));
        RTCReadCommand actual = (RTCReadCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadRTCSet() {
        byte[] input = new byte[]{
                (byte) 'R',
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };
        RTCSetCommand expected = new RTCSetCommand(CommandType.Response);
        expected.setTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.of("UTC")));
        RTCSetCommand actual = (RTCSetCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadConfigRead() {
        byte[] input = new byte[]{(byte) 'c', (byte) 'o', (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ConfigReadCommand expected = new ConfigReadCommand(CommandType.Response);
        expected.setConfigParam(ConfigParam.MainVoltageOff);
        expected.setValue(0.0f);
        ConfigReadCommand actual = (ConfigReadCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadConfigSet() {
        byte[] input = new byte[]{(byte) 'C', (byte) 'o', (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        ConfigSetCommand expected = new ConfigSetCommand(CommandType.Response);
        expected.setConfigParam(ConfigParam.MainVoltageOff);
        expected.setValue(0.0f);
        ConfigSetCommand actual = (ConfigSetCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadOutputRead() {
        byte[] input = new byte[]{(byte) 'o', (byte) 0x02, (byte) 0x01};
        OutputReadCommand expected = new OutputReadCommand(CommandType.Response);
        expected.setOutputNumber(2);
        expected.setStatus(true);
        OutputReadCommand actual = (OutputReadCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void parsePayloadOutputSet() {
        byte[] input = new byte[]{(byte) 'O', (byte) 0x02, (byte) 0x01};
        OutputSetCommand expected = new OutputSetCommand(CommandType.Response);
        expected.setOutputNumber(2);
        expected.setStatus(true);
        OutputSetCommand actual = (OutputSetCommand) CommandFactory.parsePayload(input);
        assertEquals(expected, actual);
    }

    @Test
    void getCommand() {
        assertInstanceOf(NullCommand.class, CommandFactory.getCommand(CommandByte.Null));
        assertInstanceOf(PingCommand.class, CommandFactory.getCommand(CommandByte.Ping));
        assertInstanceOf(ResetCommand.class, CommandFactory.getCommand(CommandByte.Reset));
        assertInstanceOf(TelemetryCommand.class, CommandFactory.getCommand(CommandByte.Telemetry));
        assertInstanceOf(RTCReadCommand.class, CommandFactory.getCommand(CommandByte.RTCRead));
        assertInstanceOf(RTCSetCommand.class, CommandFactory.getCommand(CommandByte.RTCSet));
        assertInstanceOf(ConfigReadCommand.class, CommandFactory.getCommand(CommandByte.ConfigRead));
        assertInstanceOf(ConfigSetCommand.class, CommandFactory.getCommand(CommandByte.ConfigSet));
        assertInstanceOf(OutputReadCommand.class, CommandFactory.getCommand(CommandByte.OutputRead));
        assertInstanceOf(OutputSetCommand.class, CommandFactory.getCommand(CommandByte.OutputSet));
    }
}