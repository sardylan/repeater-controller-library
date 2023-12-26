package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class RTCReadResponseCommand extends AbstractResponseCommand {

    protected LocalDateTime timestamp;

    public RTCReadResponseCommand(ResponseType responseType) {
        super(CommandType.RTCRead, responseType);
    }

    protected RTCReadResponseCommand(CommandType commandType, ResponseType responseType) {
        super(commandType, responseType);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public void parseArgs(byte[] args) {
        byte[] tsArgs = Arrays.copyOfRange(args, 0, 4);
        timestamp = ByteUtility.bytesToDateTime(tsArgs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RTCReadResponseCommand that = (RTCReadResponseCommand) o;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timestamp);
    }

    @Override
    public String toString() {
        return String.format("RTCReadResponse [%s]", timestamp.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
