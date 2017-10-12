/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

public class Column extends Structure{

  private final int columnNumber;

  public Column(int columnNumber) {
    super(STRUCTURE_TYPE.COLUMN);
    this.columnNumber = columnNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if (o == null || getClass() != o.getClass()) { return false; }

    Column column = (Column) o;

    return columnNumber == column.columnNumber;
  }

  @Override
  public int hashCode() {
    return columnNumber;
  }
}
