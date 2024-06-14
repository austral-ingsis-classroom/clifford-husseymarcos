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
        commandBuilders.put("cd", new CdCommandBuilder());
        commandBuilders.put("ls", new LsCommandBuilder());
        commandBuilders.put("mkdir", new MkDirCommandBuilder());
        commandBuilders.put("touch", new TouchCommandBuilder());
        commandBuilders.put("rm", new RmCommandBuilder());
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

        Command cmd = builder.build(arguments, currentDirectory);
        String result = cmd.execute();

        checkChangeCurrentDirectory(cmd);

        return result;
    }

    private void checkChangeCurrentDirectory(Command cmd) {
        if (cmd instanceof CdCommand cdCommand) {
            currentDirectory = cdCommand.getCurrentDirectory();
        }
    }


}
