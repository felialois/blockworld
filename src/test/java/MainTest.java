/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

import com.google.common.collect.Lists;
import controller.Globals;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import structures.Arm;
import structures.Arm.ARM_DIRECTION;
import structures.Block;
import structures.Operation;
import structures.Predicate;
import structures.Predicate.TYPE;
import structures.Structure;

public class MainTest extends TestCase {
  public void testMain() throws Exception {


    Block A = new Block("A",1);
    Block blockX = new Block("x",1);
    Block blockY = new Block("y",1);
    Block B = new Block("B",1);
    Block C = new Block("C",2);
    Block D = new Block("D",3);


    List<Predicate> preconditions = Lists.newArrayList(
        new Predicate(TYPE.HOLDING,Lists.newArrayList(blockX, Globals.anyArm)),
        new Predicate(TYPE.CLEAR,Lists.newArrayList((Structure)blockY)),
        new Predicate(TYPE.HEAVIER,Lists.newArrayList((Structure)blockY,blockX))
    );
    List<Predicate> add = Lists.newArrayList(
        new Predicate(TYPE.ON,Lists.newArrayList((Structure)blockX,blockY)),
        new Predicate(TYPE.EMPTY_ARM,Lists.newArrayList((Structure)Globals.anyArm))
    );
    List<Predicate> delete = Lists.newArrayList(
        new Predicate(TYPE.HOLDING,Lists.newArrayList(blockX, Globals.anyArm)),
        new Predicate(TYPE.CLEAR,Lists.newArrayList((Structure)blockY))
        );
    Operation stack = new Operation(preconditions,add,delete);


    List<Predicate> preconditionsU = Lists.newArrayList(
        new Predicate(TYPE.ON,Lists.newArrayList((Structure)blockX, blockY)),
        new Predicate(TYPE.CLEAR,Lists.newArrayList((Structure)blockX)),
        new Predicate(TYPE.EMPTY_ARM,Lists.newArrayList((Structure)Globals.anyArm))
    );
    List<Predicate> addU = Lists.newArrayList(
        new Predicate(TYPE.HOLDING,Lists.newArrayList((Structure)blockX,Globals.anyArm)),
        new Predicate(TYPE.CLEAR,Lists.newArrayList((Structure)blockY))
    );
    List<Predicate> deleteU = Lists.newArrayList(
        new Predicate(TYPE.ON,Lists.newArrayList((Structure)blockX, blockY)),
        new Predicate(TYPE.EMPTY_ARM,Lists.newArrayList((Structure)Globals.anyArm))
    );
    Operation unstack = new Operation(preconditionsU,addU,deleteU);

  }

}