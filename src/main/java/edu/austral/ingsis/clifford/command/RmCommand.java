package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;

public class RmCommand implements Command {

  Directory currentDirectory;
  private final String name;
  private final boolean recursive;

  public RmCommand(Directory currentDirectory, String name, boolean recursive) {
    this.currentDirectory = currentDirectory;
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public String execute() {
    if (recursive) {
      return removeRecursive(currentDirectory, name)
          ? "'" + name + "' removed"
          : "Failed to remove '" + name + "'";
    } else {
      return removeNonRecursive(currentDirectory, name);
    }
  }

  private boolean removeRecursive(Directory directory, String directoryName) {
    Directory subDirectoryToRemove = directory.getSubDirectory(directoryName);

    if (subDirectoryToRemove != null) {
      for (Directory subDirectory : subDirectoryToRemove.getSubDirectories().values()) {

        String subDirectoryName = subDirectory.getName();
        removeRecursive(subDirectoryToRemove, subDirectoryName);
      }

      return directory.removeSubDirectory(directoryName);
    }

    File fileToRemove = directory.getFile(directoryName);
    directory.removeFile(directoryName);
    return fileToRemove != null;
  }

  private String removeNonRecursive(Directory directory, String itemName) {
    Directory subDirectoryToRemove = directory.getSubDirectory(itemName);

    if (subDirectoryToRemove != null) {
      return "Use --recursive option to remove directories";
    }

    File fileToRemove = directory.getFile(itemName);

    if (fileToRemove != null) {
      directory.removeFile(itemName);
      return "'" + itemName + "' removed";
    }
    return "File or directory '" + itemName + "' does not exist";
  }

  @Override
  public String toString() {
    return "RmCommand{"
        + "currentDirectory="
        + currentDirectory.getName()
        + ", name='"
        + name
        + '\''
        + ", recursive="
        + recursive
        + '}';
  }
}
