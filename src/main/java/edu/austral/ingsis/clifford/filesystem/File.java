package edu.austral.ingsis.clifford.filesystem;

public class File implements FileSystem {

  private final String name;
  private final Directory directory;

  public File(String name, Directory directory) {
    this.directory = directory;
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getRootDirectory() {
    return directory;
  }
}
