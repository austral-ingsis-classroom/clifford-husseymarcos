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
        if (name.equals("/")) {
            currentDirectory = currentDirectory.getRootDirectory();
            return "moved to directory '" + currentDirectory.getName() + "'";
        }

        String[] directories = name.split("/");
        Directory destination = currentDirectory;

        for (String dir : directories) {
            if (dir.equals("..")) {
                Directory parent = destination.getParent();
                if (parent != null) {
                    destination = parent;
                } else {
                    return "moved to directory '" + currentDirectory.getName() + "'";
                }
            } else {
                Directory nextDir = destination.getSubDirectory(dir);
                if (nextDir != null) {
                    destination = nextDir;
                } else {
                    return "'" + dir + "' directory does not exist";
                }
            }
        }

        currentDirectory = destination;
        return "moved to directory '" + destination.getName() + "'";
    }


    public Directory getCurrentDirectory() {
        return currentDirectory;
    }

    @Override
    public String toString() {
        return "cd {" +
                "current Directory=" + currentDirectory.getName() +
                ", new Directory ='" + name + '\'' +
                '}';
    }
}
