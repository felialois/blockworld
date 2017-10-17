/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import com.google.common.collect.Lists;
import java.util.List;
import structures.Predicate.TYPE;

public class Operation {

  public enum OPS  {
      STACK,
      UNSTACK,
      PICKUP,
      LEAVE
  }
  private final List<Predicate> preconditions;
  private final List<Predicate> add;
  private final List<Predicate> delete;
  private final OPS name;

  public Operation(List<Predicate> preconditions, List<Predicate> add, List<Predicate> delete,
      OPS name) {
    this.preconditions = preconditions;
    this.add = add;
    this.delete = delete;
    this.name = name;
  }

  public List<Predicate> getPreconditions() {
    return preconditions;
  }

  public List<Predicate> getAdd() {
    return add;
  }

  public List<Predicate> getDelete() {
    return delete;
  }

  /**
   * Creates a stack operation
   *
   * @param blockX block to be stacked
   * @param blockY block on top of which X gets stacked
   * @param arm    arm used to stack
   *
   * @return
   */
  public static Operation makeStack(Block blockX, Block blockY, Arm arm) {
    List<Predicate> preconditions = Lists.newArrayList(
        new Predicate(TYPE.HOLDING, Lists.newArrayList(blockX, arm)),
        new Predicate(TYPE.CLEAR, Lists.newArrayList((Structure) blockY)),
        new Predicate(TYPE.HEAVIER, Lists.newArrayList((Structure) blockY, blockX))
    );
    List<Predicate> add = Lists.newArrayList(
        new Predicate(TYPE.ON, Lists.newArrayList((Structure) blockX, blockY)),
        new Predicate(TYPE.EMPTY_ARM, Lists.newArrayList((Structure) arm))
    );
    List<Predicate> delete = Lists.newArrayList(
        new Predicate(TYPE.HOLDING, Lists.newArrayList(blockX, arm)),
        new Predicate(TYPE.CLEAR, Lists.newArrayList((Structure) blockY))
    );
    return new Operation(preconditions, add, delete, OPS.STACK);
  }

  /**
   * Creates a unstack operation
   *
   * @param blockX block to be unstacked
   * @param blockY block below x
   * @param arm    arm to be used
   *
   * @return
   */
  public static Operation makeUnstack(Block blockX, Block blockY, Arm arm) {
    List<Predicate> preconditionsU = Lists.newArrayList(
        new Predicate(TYPE.ON, Lists.newArrayList((Structure) blockX, blockY)),
        new Predicate(TYPE.CLEAR, Lists.newArrayList((Structure) blockX)),
        new Predicate(TYPE.EMPTY_ARM, Lists.newArrayList((Structure) arm))
    );
    List<Predicate> addU = Lists.newArrayList(
        new Predicate(TYPE.HOLDING, Lists.newArrayList(blockX, arm)),
        new Predicate(TYPE.CLEAR, Lists.newArrayList((Structure) blockY))
    );
    List<Predicate> deleteU = Lists.newArrayList(
        new Predicate(TYPE.ON, Lists.newArrayList((Structure) blockX, blockY)),
        new Predicate(TYPE.EMPTY_ARM, Lists.newArrayList((Structure) arm))
    );
    return new Operation(preconditionsU, addU, deleteU, OPS.UNSTACK);

  }

  /**
   * Creates a pickUp operation
   *
   * @param blockX      block to be picked up
   * @param arm         arm to be used
   * @param columnsLeft amount of columns being used
   *
   * @return
   */
  public static Operation makePickUp(Block blockX, Arm arm, int columnsLeft) {
    List<Predicate> preconditionsP = Lists.newArrayList(
        new Predicate(TYPE.CLEAR, Lists.newArrayList((Structure) blockX)),
        new Predicate(TYPE.EMPTY_ARM, Lists.newArrayList((Structure) arm)),
        new Predicate(TYPE.ON_TABLE, Lists.newArrayList((Structure) blockX)),
        new Predicate(TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(columnsLeft)))
    );
    List<Predicate> addP = Lists.newArrayList(
        new Predicate(TYPE.HOLDING, Lists.newArrayList(blockX, arm)),
        new Predicate(TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(columnsLeft - 1)))
    );
    List<Predicate> deleteP = Lists.newArrayList(
        new Predicate(TYPE.ON_TABLE, Lists.newArrayList((Structure) blockX)),
        new Predicate(TYPE.EMPTY_ARM, Lists.newArrayList((Structure) arm)),
        new Predicate(TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(columnsLeft)))
    );
    return new Operation(preconditionsP, addP, deleteP, OPS.PICKUP);

  }

  /**
   * Creates a leave operation
   *
   * @param blockX      block to be left
   * @param arm         arm to be used
   * @param columnsLeft number of columns available
   *
   * @return
   */
  public static Operation makeLeave(Block blockX, Arm arm, int columnsLeft) {
    List<Predicate> preconditionsL = Lists.newArrayList(
        new Predicate(TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(columnsLeft))),
        new Predicate(TYPE.HOLDING,
            Lists.newArrayList(blockX, arm))
    );
    List<Predicate> addL = Lists.newArrayList(
        new Predicate(TYPE.ON_TABLE, Lists.newArrayList((Structure) blockX)),
        new Predicate(TYPE.EMPTY_ARM, Lists.newArrayList((Structure) arm)),
        new Predicate(TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(columnsLeft + 1)))
    );
    List<Predicate> deleteL = Lists.newArrayList(
        new Predicate(TYPE.USED_COLS_NUM,
            Lists.newArrayList((Structure) new Column(columnsLeft))),
        new Predicate(TYPE.HOLDING, Lists.newArrayList(blockX, arm))
    );
    return new Operation(preconditionsL, addL, deleteL, OPS.LEAVE);

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if ((o == null) || (getClass() != o.getClass())) { return false; }

    Operation operation = (Operation) o;

    if ((preconditions != null) ? !preconditions.equals(operation.preconditions) : (operation
        .preconditions != null)) {
      return false;
    }
    if ((add != null) ? !add.equals(operation.add) : (operation.add != null)) { return false; }
    return (delete != null) ? delete.equals(operation.delete) : (operation.delete == null);
  }

  @Override
  public int hashCode() {
    int result = (preconditions != null) ? preconditions.hashCode() : 0;
    result = (31 * result) + ((add != null) ? add.hashCode() : 0);
    result = (31 * result) + ((delete != null) ? delete.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return name.toString();
  }
}
