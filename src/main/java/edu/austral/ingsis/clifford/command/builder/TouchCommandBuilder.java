package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.TouchCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class TouchCommandBuilder implements CommandBuilder{

    private Directory currentDirectory;
    private String fileName;

    public TouchCommandBuilder setCurrentDirectory(Directory currentDirectory) {
        this.currentDirectory = currentDirectory;
        return this;
    }

    public TouchCommandBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public Command build() {
        return new TouchCommand(currentDirectory, fileName);
    }
}
