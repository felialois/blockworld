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

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    Arm arm = (Arm) o;

    return armDirection == arm.armDirection;
  }

  @Override
  public int hashCode() {
    return armDirection != null ? armDirection.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Arm : " + armDirection;
  }
}
