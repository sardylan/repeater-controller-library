package org.thehellnet.ham.repeatercontroller.protocol.impl;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.AbstractCommand;
import org.thehellnet.ham.repeatercontroller.protocol.CommandByte;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.util.Objects;

public class OutputReadCommand extends AbstractCommand {

    private int outputNumber;
    private boolean status;

    public OutputReadCommand(CommandType commandType) {
        super(commandType, CommandByte.OutputRead);
    }

    public int getOutputNumber() {
        return outputNumber;
    }

    public void setOutputNumber(int outputNumber) {
        if (outputNumber < 1 || outputNumber > OUTPUT_MAX_NUMBER) {
            throw new ProtocolException("Output number out of bounds");
        }

        this.outputNumber = outputNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public byte[] serializeArgs() {
        if (getCommandType() == CommandType.Request) {
            return new byte[]{(byte) outputNumber};
        }

        return new byte[]{(byte) outputNumber, (byte) (status ? 0x01 : 0x00)};
    }

    @Override
    protected void parseCommandArgs(byte[] args) {
        outputNumber = args[0];

        if (getCommandType() == CommandType.Response) {
            status = args[1] > 0;
        }
    }

    @Override
    protected int argsSize() {
        if (getCommandType() == CommandType.Request) {
            return 1;
        }

        return 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputReadCommand that = (OutputReadCommand) o;
        return outputNumber == that.outputNumber && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), outputNumber, status);
    }

    @Override
    public String toString() {
        return String.format("%s %d:%b", super.toString(), outputNumber, status);
    }
}
