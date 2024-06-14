package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.command.builder.*;
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
                .setCurrentDirectory(currentDirectory)
                .setNoOrder(true));

        commandBuilders.put("mkdir", new MkDirCommandBuilder()
                .setCurrentDirectory(currentDirectory)
                .setDirName(""));

        commandBuilders.put("touch", new TouchCommandBuilder()
        .setCurrentDirectory(currentDirectory)
                .setFileName(""));

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

        boolean hasArguments = parts.length > 1;
        String arguments = hasArguments ? parts[1] : "";

        switch (mainCommand) {
            case "mkdir":
                mkdirCommandOptions((MkDirCommandBuilder) builder, arguments);
                break;
            case "ls":
                lsCommandOptions((LsCommandBuilder) builder, arguments);
                break;
            case "cd":
                cdCommandOptions((CdCommandBuilder) builder, arguments);
                break;
            case "touch":
                touchCommandOptions((TouchCommandBuilder) builder, arguments);
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

    private  void lsCommandOptions(LsCommandBuilder builder, String arguments) {
        boolean ascendingOrder = "--ord=asc".equals(arguments);
        boolean descendingOrder = "--ord=desc".equals(arguments);


        if (ascendingOrder) {
            builder.setAscendingOrder(true);
        } else {
            if (descendingOrder) {
                builder.setAscendingOrder(false);
            } else {
                builder.setNoOrder(true);
            }
        }
    }

    private  void touchCommandOptions(TouchCommandBuilder builder, String arguments) {
        builder.setFileName(arguments);
    }

    private  void mkdirCommandOptions(MkDirCommandBuilder builder, String arguments) {
        builder.setDirName(arguments);
    }

}
