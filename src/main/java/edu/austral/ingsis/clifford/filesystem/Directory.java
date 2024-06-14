package edu.austral.ingsis.clifford.filesystem;

import java.util.*;

public class Directory implements FileSystem {

  private final String name;
  private final Directory parent;
  private final Map<String, FileSystem> allFileSystems;

  public Directory(String name) {
    this(name, null);
  }

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.allFileSystems = new LinkedHashMap<>();
  }

  @Override
  public String name() {
    return name;
  }

  public Directory getParent() {
    return parent;
  }

  public void add(FileSystem archive) {
    String archiveName = archive.name();
    allFileSystems.remove(archiveName);
    allFileSystems.put(archiveName, archive);
  }

  public Directory getSubDirectory(String name) {
    FileSystem archive = allFileSystems.get(name);
    if (archive instanceof Directory) {
      return (Directory) archive;
    }
    return null;
  }

  public Map<String, Directory> getSubDirectories() {
    Map<String, Directory> subDirectories = new HashMap<>();
    for (FileSystem fileSystem : allFileSystems.values()) {
      if (fileSystem instanceof Directory directory) {
        subDirectories.put(directory.name(), directory);
      }
    }
    return subDirectories;
  }

  public File getFile(String name) {
    FileSystem archive = allFileSystems.get(name);
    if (archive instanceof File) {
      return (File) archive;
    }
    return null;
  }

  public void removeFile(String name) {
    allFileSystems.remove(name);
  }

  public boolean removeSubDirectory(String directoryName) {
    Directory subDirectoryToRemove = getSubDirectory(directoryName);
    if (subDirectoryToRemove != null) {
      allFileSystems.remove(directoryName);
      return true;
    }
    return false;
  }

  public String getPath() {
    Deque<String> pathDeque = new LinkedList<>();
    Directory current = this;

    while (current != null) {
      String currentDirectoryName = current.name();
      if (!currentDirectoryName.equals("/")) {
        pathDeque.addFirst(currentDirectoryName);
      }
      current = current.getParent();
    }

    if (pathDeque.isEmpty()) {
      return "/";
    } else {
      return "/" + String.join("/", pathDeque);
    }
  }

  public Directory directory() {
    return parent;
  }

  public Map<String, FileSystem> getAllFileSystems() {
    return allFileSystems;
  }

  @Override
  public String toString() {
    return name;
  }

  public Directory getRootDirectory() {
    Directory current = this;
    while (current.getParent() != null) {
      current = current.getParent();
    }
    return current;
  }


}
