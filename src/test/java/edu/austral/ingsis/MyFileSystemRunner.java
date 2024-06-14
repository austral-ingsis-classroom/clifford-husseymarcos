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
            String executedCommand = executeCommand(command);
            results.add(executedCommand);
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

        String arguments = parts[1];

        switch (mainCommand) {
            case "mkdir":
                MkDirCommandBuilder mkDirCommandBuilder = (MkDirCommandBuilder) builder;
                mkDirCommandBuilder.setDirName(arguments);
                break;
            case "cd":
                CdCommandBuilder cdCommandBuilder = (CdCommandBuilder) builder;
                cdCommandBuilder.setDirName(arguments);
                break;
        }

        Command cmd = builder.build();
        String result = cmd.execute();

        if (cmd instanceof CdCommand cdCommand) {
            currentDirectory = cdCommand.getCurrentDirectory();
        }

        return result;
    }

}
