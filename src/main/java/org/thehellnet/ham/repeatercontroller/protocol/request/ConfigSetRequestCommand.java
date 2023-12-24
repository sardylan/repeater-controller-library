package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.util.Objects;

public class ConfigSetRequestCommand extends ConfigReadRequestCommand {

    private Object value;

    public ConfigSetRequestCommand() {
        super(CommandType.ConfigSet);
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
                if (!(value instanceof Float)) {
                    throw new ProtocolException("Value must be float");
                }
                this.value = value;
            }
            break;

            default:
                throw new ProtocolException("Value not supported");
        }
    }

    @Override
    public byte[] serializeArgs() {
        byte[] args = new byte[5];

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConfigSetRequestCommand that = (ConfigSetRequestCommand) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value);
    }
}
