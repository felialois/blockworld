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
  private final Block A = new Block("A", 1);
  private final Block B = new Block("B", 2);
  private final Block C = new Block("C", 2);
  private final Block D = new Block("D", 3);
  private final Block F = new Block("F", 1);

  @Override
  public void setUp() throws Exception {
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

    testState = new State(statePreds);
    super.setUp();
  }

  public void testGetUsedColumns() throws Exception {
    int actual = testState.getUsedColumns();
    assertEquals("The number of used columns should be the correct one", 2, actual);
  }

  public void testIsBlockClear() throws Exception {
    boolean actual = testState.isBlockClear((Block) F);
    boolean actualFalse = testState.isBlockClear((Block) A);

    assertEquals("The block should be clear", true, actual);
    assertEquals("The block should not be clear", false, actualFalse);

  }

  public void testGetClearBlocks() throws Exception {
    List<Block> actual = testState.getClearBlocks();

    List<Block> expected = new ArrayList<>();
    expected.add((Block) F);
    expected.add((Block) C);
    System.out.println(actual);
    assertEquals("The clear blocks should be the correct ones", Sets.newHashSet(expected), Sets
        .newHashSet(actual));

  }

  public void testIsBlockOnTable() throws Exception {
    boolean actual = testState.isBlockOnTable((Block) D);
    boolean actualFalse = testState.isBlockOnTable((Block) A);

    assertEquals("The block should be clear", true, actual);
    assertEquals("The block should not be clear", false, actualFalse);
  }

  public void testCreateState() throws Exception {

    Operation operationStackCBR = Operation.makeStack(C, B, Globals.right);
    State actual = State.createState(testState, operationStackCBR, new ArrayList<Operation>());

    List<Predicate> expectedStatePreds = new ArrayList<>();
    expectedStatePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(B)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, D)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, A)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(C)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.left)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.HOLDING,
            Lists.newArrayList((Structure) C, Globals.right)
        )
    );
    expectedStatePreds.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(B)
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(2))
        ));
    expectedStatePreds.add(
        new Predicate(
            TYPE.HEAVIER,
            Lists.<Structure>newArrayList(B, C)
        ));

    State expected = new State(operationStackCBR, expectedStatePreds, new ArrayList<Operation>());
    assertEquals("The state should be the correct one",expected,actual);


  }

  public void testGetOperations() throws Exception {
    Predicate onCB = new Predicate(
        TYPE.ON,
        Lists.<Structure>newArrayList(C, B)
    );
    List<Operation> operations = State.getOperations(onCB, testState);

    System.out.println(operations);

    Operation operationStackCBR = Operation.makeStack(C, B, Globals.right);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(B)
        ));
    predicates.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    predicates.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, D)
        ));
    predicates.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, A)
        ));
    predicates.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
        ));
    predicates.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(C)
        ));
    predicates.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.left)
        ));
    predicates.add(
        new Predicate(
            TYPE.HOLDING,
            Lists.newArrayList((Structure) C)
        )
    );
    predicates.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(B)
        ));
    predicates.add(
        new Predicate(
            TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(2))
        ));

    State newState = new State(operationStackCBR, predicates, new ArrayList<Operation>());

    Predicate holdingC = new Predicate(
        TYPE.HOLDING,
        Lists.<Structure>newArrayList(C, Globals.right)
    );
    List<Operation> ops2 = State.getOperations(holdingC, newState);
    System.out.println(ops2);

  }

  public void testCreateNextLevelStates() throws Exception {
    Operation operationStackCBR = Operation.makeStack(C, B, Globals.right);
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(B)
        ));
    predicates.add(
        new Predicate(
            TYPE.ON_TABLE,
            Lists.<Structure>newArrayList(D)
        ));
    predicates.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(A, D)
        ));
    predicates.add(
        new Predicate(
            TYPE.ON,
            Lists.<Structure>newArrayList(F, A)
        ));
    predicates.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(F)
        ));
    predicates.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(C)
        ));
    predicates.add(
        new Predicate(
            TYPE.EMPTY_ARM,
            Lists.newArrayList((Structure) Globals.left)
        ));
    predicates.add(
        new Predicate(
            TYPE.HOLDING,
            Lists.newArrayList((Structure) C, Globals.right)
        )
    );
    predicates.add(
        new Predicate(
            TYPE.CLEAR,
            Lists.<Structure>newArrayList(B)
        ));
    predicates.add(
        new Predicate(
            TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(2))
        ));

    State newState = new State(operationStackCBR, predicates, new ArrayList<Operation>());

    List<State> nextLevelStates = State.createNextLevelStates(newState);
    System.out.println(nextLevelStates);
    List<State> nextLevelStates2 = State.createNextLevelStates(nextLevelStates.get(1));
    System.out.println(nextLevelStates2);

  }



}