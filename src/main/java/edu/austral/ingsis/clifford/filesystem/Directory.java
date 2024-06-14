package edu.austral.ingsis.clifford.filesystem;

import java.util.*;

public class Directory implements FileSystem {

    private final String name;
    private final Directory parent;
    private final Map<String, Directory> subDirectories;
    private final Map<String, File> files;

    public Directory(String name) {
        this(name, null);
    }

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.subDirectories = new LinkedHashMap<>();
        this.files = new HashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public void add(Directory directory) {
        subDirectories.put(directory.getName(), directory);
    }

    public void add(File file) {
        files.put(file.getName(), file);
    }


    public Directory getSubDirectory(String name) {
        return subDirectories.get(name);
    }

    public Map<String, Directory> getSubDirectories() {
        return subDirectories;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public File getFile(String name) {
        return files.get(name);
    }

    public void removeFile(String name) {
        files.remove(name);
    }

    public boolean removeSubDirectory(String directoryName) {
        Directory subDirectoryToRemove = subDirectories.get(directoryName);

        if (subDirectoryToRemove != null) {

            Collection<Directory> directories = subDirectoryToRemove.getSubDirectories().values();

            for (Directory subSubDirectory : directories) {
                String subDirectoryName = subSubDirectory.getName();
                removeSubDirectory(subDirectoryName);
            }

            subDirectoryToRemove.getFiles().clear();
            return subDirectories.remove(directoryName) != null;
        }

        return false;
    }

    @Override
    public String toString() {
        return name;
    }


}
