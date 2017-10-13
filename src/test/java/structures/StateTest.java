/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import controller.Globals;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import structures.Predicate.TYPE;

public class StateTest extends TestCase {

  private State testState;
  private final Structure A = new Block("A", 1);
  private final Structure B = new Block("B", 2);
  private final Structure C = new Block("C", 2);
  private final Structure D = new Block("D", 3);
  private final Structure F = new Block("F", 1);

  @Override
  public void setUp() throws Exception {
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

    testState = new State(statePreds);
    super.setUp();
  }

  public void testGetUsedColumns() throws Exception {
  }

  public void testIsBlockClear() throws Exception {
    testState.listClearBlocks();
    boolean actual = testState.isBlockClear((Block) F);
    boolean actualFalse = testState.isBlockClear((Block) A);

    assertEquals("The block should be clear",true,actual);
    assertEquals("The block should not be clear",false,actualFalse);

  }

  public void testGetClearBlocks() throws Exception {
    testState.listClearBlocks();
    List<Block> actual = testState.getClearBlocks();

    List<Block> expected = new ArrayList<>();
    expected.add((Block) F);
    expected.add((Block) C);
    System.out.println(actual);
    assertEquals("The clear blocks should be the correct ones", Sets.newHashSet(expected),Sets.newHashSet(actual));

  }

  public void testIsBlockOnTable() throws Exception{
    boolean actual = testState.isBlockOnTable((Block) D);
    boolean actualFalse = testState.isBlockOnTable((Block) A);

    assertEquals("The block should be clear",true,actual);
    assertEquals("The block should not be clear",false,actualFalse);
  }

  public void testCreateState() throws Exception {
  }

  public void testGetOperations() throws Exception {
  }

}