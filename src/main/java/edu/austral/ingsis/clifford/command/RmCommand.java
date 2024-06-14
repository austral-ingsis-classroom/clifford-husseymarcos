package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;
import java.util.Collection;

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

      Collection<Directory> subDirectorySubDirectories =
          subDirectoryToRemove.getSubDirectories().values();

      for (Directory subDirectory : subDirectorySubDirectories) {

        String subDirectoryName = subDirectory.name();
        removeRecursive(subDirectoryToRemove, subDirectoryName);
      }

      return directory.removeSubDirectory(directoryName);
    }

    return removeFile(directory, directoryName);
  }

  private boolean removeFile(Directory directory, String directoryName) {
    File fileToRemove = directory.getFile(directoryName);
    directory.removeFile(directoryName);
    return fileToRemove != null;
  }

  private String removeNonRecursive(Directory directory, String itemName) {

    Directory subDirectoryToRemove = directory.getSubDirectory(itemName);

    if (subDirectoryToRemove != null) {
      return "cannot remove '" + itemName + "', is a directory";
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
        + currentDirectory.name()
        + ", name='"
        + name
        + '\''
        + ", recursive="
        + recursive
        + '}';
  }
}
