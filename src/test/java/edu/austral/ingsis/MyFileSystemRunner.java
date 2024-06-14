package edu.austral.ingsis;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.command.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFileSystemRunner implements FileSystemRunner{

    private Directory currentDirectory;
    private final Map<String, CommandBuilder> commandBuilders;

    public MyFileSystemRunner() {
        this.currentDirectory = new Directory("root");
        this.commandBuilders = new HashMap<>();
        initializeCommandBuilders();
    }


    private void initializeCommandBuilders() {
        commandBuilders.put("cd", new CdCommandBuilder()
                .setCurrentDirectory(currentDirectory)
                .setDirName(""));


        commandBuilders.put("mkdir", new MkDirCommandBuilder()
                .setCurrentDirectory(currentDirectory)
                .setDirName(""));


    }

    @Override
    public List<String> executeCommands(List<String> commands) {
        List<String> results = new ArrayList<>();

        for (String command : commands) {
            results.add(executeCommand(command));
        }

        return results;
    }

    private String executeCommand(String command) {
        String[] parts = command.split(" ");
        String mainCommand = parts[0];

        CommandBuilder builder = commandBuilders.get(mainCommand);
        if (builder == null) {
            return "Unknown command: " + command;
        }

        switch (mainCommand) {
            case "mkdir":
                ((MkDirCommandBuilder) builder).setDirName(parts.length > 1 ? parts[1] : "");
                break;
            case "cd":
                ((CdCommandBuilder) builder).setDirName(parts.length > 1 ? parts[1] : "");
                break;
        }

        Command cmd = builder.build();
        String result = cmd.execute();

        if (cmd instanceof CdCommand) {
            currentDirectory = ((CdCommand) cmd).getCurrentDirectory();
        }

        return result;
    }

}
