package org.rapidpm.demo.javamagazin.article005;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Sven Ruppert on 15.05.2014.
 */
public class Ex03Test {

  @Test
  public void testBufferedReader() throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("DemoFile.txt"));
    String line = null;
    while((line = br.readLine()) != null){
      if(line.contains("zweite")){
        System.out.println(line);
      }
    }

    br.close();


//    new BufferedReader(new FileReader("DemoFile.txt"))
//        .lines()
//        .forEach(System.out::println);

    new BufferedReader(new FileReader("DemoFile.txt"))
        .lines()
        .filter(l->l.contains("zweite"))
        .findAny()
        .ifPresent(System.out::print);
  }
}
