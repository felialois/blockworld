/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

public class Cancellation {

  private final String description;
  private final REASON reason;
  private final State state;

  public enum REASON {
    CONFLICTING_PREDICATES,
    INCOMPATIBLE_PREDICATE_BLOCK_NOT_CLEAR,
    INCOMPATIBLE_PREDICATE_BLOCK_TOO_HEAVY_FOR_ARM,
    INCOMPATIBLE_PREDICATE_ARM_IN_USE,
    INCOMPATIBLE_PREDICATE_BLOCK_TOO_HEAVY_FOR_STACKING,
    INCOMPATIBLE_PREDICATE_ALL_COLUMNS_IN_USE,
    PREVIOUSLY_VISITED_STATE,
  }

  public Cancellation(String description, REASON reason, State state) {
    this.description = description;
    this.reason = reason;
    this.state = state;
  }

  @Override
  public String toString() {
    return "\n ----- \n"+reason + " : " + description + " -- "
        + (state == null ? " State canceled before creation" : state.toString())+ '\n';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if ((o == null) || (getClass() != o.getClass())) { return false; }

    Cancellation that = (Cancellation) o;

    if ((description != null) ? !description.equals(that.description) : (that.description !=
        null)) {
      return false;
    }
    return reason == that.reason;
  }

  @Override
  public int hashCode() {
    int result = (description != null) ? description.hashCode() : 0;
    result = (31 * result) + ((reason != null) ? reason.hashCode() : 0);
    return result;
  }
}
