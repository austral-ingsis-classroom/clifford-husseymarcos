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
        this.subDirectories = new HashMap<>();
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

    public List<String> listContents(boolean ascendingOrder) {
        Set<String> subDirectories = this.subDirectories.keySet();
        Set<String> files = this.files.keySet();

        List<String> contents = new ArrayList<>(subDirectories);
        contents.addAll(files);

        if (ascendingOrder) {
            Collections.sort(contents);
        } else {
            Comparator<String> reverseOrder = Collections.reverseOrder();
            contents.sort(reverseOrder);
        }
        return contents;
    }


    public Directory getSubDirectory(String name) {
        return subDirectories.get(name);
    }

}
