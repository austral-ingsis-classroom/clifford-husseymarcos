package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;

public class CdCommand implements Command{

    Directory currentDirectory;
    private String name;

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
                return "Changed directory to parent";
            } else {
                return "Already at the root directory.";
            }
        } else {
            Directory newDirectory = currentDirectory.getSubDirectory(name);
            if (newDirectory != null) {
                currentDirectory = newDirectory;
                return name;
            } else {
                return "";
            }
        }
    }

    public Directory getCurrentDirectory() {
        return currentDirectory;
    }
}
