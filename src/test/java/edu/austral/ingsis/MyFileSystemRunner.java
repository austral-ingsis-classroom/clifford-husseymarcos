package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.CdCommand;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.builder.CdCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.CommandBuilder;
import edu.austral.ingsis.clifford.command.builder.LsCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.MkDirCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.PwdCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.RmCommandBuilder;
import edu.austral.ingsis.clifford.command.builder.TouchCommandBuilder;
import edu.austral.ingsis.clifford.filesystem.Directory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyFileSystemRunner implements FileSystemRunner {

  private Directory currentDirectory;
  private final Map<String, CommandBuilder> commandBuilders;

  public MyFileSystemRunner() {
    this.currentDirectory = new Directory("/");
    this.commandBuilders = new HashMap<>();
    initializeCommandBuilders();
  }

  private void initializeCommandBuilders() {
    commandBuilders.put("cd", new CdCommandBuilder());
    commandBuilders.put("ls", new LsCommandBuilder());
    commandBuilders.put("mkdir", new MkDirCommandBuilder());
    commandBuilders.put("touch", new TouchCommandBuilder());
    commandBuilders.put("rm", new RmCommandBuilder());
    commandBuilders.put("pwd", new PwdCommandBuilder());
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
    String parameters = command.substring(mainCommand.length()).trim();

    CommandBuilder builder = commandBuilders.get(mainCommand);

    if (builder == null) {
      return "Unknown command: " + command;
    }

    Command cmd = builder.build(parameters, currentDirectory);
    String result = cmd.execute();

    checkChangeCurrentDirectory(cmd);

    return result;
  }

  private void checkChangeCurrentDirectory(Command cmd) {
    if (cmd instanceof CdCommand) {
      CdCommand cdCommand = (CdCommand) cmd;
      currentDirectory = cdCommand.getCurrentDirectory();
    }
  }
}
