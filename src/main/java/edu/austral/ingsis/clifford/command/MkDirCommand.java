package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.ArchiveCreator;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class MkDirCommand implements Command {

  private final Directory currentDirectory;
  private final String dirName;

  ArchiveCreator archiveCreator = new ArchiveCreator();

  public MkDirCommand(Directory currentDirectory, String dirName) {
    this.currentDirectory = currentDirectory;
    this.dirName = dirName;
  }

  @Override
  public String execute() {
    Directory newDirectory = new Directory(dirName, currentDirectory);
    return archiveCreator.createArchive(newDirectory);
  }

  @Override
  public String toString() {
    return "mkdir {"
        + "current Directory="
        + currentDirectory.name()
        + ", new Directory ='"
        + dirName
        + '\''
        + '}';
  }
}
