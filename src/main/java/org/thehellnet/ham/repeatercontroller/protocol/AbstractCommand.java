package org.thehellnet.ham.repeatercontroller.protocol;

import org.thehellnet.ham.repeatercontroller.exception.ProtocolException;

import java.util.Objects;

public abstract class AbstractCommand implements Command {

    private final CommandType commandType;
    private final CommandByte commandByte;

    public AbstractCommand(CommandType commandType, CommandByte commandByte) {
        this.commandType = commandType;
        this.commandByte = commandByte;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public CommandByte getCommandByte() {
        return commandByte;
    }

    public void parseArgs(byte[] args) {
        if (args.length != argsSize()) {
            throw new ProtocolException("Invalid args length");
        }

        parseCommandArgs(args);
    }

    protected abstract void parseCommandArgs(byte[] args);

    protected abstract int argsSize();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractCommand that = (AbstractCommand) o;
        return commandType == that.commandType && commandByte == that.commandByte;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, commandByte);
    }

    @Override
    public String toString() {
        return String.format("%s %s", commandType.toString(), commandByte.toString());
    }
}
