package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.File;
import edu.austral.ingsis.clifford.filesystem.FileSystem;

public class ArchiveCreator {

    public String createArchive(FileSystem archive) {

        String name = archive.name();
        Directory directory = archive.directory();

        if (isInvalidName(name)) {
            return "Invalid name: '" + name + "'. It cannot contain '/' or spaces.";
        }

        if (archive instanceof Directory) {
            Directory newDir = new Directory(name, directory);
            directory.add(newDir);
            return "'" + name + "' directory created";

        } else if (archive instanceof File) {
            File newFile = new File(name, directory);
            directory.add(newFile);
            return "'" + name + "' file created";
        }

        return "Unknown archive type";
    }

    private boolean isInvalidName(String name) {
        return name.contains("/") || name.contains(" ");
    }
}
