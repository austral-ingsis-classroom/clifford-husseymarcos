package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;

public class CdCommand implements Command {

  Directory currentDirectory;
  private final String parameter;

  public CdCommand(Directory currentDirectory, String parameter) {
    this.currentDirectory = currentDirectory;
    this.parameter = parameter;
  }

  @Override
  public String execute() {

    boolean getToRootArgument = parameter.equals("/");

    if (getToRootArgument) {
      currentDirectory = currentDirectory.getRootDirectory();
      return "moved to directory '" + currentDirectory.name() + "'";
    }

    String[] directories = parameter.split("/");

    Directory destination = currentDirectory;

    for (String dir : directories) {

      boolean getToParentDirectory = dir.equals("..");

      if (getToParentDirectory) {

        Directory parent = destination.directory();

        if (parent != null) {
          destination = parent;
        } else {
          return "moved to directory '" + currentDirectory.name() + "'";
        }

      } else {
        Directory nextDir = destination.getSubDirectory(dir);
        if (nextDir != null) {
          destination = nextDir;
        } else {
          return "'" + dir + "' directory does not exist";
        }
      }
    }

    currentDirectory = destination;
    return "moved to directory '" + destination.name() + "'";
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  @Override
  public String toString() {
    return "cd {"
        + "current Directory="
        + currentDirectory.name()
        + ", new Directory ='"
        + parameter
        + '\''
        + '}';
  }
}
