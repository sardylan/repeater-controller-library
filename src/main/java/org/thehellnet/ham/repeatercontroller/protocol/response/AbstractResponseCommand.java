package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.Command;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.util.Objects;

public abstract class AbstractResponseCommand implements Command, ResponseCommand {

    private final CommandType commandType;

    public AbstractResponseCommand(CommandType commandType) {
        this.commandType = commandType;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractResponseCommand that = (AbstractResponseCommand) o;
        return commandType == that.commandType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType);
    }

    public abstract String toString();
}

