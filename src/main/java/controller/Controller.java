/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package controller;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import structures.Block;
import structures.Cancellation;
import structures.Cancellation.REASON;
import structures.Column;
import structures.Operation;
import structures.Predicate;
import structures.Predicate.TYPE;
import structures.State;
import structures.Structure;

public class Controller {

  private final int maxColumns;
  private final List<Block> blocks;
  private final State initialState;
  private final State finalState;
  private final List<State> visitedStates;
  private final int cancelledToPrint;

  public Controller(int maxColumns, List<Block> blocks, State initialState, State finalState) {
    Globals.setColumnNumber(maxColumns);
    this.maxColumns = maxColumns;
    this.blocks = blocks;
    this.initialState = initialState;
    this.finalState = finalState;
    visitedStates = new ArrayList<>();
    cancelledToPrint = 50;
  }

  public void run() {

    long initialTime = System.currentTimeMillis();

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
    System.out.println("Total Cancelled States : " + Globals.getCancelledStates().size());
    System.out.println("Cancelled States (Top " +cancelledToPrint+") : "+
        Globals.getCancelledStates().subList(0, cancelledToPrint));
    System.out.println("Runtime : " + ((System.currentTimeMillis() - initialTime) / 1000.0) + " " +
        "seconds");

  }

  public Controller(String filename, int cancelledToPrint) throws Exception {
    visitedStates = new ArrayList<>();

    BufferedReader br = new BufferedReader(new FileReader(filename));
    String line = br.readLine();
    //Parse first line
    maxColumns = Integer.parseInt(line.split("=")[1].split(";")[0]);

    //Parse second line
    Map<String, Block> blks = new HashMap<>();
    List<Block> blockList = new ArrayList<>();
    String[] line2 = br.readLine().replace(";", "").split("=")[1].split("\\.");
    for (String block : line2) {
      String blockName = String.valueOf(block.charAt(0));
      int blockNumber = block.length() - 1;
      Block bl = new Block(blockName, blockNumber);
      blks.put(blockName, bl);
      blockList.add(bl);
    }

    //Parse third line
    String[] line3 = br.readLine().replace(";", "").split("=")[1].split("\\.");
    List<Predicate> predicatesIS = parsePred(line3, blks);
    initialState = new State(predicatesIS);

    //Parse fourth line
    String[] line4 = br.readLine().replace(";", "").split("=")[1].split("\\.");
    List<Predicate> predicatesFS = parsePred(line4, blks);
    finalState = new State(predicatesFS);

    blocks = blockList;

    this.cancelledToPrint = cancelledToPrint;


  }

  public static List<Predicate> parsePred(String[] line, Map<String, Block> blockMap) {

    List<Predicate> result = new ArrayList<>();
    int usedCols = 0;

    for (String pred : line) {
      String predName = pred.split("\\(")[0];
      String[] predParams = pred.split("\\(")[1]
          .split("\\)")[0].split(",");

      switch (predName) {
        case "ON-TABLE":
          result.add(
              new Predicate(
                  TYPE.ON_TABLE,
                  Lists.<Structure>newArrayList(blockMap.get(predParams[0])))
          );
          usedCols++;
          break;
        case "ON":
          result.add(
              new Predicate(
                  TYPE.ON,
                  Lists.<Structure>newArrayList(
                      blockMap.get(predParams[0]),
                      blockMap.get(predParams[1])
                  ))
          );
          break;
        case "CLEAR":
          result.add(
              new Predicate(
                  TYPE.CLEAR,
                  Lists.<Structure>newArrayList(blockMap.get(predParams[0])))
          );
          break;
        case "EMPTY-ARM":
          result.add(
              new Predicate(
                  TYPE.EMPTY_ARM,
                  Lists.<Structure>newArrayList(
                      predParams[0].equals("L") ? Globals.left : Globals.right
                  )));
          break;
        case "HOLDING":
          result.add(
              new Predicate(
                  TYPE.HOLDING,
                  Lists.<Structure>newArrayList(
                      blockMap.get(predParams[0]),
                      predParams[1].equals("L") ? Globals.left : Globals.right
                  )));
          break;
        default:
          throw new UnsupportedOperationException("The predicate " + predName + " could not be " +
              "parsed");
      }


    }

    result.add(new Predicate(
        TYPE.USED_COLS_NUM,
        Lists.<Structure>newArrayList(new Column(usedCols))));

    return result;

  }
}
