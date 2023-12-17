package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.util.Arrays;
import java.util.Objects;

public abstract class ConfigCommand extends AbstractCommand {

    protected ConfigParam configParam = null;
    protected Object value;

    public ConfigCommand(CommandType commandType, CommandByte commandByte) {
        super(commandType, commandByte);
    }

    public ConfigParam getConfigParam() {
        return configParam;
    }

    public void setConfigParam(ConfigParam configParam) {
        this.configParam = configParam;
    }

    public float getValueFloat() {
        if (configParam == null) {
            throw new ProtocolException("Config param not set yet");
        }

        switch (configParam) {
            case MainVoltageOff:
            case MainVoltageOn:
                return (float) value;
        }

        throw new ProtocolException("Value not supported");
    }

    public void setValue(Object value) {
        if (configParam == null) {
            throw new ProtocolException("Config param not set yet");
        }

        switch (configParam) {
            case MainVoltageOff:
            case MainVoltageOn: {
                if (value instanceof Float) {
                    this.value = value;
                    return;
                }
            }
            break;
        }

        throw new ProtocolException("Value not supported");
    }

    @Override
    public void parseArgs(byte[] args) {
        if (args.length < 1) {
            throw new ProtocolException("Invalid args length");
        }

        parseConfigParam(args);
        super.parseArgs(args);
    }

    @Override
    public byte[] serializeArgs() {
        byte[] args = new byte[argsSize()];
        args[0] = configParam.serialize();

        switch (configParam) {
            case MainVoltageOff:
            case MainVoltageOn:
                System.arraycopy(ByteUtility.float32ToBytesBE((float) value), 0, args, 1, 4);
                break;
        }

        return args;
    }

    @Override
    protected void parseCommandArgs(byte[] args) {
        parseConfigParam(args);

        switch (configParam) {
            case MainVoltageOff:
            case MainVoltageOn:
                value = ByteUtility.bytesBEToFloat32(Arrays.copyOfRange(args, 1, 5));
                break;
        }
    }

    @Override
    protected int argsSize() {
        if (configParam == null) {
            throw new ProtocolException("Config param not set yet");
        }

        switch (configParam) {
            case MainVoltageOff:
            case MainVoltageOn:
                return 5;

        }

        throw new ProtocolException("Not Implemented");
    }

    protected void parseConfigParam(byte[] args) {
        configParam = ConfigParam.parse(args[0]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConfigCommand that = (ConfigCommand) o;
        return configParam == that.configParam && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), configParam, value);
    }

    @Override
    public String toString() {
        return String.format("%s %s=%s", super.toString(), configParam, value);
    }
}
