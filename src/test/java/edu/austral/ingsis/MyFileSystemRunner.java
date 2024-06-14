package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.command.builder.CdCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.CommandBuilder;
import edu.austral.ingsis.clifford.command.builder.LsCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.MkDirCommandBuilder;
import edu.austral.ingsis.clifford.filesystem.Directory;

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

        commandBuilders.put("ls", new LsCommandBuilder()
                .setDirectory(currentDirectory)
                .setNoOrder(true));

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
                mkdirCommandOptions((MkDirCommandBuilder) builder, arguments);
                break;
            case "ls":
                lsCommandOptions((LsCommandBuilder) builder, parts);
                break;
            case "cd":
                cdCommandOptions((CdCommandBuilder) builder, arguments);
                break;
        }

        Command cmd = builder.build();
        String result = cmd.execute();

        checkChangeCurrentDirectory(cmd);

        return result;
    }

    private void checkChangeCurrentDirectory(Command cmd) {
        if (cmd instanceof CdCommand cdCommand) {
            currentDirectory = cdCommand.getCurrentDirectory();
        }
    }

    private  void cdCommandOptions(CdCommandBuilder builder, String arguments) {
        builder.setDirName(arguments);
    }

    private  void lsCommandOptions(LsCommandBuilder builder, String[] parts) {
        String arguments = parts[1];
        boolean ascendingOrder = "--ord=asc".equals(arguments);
        if (ascendingOrder) {
            builder.setAscendingOrder(true);
        } else if ("--ord=desc".equals(arguments)) {
            builder.setAscendingOrder(false);
        } else {
            builder.setNoOrder(true);
        }
    }

    private  void mkdirCommandOptions(MkDirCommandBuilder builder, String arguments) {
        builder.setDirName(arguments);
    }

}
