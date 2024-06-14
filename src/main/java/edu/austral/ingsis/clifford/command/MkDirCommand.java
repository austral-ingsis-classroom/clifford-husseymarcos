package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;

public class MkDirCommand implements Command{

    private final Directory currentDirectory;
    private final String dirName;


    public MkDirCommand(Directory currentDirectory, String dirName) {
        this.currentDirectory = currentDirectory;
        this.dirName = dirName;
    }


    @Override
    public String execute() {
        if (dirName.contains("/") || dirName.contains(" ")) {
            return "Invalid directory name: '" + dirName + "'. It cannot contain '/' or spaces.";
        }

        Directory newDir = new Directory(dirName, currentDirectory);
        currentDirectory.add(newDir);
        return "'" + dirName + "' directory created";
    }


}
