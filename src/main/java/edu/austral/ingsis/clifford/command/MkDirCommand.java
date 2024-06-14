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
        boolean containsSlash = dirName.contains("/");
        boolean containsSpaces = dirName.contains(" ");

        boolean invalidDirectory = containsSlash || containsSpaces;

        if (invalidDirectory) {
            return "Invalid directory name: '" + dirName + "'. It cannot contain '/' or spaces.";
        }

        Directory newDir = new Directory(dirName, currentDirectory);
        currentDirectory.add(newDir);
        return "'" + dirName + "' directory created";
    }


    @Override
    public String toString() {
        return "mkdir {" +
                "current Directory=" + currentDirectory.getName() +
                ", new Directory ='" + dirName + '\'' +
                '}';
    }


}
