package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.TouchCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class TouchCommandBuilder implements CommandBuilder{

    @Override
    public Command build(String arguments, Directory currentDirectory) {
        return new TouchCommand(currentDirectory, arguments);
    }
}
