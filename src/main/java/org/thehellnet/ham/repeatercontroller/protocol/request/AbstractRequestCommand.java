package org.thehellnet.ham.repeatercontroller.protocol.request;

import org.thehellnet.ham.repeatercontroller.protocol.Command;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;

import java.util.Objects;

public abstract class AbstractRequestCommand implements Command, RequestCommand {

    private final CommandType commandType;

    public AbstractRequestCommand(CommandType commandType) {
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
        AbstractRequestCommand that = (AbstractRequestCommand) o;
        return commandType == that.commandType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType);
    }
}
