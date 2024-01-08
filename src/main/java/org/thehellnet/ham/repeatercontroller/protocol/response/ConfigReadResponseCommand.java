package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.enums.ConfigParam;
import org.thehellnet.ham.repeatercontroller.utility.ByteUtility;

import java.util.Arrays;
import java.util.Objects;

public class ConfigReadResponseCommand extends AbstractResponseCommand {

    protected ConfigParam configParam;
    protected Object value;

    public ConfigReadResponseCommand() {
        super(CommandType.ConfigRead);
    }

    protected ConfigReadResponseCommand(CommandType commandType) {
        super(commandType);
    }

    public ConfigParam getConfigParam() {
        return configParam;
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

    @Override
    public void parseArgs(byte[] args) {
        configParam = ConfigParam.parse(args[0]);

        switch (configParam) {
            case MainVoltageOff:
            case MainVoltageOn:
                value = ByteUtility.bytesBEToFloat32(Arrays.copyOfRange(args, 1, 5));
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConfigReadResponseCommand that = (ConfigReadResponseCommand) o;
        return configParam == that.configParam && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), configParam, value);
    }

    @Override
    public String toString() {
        return "ConfigReadResponseCommand";
    }
}
