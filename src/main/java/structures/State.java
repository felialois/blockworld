/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import java.util.List;

public class State {

  //Operation to be performed in the state
  private final Operation operation;

  //Predicates from the state higher up, that need to be checked with the current state
  private final List<Predicate> regressionPredicates;

  //State of all the checked predicates.
  /**
   * TRUE : if the precondition is in the operation's ADD
   * FALSE : if the precondition is in the operation's DELETE
   * NONE : if it's in neither and needs to be added at the next level as a precondition
   */
  private final List<REGRESSION_STATE> regressionStates;

  public State(Operation operation, List<Predicate> regressionPredicates, List<REGRESSION_STATE>
      regressionStates) {
    this.operation = operation;
    this.regressionPredicates = regressionPredicates;
    this.regressionStates = regressionStates;
  }

  public Operation getOperation() {
    return operation;
  }

  public List<Predicate> getRegressionPredicates() {
    return regressionPredicates;
  }

  public List<REGRESSION_STATE> getRegressionStates() {
    return regressionStates;
  }

  public enum REGRESSION_STATE{
    TRUE,
    FALSE,
    NONE
  }



}
