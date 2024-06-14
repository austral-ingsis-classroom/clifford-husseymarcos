package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.MkDirCommand;

public class MkDirCommandBuilder implements CommandBuilder {

    @Override
    public Command build(String arguments, Directory currentDirectory) {
        return new MkDirCommand(currentDirectory, arguments);
    }
}



