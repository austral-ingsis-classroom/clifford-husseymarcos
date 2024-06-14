package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;

public class CdCommandBuilder implements CommandBuilder{

    private Directory currentDirectory;
    private String dirName;

    public CdCommandBuilder setCurrentDirectory(Directory currentDirectory) {
        this.currentDirectory = currentDirectory;
        return this;
    }

    public CdCommandBuilder setDirName(String dirName) {
        this.dirName = dirName;
        return this;
    }

    @Override
    public Command build() {
        return new CdCommand(currentDirectory, dirName);
    }

}
