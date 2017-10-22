/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package controller;

import java.util.ArrayList;
import java.util.List;
import structures.Arm;
import structures.Arm.ARM_DIRECTION;
import structures.Block;
import structures.Cancelation;
import structures.Column;

public class Globals {

  public static Arm left = new Arm(ARM_DIRECTION.LEFT);
  public static Arm right = new Arm(ARM_DIRECTION.RIGHT);
  private static final Column anyColumns = new Column(3);

  private static final Block blockX = new Block("x", 1);
  private static final Block blockY = new Block("y", 1);

  private static int columnNumber = 3;

  public static int getColumnNumber() {
    return columnNumber;
  }

  public static void setColumnNumber(int columnNumber) {
    Globals.columnNumber = columnNumber;
  }

  public static final List<Cancelation> cancelledStates = new ArrayList<>();


}
