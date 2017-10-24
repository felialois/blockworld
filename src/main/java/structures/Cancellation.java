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

  public enum REASON {
    CONFLICTING_PREDICATES,
    BREAKING_RULE
  }

  public Cancellation(String description, REASON reason) {
    this.description = description;
    this.reason = reason;
  }

  @Override
  public String toString() {
    return reason + " : " + description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if ((o == null) || (getClass() != o.getClass())) { return false; }

    Cancellation that = (Cancellation) o;

    if ((description != null) ? !description.equals(that.description) : (that.description != null)) {
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
