package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;

public class TouchCommand implements Command {

  private final Directory currentDirectory;
  private final String fileName;

  ArchiveCreator archiveCreator = new ArchiveCreator();


  public TouchCommand(Directory currentDirectory, String fileName) {
    this.currentDirectory = currentDirectory;
    this.fileName = fileName;
  }

  @Override
  public String execute() {
    return archiveCreator.createArchive(new File(fileName, currentDirectory));
  }
}
