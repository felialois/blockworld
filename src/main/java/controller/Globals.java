/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package controller;

import com.google.common.collect.Lists;
import java.util.List;
import structures.Arm;
import structures.Arm.ARM_DIRECTION;
import structures.Operation;
import structures.Predicate;
import structures.Structure;

public class Globals {

  public static Arm left = new Arm(ARM_DIRECTION.LEFT);
  public static Arm right = new Arm(ARM_DIRECTION.RIGHT);
  public static Arm anyArm = new Arm(ARM_DIRECTION.ANY);




}
