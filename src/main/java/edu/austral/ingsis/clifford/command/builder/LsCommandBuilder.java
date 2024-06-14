package edu.austral.ingsis.clifford.command.builder;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.LsCommand;
import edu.austral.ingsis.clifford.filesystem.Directory;

public class LsCommandBuilder implements CommandBuilder {

  private boolean ascendingOrder;
  private boolean noOrder;

  @Override
  public Command build(String arguments, Directory currentDirectory) {
    parseArguments(arguments);
    return new LsCommand(currentDirectory, ascendingOrder, noOrder);
  }

  private void parseArguments(String arguments) {

    boolean ascending = "--ord=asc".equals(arguments);
    boolean descending = "--ord=desc".equals(arguments);

    setOrderOfItems(ascending, descending);
  }

  private void setOrderOfItems(boolean ascending, boolean descending) {
    if (ascending) {
      setAscendingOrder(true);
    } else {
      if (descending) {
        setAscendingOrder(false);
      } else {
        setNoOrder(true);
      }
    }
  }

  public void setAscendingOrder(boolean ascendingOrder) {
    this.ascendingOrder = ascendingOrder;
    this.noOrder = false;
  }

  public void setNoOrder(boolean noOrder) {
    this.noOrder = noOrder;
  }
}
