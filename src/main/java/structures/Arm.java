/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

public class Arm extends Structure{
  
  private ARM_DIRECTION armDirection;

  public Arm(ARM_DIRECTION armDirection) {
    super(STRUCTURE_TYPE.ARM);
    this.armDirection = armDirection;
  }

  public ARM_DIRECTION getArmType() {
    return armDirection;
  }

  public enum ARM_DIRECTION {
    LEFT, RIGHT
  }



}
