package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class RTCSetRequestCommand extends RTCReadRequestCommand {

    private LocalDateTime timestamp;

    public RTCSetRequestCommand() {
        super(CommandType.RTCSet);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public byte[] serializeArgs() {
        int unixTs = (int) (timestamp.toInstant(ZoneOffset.UTC).toEpochMilli() / 1000);
        return ByteUtility.int32ToBytesBE(unixTs);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RTCSetRequestCommand that = (RTCSetRequestCommand) o;
        return Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), timestamp);
    }
}
