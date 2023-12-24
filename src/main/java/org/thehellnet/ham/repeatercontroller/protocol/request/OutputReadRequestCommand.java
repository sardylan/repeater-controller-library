package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.util.Objects;

public class OutputReadRequestCommand extends AbstractRequestCommand {

    static final int maxOutputs = 8;

    protected int outputNumber;

    public OutputReadRequestCommand() {
        super(CommandType.OutputRead);
    }

    protected OutputReadRequestCommand(CommandType commandType) {
        super(commandType);
    }

    public int getOutputNumber() {
        return outputNumber;
    }

    public void setOutputNumber(int outputNumber) {
        if (outputNumber < 0 || outputNumber >= maxOutputs) {
            throw new ProtocolException("Output number out of allowed values");
        }

        this.outputNumber = outputNumber;
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[]{(byte) outputNumber};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OutputReadRequestCommand that = (OutputReadRequestCommand) o;
        return outputNumber == that.outputNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), outputNumber);
    }
}
