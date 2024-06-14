package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.filesystem.Directory;

public interface CommandBuilder {
    Command build(String arguments, Directory currentDirectory);
}
