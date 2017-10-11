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

}
