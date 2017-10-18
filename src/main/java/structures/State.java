/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import com.google.common.collect.Sets;
import controller.Globals;
import java.util.ArrayList;
import java.util.List;
import structures.Arm.ARM_DIRECTION;
import structures.Predicate.TYPE;

public class State {

  //Operation to be performed in the state
  private final Operation operation;

  //Predicates from the state higher up, that need to be checked with the current state
  private final List<Predicate> regressionPredicates;

  // Blocks with no other block on top
  private List<Block> clearBlocks;

  //Used columns
  private int usedColumns;

  //Saves if the left arm is empty
  private boolean leftArmEmpty = true;

  //Saves if the right arm is empty
  private boolean rightArmEmpty = true;

  public boolean isLeftArmEmpty() {
    return leftArmEmpty;
  }

  public boolean isRightArmEmpty() {
    return rightArmEmpty;
  }

  /**
   * TRUE : if the precondition is in the operation's ADD FALSE : if the precondition is in the
   * operation's DELETE NONE : if it's in neither and needs to be added at the next level as a
   * precondition
   */

  public State(Operation operation, List<Predicate> regressionPredicates) {
    this.operation = operation;
    this.regressionPredicates = regressionPredicates;
    prepareState();

  }

  public State(List<Predicate> regressionPredicates) {
    this.operation = null;
    this.regressionPredicates = regressionPredicates;
    prepareState();
  }


  public Operation getOperation() {
    return operation;
  }

  public List<Predicate> getRegressionPredicates() {
    return regressionPredicates;
  }

  public int getUsedColumns() {
    return usedColumns;
  }

  /**
   * Checks if a block is clear in the current state
   *
   * @param block block to be checked
   *
   * @return if the block is clear
   */
  public boolean isBlockClear(Block block) {
    return clearBlocks.contains(block);
  }

  /**
   * Checks if a block is on the table
   *
   * @param block block to be checked
   *
   * @return if the block is on the table
   */
  public boolean isBlockOnTable(Block block) {
    for (Predicate predicate : regressionPredicates) {
      if ((predicate.getType() == TYPE.ON_TABLE)
          && predicate.getParams().get(0).equals(block)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if an arm is empty
   *
   * @param block block to be checked
   *
   * @return if the block is on the table
   */
  public boolean isArmEmpty(Block block) {
    for (Predicate predicate : regressionPredicates) {
      if ((predicate.getType() == TYPE.ON_TABLE)
          && predicate.getParams().get(0).equals(block)) {
        return true;
      }
    }
    return false;
  }

  public List<Block> getClearBlocks() {
    return clearBlocks;
  }

  /**
   * Get all the clear blocks in a state
   */
  private void prepareState() {
    clearBlocks = new ArrayList<>();
    for (Predicate predicate : regressionPredicates) {
      if (predicate.getType() == TYPE.CLEAR) {
        clearBlocks.add((Block) predicate.getParams().get(0));
      }
      if (predicate.getType() == TYPE.USED_COLS_NUM) {
        usedColumns = ((Column) predicate.getParams().get(0)).getColumnNumber();
      }
      if (predicate.getType() == TYPE.HOLDING) {
        clearBlocks.remove((Block) predicate.getParams().get(0));
        if (((Arm) predicate.getParams().get(1)).getArmType() == ARM_DIRECTION.RIGHT) {
          rightArmEmpty = false;
        } else if (((Arm) predicate.getParams().get(1)).getArmType() == ARM_DIRECTION.LEFT) {
          leftArmEmpty = false;
        }
      }
    }
  }

  /**
   * Creates a new state from a previous state and an operation
   *
   * @param oldState  previous state from a higher level
   * @param operation operation
   *
   * @return new state that represents a state BEFORE the operation was applied to the old one
   */
  public static State createState(State oldState, Operation operation) {
    List<Predicate> predicateList = new ArrayList<>();

    for (Predicate predicate : oldState.getRegressionPredicates()) {
      if (operation.getAdd().contains(predicate)) {
        //if the add contains the TRUE then we don't need to add it to the next predicate list
        continue;
      } else if (operation.getDelete().contains(predicate) && (predicate.getType() != TYPE
          .USED_COLS_NUM)) {
        // if the operation's delete contains a predicate then the state is FALSE and can't continue
        return null;
      } else {
        // if it's not true o
        predicateList.add(predicate);
      }
    }

    predicateList.addAll(operation.getPreconditions());

    return new State(operation, predicateList);
  }

  /**
   * Creates a list of states from all the possible operations that could come before a state
   *
   * @param oldState old state
   *
   * @return list of states
   */
  public static List<State> createNextLevelStates(State oldState) {
    List<State> states = new ArrayList<>();
    List<Operation> operations;

    for (Predicate pred : oldState.getRegressionPredicates()) {
      operations = getOperations(pred, oldState);
      for (Operation operation : operations) {
        states.add(createState(oldState, operation));
      }

    }

    return states;
  }

  /**
   * Get all the possible operations that could've produced a specific predicate
   *
   * @param pred  predicate
   * @param state state of the predicate to be analyzed
   *
   * @return
   */
  public static List<Operation> getOperations(Predicate pred, State state) {
    List<Operation> result = new ArrayList<>();
    Block blockY;
    Block blockX;
    Arm arm;

    switch (pred.getType()) {
      case ON_TABLE:
        // Take the block for the leave operation
        Block blockToLeave = (Block) pred.getParams().get(0);
        // If the block is not clear don't create the leave operation
        if (state.isBlockClear(blockToLeave)) {
          // If the block is light enough for the left arm then add the left arm operation
          // also check if the arm is empty
          if (blockToLeave.lightBlock() && state.leftArmEmpty) {
            result.add(
                Operation.makeLeave(blockToLeave, Globals.left, state.getUsedColumns()));
          } else if (state.rightArmEmpty) {
            // Add the right arm operation
            result.add(
                Operation.makeLeave(blockToLeave, Globals.right, state.getUsedColumns()));
          }
        }
        break;
      case ON:
        // ON (X, Y)
        blockX = (Block) pred.getParams().get(0);
        blockY = (Block) pred.getParams().get(1);
        //
        if (state.isBlockClear(blockX) && (blockX.getWeight() <= blockY.getWeight())) {
          if (blockX.lightBlock() && state.leftArmEmpty) {
            result.add(
                Operation.makeStack(blockX, blockY, Globals.left)
            );
          }
          if (state.rightArmEmpty) {
            result.add(Operation.makeStack(blockX, blockY, Globals.right));
          }
        }
        break;
      case HOLDING:
        // HOLDING (x,A)
        blockX = (Block) pred.getParams().get(0);
        arm = (Arm) pred.getParams().get(1);
        //Pick Up
        if (state.getUsedColumns() < 3) {
          result.add(
              Operation.makePickUp(blockX, arm, state.getUsedColumns())
          );
        }
        //Unstack
        for (Block clearBlock : state.getClearBlocks()) {
          if (blockX.getWeight() <= clearBlock.getWeight()) {
            result.add(
                Operation.makeUnstack(blockX, clearBlock, arm)
            );
          }
        }
        break;
      case CLEAR:
        break;
      case EMPTY_ARM:
        break;
    }
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if ((o == null) || (getClass() != o.getClass())) { return false; }

    State state = (State) o;

    if ((operation != null) ? !operation.equals(state.operation) : (state.operation != null)) {
      return false;
    }
    return (regressionPredicates != null) ?
        Sets.newHashSet(regressionPredicates).equals(Sets.newHashSet(state.regressionPredicates))
        : (state.regressionPredicates == null);
  }

  @Override
  public int hashCode() {
    int result = (operation != null) ? operation.hashCode() : 0;
    result = (31 * result) + ((regressionPredicates != null) ? regressionPredicates.hashCode() : 0);
    return result;
  }
}
