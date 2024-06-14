package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.CdCommand;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class CdCommandBuilder implements CommandBuilder {

  @Override
  public Command build(String arguments, Directory currentDirectory) {
    return new CdCommand(currentDirectory, arguments);
  }
}
