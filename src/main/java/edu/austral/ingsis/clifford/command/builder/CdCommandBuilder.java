package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.command.CdCommand;
import edu.austral.ingsis.clifford.command.Command;

public class CdCommandBuilder implements CommandBuilder {

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
    public Command build(String arguments, Directory currentDirectory) {
        return new CdCommand(currentDirectory, arguments);
    }

}
