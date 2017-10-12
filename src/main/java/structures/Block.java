/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

package structures;

public class Block extends Structure {

  public Block(String name, int weight) {
    super(STRUCTURE_TYPE.BLOCK);
    this.name = name;
    this.weight = weight;
  }

  // Name of the block
  private final String name;

  //Weight of the block
  private final int weight;

  public String getName() {
    return name;
  }

  public int getWeight() {
    return weight;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) { return true; }
    if ((o == null) || (getClass() != o.getClass())) { return false; }

    Block block = (Block) o;

    if (weight != block.weight) { return false; }
    return (name != null) ? name.equals(block.name) : (block.name == null);
  }

  @Override
  public int hashCode() {
    int result = (name != null) ? name.hashCode() : 0;
    result = (31 * result) + weight;
    return result;
  }
}
