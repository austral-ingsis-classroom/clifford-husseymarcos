package edu.austral.ingsis.clifford;

import java.util.HashMap;
import java.util.Map;

public class Directory implements FileSystem{

    private String name;
    private Directory parent;
    private Map<String, Directory> subDirectories;
    private Map<String, File> files;



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



    public Directory getSubDirectory(String name) {
        for (FileSystem fs : contents) {

            String fsName = fs.getName();
            boolean fsEqualsName = fsName.equals(name);

            if (fs instanceof Directory && fsEqualsName) {
                return (Directory) fs;
            }
        }
        return null;
    }


}
