package edu.austral.ingsis.clifford.command;

import java.util.List;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class LsCommand implements Command {

    private final Directory directory;
    private final boolean ascendingOrder;
    private final boolean noOrder;

    public LsCommand(Directory directory, boolean ascendingOrder, boolean noOrder) {
        this.directory = directory;
        this.ascendingOrder = ascendingOrder;
        this.noOrder = noOrder;
    }

    @Override
    public String execute() {
        List<String> contents;

        if (noOrder) {
            boolean noOrder = false;
            contents = directory.listContents(noOrder);
        } else {
            contents = directory.listContents(ascendingOrder);
        }
        return String.join(" ", contents);
    }
}


