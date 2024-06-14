package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.LsCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class LsCommandBuilder implements CommandBuilder{

    private Directory currentDirectory;
    private boolean ascendingOrder;
    private boolean noOrder;

    public LsCommandBuilder setCurrentDirectory(Directory directory) {
        this.currentDirectory = directory;
        return this;
    }

    @Override
    public Command build() {
        return new LsCommand(currentDirectory, ascendingOrder, noOrder);
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
}
