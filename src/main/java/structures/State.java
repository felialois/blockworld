/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import controller.Globals;
import java.util.ArrayList;
import java.util.List;

public class State {

  //Operation to be performed in the state
  private final Operation operation;

  //Predicates from the state higher up, that need to be checked with the current state
  private final List<Predicate> regressionPredicates;

  //State of all the checked predicates.

  /**
   * TRUE : if the precondition is in the operation's ADD FALSE : if the precondition is in the
   * operation's DELETE NONE : if it's in neither and needs to be added at the next level as a
   * precondition
   */

  public State(Operation operation, List<Predicate> regressionPredicates) {
    this.operation = operation;
    this.regressionPredicates = regressionPredicates;
  }

  public State(List<Predicate> regressionPredicates) {
    this.operation = null;
    this.regressionPredicates = regressionPredicates;
  }


  public Operation getOperation() {
    return operation;
  }

  public List<Predicate> getRegressionPredicates() {
    return regressionPredicates;
  }

  //TODO IMPLEMENT
  public int getColumnsLeft() {
    return 3;
  }

  /**
   * Creates a new state from a previous state and an operation
   *
   * @param operation operation
   * @param oldState  previous state from a higher level
   *
   * @return
   */
  public static State createState(Operation operation, State oldState) {
    List<Predicate> regressionPredicates = new ArrayList<>();

    for (Predicate pred : oldState.getRegressionPredicates()) {
      if (operation.getAdd().contains(pred)) {

      } else if (operation.getDelete().contains(pred)) {
        return null;
      } else {
        regressionPredicates.add(pred);
      }
    }

    regressionPredicates.addAll(operation.getPreconditions());
    return new State(operation, regressionPredicates);
  }

  /**
   * Get all the possible operations that could've produced a specific predicate
   *
   * @param pred  predicate
   * @param state state of the predicate to be analyzed
   *
   * @return
   */
  public List<Operation> getOperations(Predicate pred, State state) {
    List<Operation> result = new ArrayList<>();
    switch (pred.getType()) {
      case ON_TABLE:
        // Take the block for the leave operation
        Block blockToLeave = (Block) pred.getParams().get(0);
        // If the block is light enough for the left arm then add the left arm operation
        if (blockToLeave.getWeight() <= 1) {
          result.add(
              Operation.makeLeave(blockToLeave, Globals.left, state.getColumnsLeft()));
        }
        // Add the right arm operation
        result.add(
            Operation.makeLeave(blockToLeave, Globals.right, state.getColumnsLeft()));
        break;
      case ON:
        break;
      case CLEAR:
        break;
      case EMPTY_ARM:
        break;
      case HOLDING:
        break;
      case USED_COLS_NUM:
        break;
      case HEAVIER:
        break;
      case LIGHT_BLOCK:
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
    return (regressionPredicates != null) ? regressionPredicates.equals(state.regressionPredicates)
        : (state.regressionPredicates == null);
  }

  @Override
  public int hashCode() {
    int result = (operation != null) ? operation.hashCode() : 0;
    result = (31 * result) + ((regressionPredicates != null) ? regressionPredicates.hashCode() : 0);
    return result;
  }
}
