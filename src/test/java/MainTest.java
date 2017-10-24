/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

import com.google.common.collect.Lists;
import controller.Controller;
import controller.Globals;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import junit.framework.TestCase;
import structures.Block;
import structures.Column;
import structures.Operation;
import structures.Predicate;
import structures.Predicate.TYPE;
import structures.State;
import structures.Structure;

public class MainTest extends TestCase {

  private final Block A = new Block("A", 1);
  private final Block B = new Block("B", 2);
  private final Block C = new Block("C", 2);
  private final Block D = new Block("D", 3);
  private final Block E = new Block("E", 4);
  private final Block F = new Block("F", 1);

  public void testMain1(){
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

    State testFinalState = new State(statePreds);

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

    State testInitialState = new State(initPreds);

    Controller controller = new Controller(
        3,
        Lists.newArrayList(A,B,C,D,F),
        testInitialState,
        testFinalState);

    controller.run();
  }

  public void testMain2(){
    List<Predicate> statePreds = new ArrayList<>();
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(E)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(B, E)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, B)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(A)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
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

    State testFinalState = new State(statePreds);

    List<Predicate> initPreds = new ArrayList<>();
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(E)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(F)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(B, C)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(C, E)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, F)
        ));
    initPreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(A)
        ));
    initPreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(B)
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

    State testInitialState = new State(initPreds);

    Controller controller = new Controller(
        3,
        Lists.newArrayList(A,B,C,D,F,E),
        testInitialState,
        testFinalState);

    controller.run();
  }

  public void testMain3(){
    List<Predicate> statePreds = new ArrayList<>();
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(E)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, F)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(D, E)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(B, D)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(A)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(B)
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

    State testInitialState = new State(statePreds);

    List<Predicate> initPreds = new ArrayList<>();
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(E)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(C, E)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, C)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(B, D)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, B)
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

    State testFinalState = new State(initPreds);

    Controller controller = new Controller(
        3,
        Lists.newArrayList(A,B,C,D,F,E),
        testInitialState,
        testFinalState);

    controller.run();
  }

  public void testMain4(){
    List<Predicate> statePreds = new ArrayList<>();
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(E)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(B)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(D, E)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, C)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(D)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(B)
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
            Lists.newArrayList((Structure) new Column(3))
        ));

    State testInitialState = new State(statePreds);

    List<Predicate> initPreds = new ArrayList<>();
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(E)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, E)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(B, D)
        ));
    initPreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(C, B)
        ));
    initPreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(C)
        ));
    initPreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
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

    State testFinalState = new State(initPreds);

    Controller controller = new Controller(
        3,
        Lists.newArrayList(B,C,D,F,E),
        testInitialState,
        testFinalState);

    controller.run();
  }


}