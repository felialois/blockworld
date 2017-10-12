/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

public class Structure {

  private STRUCTURE_TYPE type;

  public Structure(STRUCTURE_TYPE type) {
    this.type = type;
  }

  public STRUCTURE_TYPE getType() {
    return type;
  }

  public enum STRUCTURE_TYPE{
    ARM, BLOCK, COLUMN
  }

}
