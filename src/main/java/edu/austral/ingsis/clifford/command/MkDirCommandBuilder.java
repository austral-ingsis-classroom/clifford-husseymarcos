package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;

public class MkDirCommandBuilder implements CommandBuilder {

    private Directory currentDirectory;
    private String dirName;

    public MkDirCommandBuilder setCurrentDirectory(Directory currentDirectory) {
        this.currentDirectory = currentDirectory;
        return this;
    }

    public MkDirCommandBuilder setDirName(String dirName) {
        this.dirName = dirName;
        return this;
    }

    @Override
    public Command build() {
        return new MkDirCommand(currentDirectory, dirName);
    }
}



