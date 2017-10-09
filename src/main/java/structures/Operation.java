/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

import java.util.List;

public class Operation {

  private final List<Predicate> preconditions;
  private final List<Predicate> add;
  private final List<Predicate> delete;

  public Operation(List<Predicate> preconditions, List<Predicate> add, List<Predicate> delete) {
    this.preconditions = preconditions;
    this.add = add;
    this.delete = delete;
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
}
