package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.LsCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class LsCommandBuilder implements CommandBuilder{

    private Directory directory;
    private boolean ascendingOrder;
    private boolean noOrder;

    public LsCommandBuilder setDirectory(Directory directory) {
        this.directory = directory;
        return this;
    }

    public LsCommandBuilder setAscendingOrder(boolean ascendingOrder) {
        this.ascendingOrder = ascendingOrder;
        this.noOrder = false;
        return this;
    }

    public LsCommandBuilder setNoOrder(boolean noOrder) {
        this.noOrder = noOrder;
        return this;
    }

    @Override
    public Command build() {
        return new LsCommand(directory, ascendingOrder, noOrder);
    }
}
