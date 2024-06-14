package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.RmCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class RmCommandBuilder implements CommandBuilder {

    @Override
    public Command build(String arguments, Directory currentDirectory) {
        boolean isRecursive = isRecursive(arguments);
        String name = extractName(arguments);
        return new RmCommand(currentDirectory, name, isRecursive);
    }

    private boolean isRecursive(String arguments) {
        return arguments != null && arguments.contains("--recursive");
    }

    private String extractName(String arguments) {
        if (arguments != null) {
            return arguments.replace("--recursive", "").trim();
        }
        return null;
    }
}
