/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import structures.Block;
import structures.State;

public class Controller {

  private final int maxColumns;
  private final List<Block> blocks;
  private final State initialState;
  private final State finalState;
  private final List<State> visitedStates;

  public Controller(int maxColumns, List<Block> blocks, State initialState, State finalState) {
    Globals.setColumnNumber(maxColumns);
    this.maxColumns = maxColumns;
    this.blocks = blocks;
    this.initialState = initialState;
    this.finalState = finalState;
    visitedStates = new ArrayList<>();
  }

  public void run(){
    Queue<State> queue = new LinkedList<>();
    queue.add(finalState);
    while(!queue.peek().equals(initialState)){
      State currentState = queue.remove();
      visitedStates.add(currentState);
      List<State> nextLevelStates
          = State.createNextLevelStates(currentState);
      for (State nextLevelState : nextLevelStates) {
        if(!visitedStates.contains(nextLevelState) && (nextLevelState != null)){
          queue.add(nextLevelState);

        }
      }

    }

    State iState = queue.remove();
    System.out.println(iState.getPlan());

  }
}
