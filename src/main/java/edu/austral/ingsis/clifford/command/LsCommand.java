package edu.austral.ingsis.clifford.command;

import java.util.*;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;

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
        contents = listContents(directory);
        return String.join(" ", contents);
    }

    public List<String> listContents(Directory directory) {
        Map<String, Directory> directorySubDirectories = directory.getSubDirectories();
        Map<String, File> directoryFiles = directory.getFiles();

        Set<String> subDirectories = directorySubDirectories.keySet();
        Set<String> files = directoryFiles.keySet();

        List<String> contents = new ArrayList<>(subDirectories);
        contents.addAll(files);

        if (noOrder){
            return contents;
        }

        if (ascendingOrder) {
            Collections.sort(contents);
        } else {
            Comparator<String> reverseOrder = Collections.reverseOrder();
            contents.sort(reverseOrder);
        }

        return contents;
    }
}


