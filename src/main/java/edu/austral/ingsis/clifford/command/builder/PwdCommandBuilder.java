package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.PwdCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class PwdCommandBuilder implements CommandBuilder {

  @Override
  public Command build(String arguments, Directory currentDirectory) {
    return new PwdCommand(currentDirectory);
  }
}
