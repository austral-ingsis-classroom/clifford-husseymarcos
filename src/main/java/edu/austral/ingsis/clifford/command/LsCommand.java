package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.filesystem.Directory;
import edu.austral.ingsis.clifford.filesystem.FileSystem;
import java.util.*;

public class LsCommand implements Command {

  private final Directory directory;
  private final boolean ascendingOrder;
  private final boolean noOrder;

  public LsCommand(Directory directory, boolean ascendingOrder, boolean noOrder) {
    this.directory = directory;
    this.ascendingOrder = ascendingOrder;
    this.noOrder = noOrder;
  }

  @Override
  public String execute() {
    List<String> contents = listContents(directory);
    return String.join(" ", contents);
  }

  public List<String> listContents(Directory directory) {
    Map<String, FileSystem> allArchives = directory.getAllFileSystems();

    Set<String> allArchivesString = allArchives.keySet();

    List<String> contents = new ArrayList<>(allArchivesString);

    if (noOrder) {
      return contents;
    }

    if (ascendingOrder) {
      Collections.sort(contents);
    } else {
      Comparator<String> reverseOrder = Collections.reverseOrder();
      contents.sort(reverseOrder);
    }

    return contents;
  }
}
