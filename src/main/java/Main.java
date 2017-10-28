/*
 * Copyright (C) year Singular Me LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 * Written by felipe
 */

import controller.Controller;

public class Main {

  public static void main(String[] args) {

    Controller controller = null;
    try {
      controller = new Controller(args[0],Integer.parseInt(args[1]));
    } catch (Exception e) {
      System.out.println("Error reading file");
      e.printStackTrace();
    }

    controller.run();
  }

}
