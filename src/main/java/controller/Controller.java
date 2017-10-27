/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package controller;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import structures.Block;
import structures.Cancellation;
import structures.Cancellation.REASON;
import structures.Operation;
import structures.State;

public class Controller {

  private final int maxColumns;
  private final List<Block> blocks;
  private final State initialState;
  private final State finalState;
  private final List<State> visitedStates;
  private final int cancelledToPrint = 50;

  public Controller(int maxColumns, List<Block> blocks, State initialState, State finalState) {
    Globals.setColumnNumber(maxColumns);
    this.maxColumns = maxColumns;
    this.blocks = blocks;
    this.initialState = initialState;
    this.finalState = finalState;
    visitedStates = new ArrayList<>();
  }

  public void run() {
    int statesGenerated = 0;
    Queue<State> queue = new LinkedList<>();
    //start with the final state
    queue.add(finalState);
    try {
      //perform the regression breathe first
      while (!queue.peek().equals(initialState)) {
        State currentState = queue.remove();
        visitedStates.add(currentState);
        //Get the next level states for the current state
        List<State> nextLevelStates
            = State.createNextLevelStates(currentState);

        statesGenerated += nextLevelStates.size();

        //add the non visited states to the queue
        for (State nextLevelState : nextLevelStates) {
          if (!visitedStates.contains(nextLevelState) && (nextLevelState != null)) {
            queue.add(nextLevelState);

          } else {
            Globals.addCancelledState(
                new Cancellation(
                    "State cancelled because the state had already been visited",
                    REASON.PREVIOUSLY_VISITED_STATE,
                    nextLevelState
                )
            );
          }
        }

      }
    } catch (Exception e) {
      System.out.println("Solution not found");
    }
    State iState = queue.remove();
    List<Operation> plan = Lists.reverse(iState.getPlan());

    System.out.println("Operators : " + plan.size());
    System.out.println("States Generated : " + statesGenerated);
    System.out.println("Plan : " + plan);
    System.out.println("Cancelled States : " +
        Globals.getCancelledStates().subList(0, cancelledToPrint));


  }
}
