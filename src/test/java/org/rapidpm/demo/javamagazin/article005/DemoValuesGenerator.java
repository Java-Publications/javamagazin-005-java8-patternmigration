package org.rapidpm.demo.javamagazin.article005;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Sven Ruppert on 14.05.2014.
 */
public class DemoValuesGenerator {

  public static final Random random = new Random();
  public static final long ANZAHL_MESSWERTE = 10;
  public static final int MAX_GENERATED_INT = 100;

  public static List<String> createDemoValues(){
    return IntStream.range(0, 10)
        .boxed()
        .map(i -> "Value_" + i)
        .collect(Collectors.toList());
  }

  public static List<String> createDemoValuesWithNullElements(){

    List<String> result = new ArrayList<>();
    for(int i=0;i<10;i++){
      if(random.nextBoolean()){
        result.add(null);
      }else {
        result.add("Value_" + i);
      }
    }
    return result;
  }

  public static List<Integer> generateDemoValuesForY() {
    final Random random = new Random();
    return Stream
        .generate(() -> random.nextInt(MAX_GENERATED_INT))
        .limit(ANZAHL_MESSWERTE)
        .collect(Collectors.toList());

  }


}
