package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;

public class CdCommand implements Command{

    Directory currentDirectory;
    private final String name;

    public CdCommand(Directory currentDirectory, String name) {
        this.currentDirectory = currentDirectory;
        this.name = name;
    }

    @Override
    public String execute() {
        if (name.equals("..")) {
            Directory parent = currentDirectory.getParent();
            if (parent != null) {
                currentDirectory = parent;
                return "moved to directory '" + parent.getName() + "'";
            } else {
                return "moved to directory '/'";
            }
        } else {
            Directory newDirectory = currentDirectory.getSubDirectory(name);
            if (newDirectory != null) {
                currentDirectory = newDirectory;
                return "Changed directory to '" + name + "'";
            } else {
                return "'" + name + "' directory does not exist";
            }
        }
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }
}
