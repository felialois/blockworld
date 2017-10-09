/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import java.util.List;

public class Predicate {

  public enum TYPE {
    ON_TABLE,
    ON,
    CLEAR,
    EMPTY_ARM,
    HOLDING,
    USED_COLS_NUM,
    HEAVIER,
    LIGHT_BLOCK
  }

  // Type of predicate
  private final TYPE type;
  // Parameters
  private final List<String> params;

  public Predicate(TYPE type, List<String> params) {
    this.type = type;
    this.params = params;
  }

  public TYPE getType() {
    return type;
  }

  public List<String> getParams() {
    return params;
  }
}
