/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

import com.google.common.collect.Lists;
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
  public void testMain() throws Exception {

    Structure A = new Block("A", 1);
    Structure B = new Block("B", 2);
    Structure C = new Block("C", 2);
    Structure D = new Block("D", 3);
    Structure F = new Block("F", 1);


    List<Predicate> statePreds = new ArrayList<>();
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.newArrayList(B)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.newArrayList(D)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.newArrayList(C, B)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.newArrayList(A, D)
        ));
    statePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.newArrayList(F, A)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.newArrayList(F)
        ));
    statePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.newArrayList(C)
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

    State testState = new State(statePreds);
  }


}