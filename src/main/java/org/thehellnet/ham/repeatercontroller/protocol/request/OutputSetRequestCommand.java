package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.util.Objects;

public class OutputSetRequestCommand extends OutputReadRequestCommand {

    private boolean status;

    public OutputSetRequestCommand() {
        super(CommandType.OutputSet);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[]{(byte) outputNumber, (byte) (status ? 0x01 : 0x00)};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputSetRequestCommand that = (OutputSetRequestCommand) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), status);
    }
}
