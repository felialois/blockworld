/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import structures.Block;
import structures.State;

public class Main {

  public static void main(String[] args){

    int maxColumns = 3;
    List<Block> blocks = new ArrayList<>();
    State initialState = null;
    State finalState = null;

    Queue<State> queue = new LinkedList<>();
    queue.add(finalState);
    while(!queue.peek().equals(initialState)){
      State currentState = queue.remove();
      List<State> nextLevelStates
          = State.createNextLevelStates(currentState);
      queue.addAll(nextLevelStates);

    }


  }

}
