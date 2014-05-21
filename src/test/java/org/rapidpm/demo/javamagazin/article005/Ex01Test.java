package org.rapidpm.demo.javamagazin.article005;

import org.junit.Assert;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.rapidpm.demo.javamagazin.article005.DemoValuesGenerator.*;

/**
 * Created by Sven Ruppert on 14.05.2014.
 */
public class Ex01Test {




  /**
   * Ohne Test, sortierte Liste
   * Ziele alle values mit i < 5
   * @throws Exception
   */
  @org.junit.Test
  public void testFor001() throws Exception {
    List<String> demoValues = createDemoValues();

    for (final String demoValue : demoValues) {
      System.out.println(demoValue);
    }

    demoValues.forEach(System.out::println);

    demoValues.forEach(
        v -> System.out.println(v)
    );

//    Consumer<String> action = v -> {
//      System.out.println(v);
//    };
//    Consumer<String> action = v -> System.out.println(v);
    Consumer<String> action = System.out::println;
//    Consumer<String> action = new Consumer<String>() {
//      @Override
//      public void accept(String s) {
//        System.out.println("s = " + s);
//      }
//    };

    demoValues.forEach(action);

    demoValues.stream()
//        .peek(v->System.out.println(v))
        .forEach(v->System.out.println(v));
  }





  /**
   * Ohne Test, sortierte Liste
   * Ziele alle values mit i < 5
   * @throws Exception
   */
  @org.junit.Test
  public void testWhile001() throws Exception {
    List<String> demoValues = createDemoValues();
    List<String> resultA = new ArrayList<>();

    Iterator<String> iterator = demoValues.iterator();
    boolean valid = true;
    while(iterator.hasNext() && valid){
      String next = iterator.next();
      System.out.println("next = " + next);
      String[] split = next.split("_");
      if(Integer.valueOf(split[1]) < 5){
        resultA.add(next);
      } else{
        valid = false;
      }
    }

    //Streams
    List<String> resultB = demoValues.stream()
        .map(v -> v.split("_"))
        .filter(v -> Integer.valueOf(v[1]) < 5)
        .map(v -> v[0] + v[1])
        .collect(Collectors.toList());

    Assert.assertTrue(resultA.size() == resultB.size());
    Assert.assertTrue(resultA.retainAll(resultB));

  }

  /**
   * Mit Test, sortierte Liste
   * Ziele alle values mit i < 5
   * @throws Exception
   */
  @org.junit.Test
  public void testWhile002() throws Exception {
    List<String> demoValues = createDemoValues();
    List<String> resultA = new ArrayList<>();

    Iterator<String> iterator = demoValues.iterator();
    boolean valid = true;
    while(iterator.hasNext() && valid){
      String next = iterator.next();
      String[] split = next.split("_");
      if(split.length == 2){
        if(Integer.valueOf(split[1]) < 5){
          resultA.add(next);
        } else{
          valid = false;
        }
      }
    }

    //Streams
    List<String> resultB = demoValues.stream()
        .map(v -> v.split("_"))
        .filter(v -> v.length == 2)
        .filter(v -> Integer.valueOf(v[1]) < 5)
        .map(v -> v[0] + v[1])
        .collect(Collectors.toList());

    Assert.assertTrue(resultA.size() == resultB.size());
    Assert.assertTrue(resultA.retainAll(resultB));

  }

  /**
   * Mit Test, un-sortierte Liste
   * Ziele alle values mit i < 5
   * @throws Exception
   */
  @org.junit.Test
  public void testWhile003() throws Exception {

    List<String> demoValues = createDemoValues();
    Collections.shuffle(demoValues, random);

    List<String> resultA = new ArrayList<>();

    Iterator<String> iterator = demoValues.iterator();
    while(iterator.hasNext()){
      String next = iterator.next();
      System.out.println("next = " + next);
      String[] split = next.split("_");
      if(split.length == 2){
        if(Integer.valueOf(split[1]) < 5){
          resultA.add(next);
        }
      }
    }

    //Streams
    List<String> resultB = demoValues.stream()
        .map(v -> v.split("_"))
        .filter(v -> v.length == 2)
        .filter(v -> Integer.valueOf(v[1]) < 5)
        .map(v -> v[0] + v[1])
        .collect(Collectors.toList());

    Assert.assertTrue(resultA.size() == resultB.size());
    Assert.assertTrue(resultA.retainAll(resultB));

  }



}
