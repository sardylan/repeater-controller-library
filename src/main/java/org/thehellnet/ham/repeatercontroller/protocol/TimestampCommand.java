package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public abstract class TimestampCommand extends AbstractCommand {

    protected ZonedDateTime timestamp;

    public TimestampCommand(CommandType commandType, CommandByte commandByte) {
        super(commandType, commandByte);
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        setTimestamp(timestamp.atZone(ZoneId.of("UTC")));
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        if (timestamp.getZone() != ZoneId.of("UTC")) {
            timestamp = timestamp.withZoneSameInstant(ZoneId.of("UTC"));
        }
        this.timestamp = timestamp;
    }

    @Override
    public byte[] serializeArgs() {
        int unixTs = (int) (timestamp.toInstant().toEpochMilli() / 1000);
        return ByteUtility.int32ToBytesBE(unixTs);
    }

    @Override
    protected void parseCommandArgs(byte[] args) {
        byte[] tsArgs = Arrays.copyOfRange(args, 0, 4);
        int unixTs = ByteUtility.bytesBEToInt32(tsArgs);
        timestamp = Instant.ofEpochSecond(unixTs).atZone(ZoneId.of("UTC"));
    }

    @Override
    protected int argsSize() {
        return 4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimestampCommand that = (TimestampCommand) o;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timestamp);
    }

    @Override
    public String toString() {
        return String.format("%s [%s]", super.toString(), timestamp.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
