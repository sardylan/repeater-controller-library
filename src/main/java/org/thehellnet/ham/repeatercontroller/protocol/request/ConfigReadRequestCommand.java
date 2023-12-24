package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ConfigParam;

import java.util.Objects;

public class ConfigReadRequestCommand extends AbstractRequestCommand {

    protected ConfigParam configParam;

    public ConfigReadRequestCommand() {
        super(CommandType.ConfigRead);
    }

    protected ConfigReadRequestCommand(CommandType commandType) {
        super(commandType);
    }

    public ConfigParam getConfigParam() {
        return configParam;
    }

    public void setConfigParam(ConfigParam configParam) {
        this.configParam = configParam;
    }

    @Override
    public byte[] serializeArgs() {
        return new byte[]{configParam.serialize()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConfigReadRequestCommand that = (ConfigReadRequestCommand) o;
        return configParam == that.configParam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), configParam);
    }
}
