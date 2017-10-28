/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package controller;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import structures.Block;
import structures.Column;
import structures.Predicate;
import structures.Predicate.TYPE;
import structures.State;
import structures.Structure;

public class ControllerTest extends TestCase {

  private State testFinalState;
  private State testInitialState;
  private final Block A = new Block("A", 1);
  private final Block B = new Block("B", 2);
  private final Block C = new Block("C", 2);
  private final Block D = new Block("D", 3);
  private final Block F = new Block("F", 1);

  @Override
  public void setUp() throws Exception{
    List<Predicate> statePreds = new ArrayList<>();
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(B)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(C, B)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, D)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, A)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.left)
        ));
    statePreds.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.right)
        ));
    statePreds.add(
        new Predicate(
            TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(2))
        ));

    testFinalState = new State(statePreds);

    List<Predicate> initPreds = new ArrayList<>();
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(C)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(B, C)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, B)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, D)
        ));
    initPreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
        ));
    initPreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(A)
        ));
    initPreds.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.left)
        ));
    initPreds.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.right)
        ));
    initPreds.add(
        new Predicate(
            TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(2))
        ));

    testInitialState = new State(initPreds);

  }

  public void testReadFile() throws Exception{
    Controller controller =
        new Controller("/Users/felipe/Downloads/PlannerFiles 2/testing2.txt",50);

    controller.run();
  }

  public void testRun() throws Exception {

    Controller controller = new Controller(
        3,
        Lists.newArrayList(A,B,C,D,F),
        testInitialState,
        testFinalState);

    controller.run();
  }

}