package org.thehellnet.ham.repeatercontroller.protocol.response;

import org.thehellnet.ham.repeatercontroller.protocol.Command;
import org.thehellnet.ham.repeatercontroller.protocol.CommandType;
import org.thehellnet.ham.repeatercontroller.protocol.ResponseType;

import java.util.Objects;

public abstract class AbstractResponseCommand implements Command, ResponseCommand {

    private final CommandType commandType;
    private final ResponseType responseType;

    public AbstractResponseCommand(CommandType commandType, ResponseType responseType) {
        this.commandType = commandType;
        this.responseType = responseType;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractResponseCommand that = (AbstractResponseCommand) o;
        return commandType == that.commandType && responseType == that.responseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, responseType);
    }
}

