package org.rapidpm.demo.javamagazin.article005;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.*;
import static org.rapidpm.demo.javamagazin.article005.DemoValuesGenerator.*;
import static org.rapidpm.demo.javamagazin.article005.DemoValuesGenerator.createDemoValuesWithNullElements;

/**
 * Created by Sven Ruppert on 16.05.2014.
 */
public class Ex04Test {

  @Test
  public void testIfNotNull() throws Exception {
    List<String> demoValues = createDemoValuesWithNullElements();

    for (final String demoValue : demoValues) {
      if (demoValue != null){
        System.out.println(demoValue);
      }
    }

    System.out.println("demoValues = " + demoValues);
    demoValues.stream()
        .filter(v -> v != null)
        .forEach(System.out::println);
  }

  @Test
  public void testMultipleFor() throws Exception {
    int dimX = 4;
    int dimY = 4;
    int dimZ = 4;

    List<List<List<Integer>>> cubicA = new ArrayList<>();
    List<List<List<Integer>>> cubicB = new ArrayList<>();

    for (int z = 0; z < dimZ; z++) {
      List<List<Integer>> yList = new ArrayList<>();
      for (int y = 0; y < dimY; y++) {
        List<Integer> xList = new ArrayList<>();
        for (int x = 0; x < dimX; x++) {
          xList.add(random.nextInt());
        }
        yList.add(xList);
      }
      cubicA.add(yList);
    }

//    return
    generate(() ->
            generate(() ->
                    random.ints().limit(dimX).boxed().collect(toList())
            ).limit(dimY).collect(toList())
//    ).limit(dimZ).collect(toList());
    ).limit(dimZ).forEach(cubicB::add);

    generate(() ->
            generate(() ->
                    random.ints().limit(dimX).boxed().collect(toList())
            ).parallel().limit(dimY).collect(toList())
    ).parallel().limit(dimZ).forEach(cubicB::add);

//    IntStream.range(0,4)
    for (List<List<Integer>> yList : cubicA) {
      for (List<Integer> xList : yList) {
        for (Integer integer : xList) {
          System.out.println("integer = " + integer);
        }
      }
    }

    cubicA.forEach(
        z -> z.forEach(
            y -> y.forEach(
                System.out::println)
        )
    );

    cubicA.parallelStream().forEach(
        z -> z.parallelStream().forEach(
            y -> y.parallelStream().forEach(
                System.out::println)
        )
    );


  }
}
