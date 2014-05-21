package org.rapidpm.demo.javamagazin.article005;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by Sven Ruppert on 14.05.2014.
 */
public class Ex02Test {


  @Test
  public void testFor01() throws Exception {
    List<String> demoValues = DemoValuesGenerator.createDemoValues();

    for (final String demoValue : demoValues) {
      String[] split = demoValue.split("_");
      Integer integer = Integer.valueOf(split[1]);
      System.out.println("integer = " + integer);
    }

    demoValues.forEach(v->{
      String[] split = v.split("_");
      Integer integer = Integer.valueOf(split[1]);
      System.out.println("integer = " + integer);
    });
  }

  @Test
  public void testFor02() throws Exception {
    List<String> demoValues = DemoValuesGenerator.createDemoValues();

    List<Integer> gerade = new ArrayList<>();
    List<Integer> unGerade = new ArrayList<>();

    for (final String demoValue : demoValues) {
      String[] split = demoValue.split("_");
      Integer integer = Integer.valueOf(split[1]);

      if(integer.equals(0)){
        System.out.println("split un-gerade = " + integer);
        unGerade.add(integer);
      } else if((integer % 2) == 0 ){
        System.out.println("split gerade = " + integer);
        gerade.add(integer);
      } else {
        System.out.println("split un-gerade = " + integer);
        unGerade.add(integer);
      }
    }
    System.out.println("unGerade = " + unGerade);
    System.out.println("gerade = " + gerade);

    Pattern compile = Pattern.compile("_");

    demoValues.forEach(v->{
//      compile.splitAsStream(v)
      String[] split = v.split("_");
      Integer integer = Integer.valueOf(split[1]);

      if(integer.equals(0)){
        System.out.println("split un-gerade = " + integer);
        unGerade.add(integer);
      } else if((integer % 2) == 0 ){
        System.out.println("split gerade = " + integer);
        gerade.add(integer);
      } else {
        System.out.println("split un-gerade = " + integer);
        unGerade.add(integer);
      }

    });




  }
}
