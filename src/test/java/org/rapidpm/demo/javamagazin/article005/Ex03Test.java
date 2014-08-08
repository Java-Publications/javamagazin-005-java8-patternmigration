package org.rapidpm.demo.javamagazin.article005;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Sven Ruppert on 15.05.2014.
 * DemoFile.txt
 *
 * Das ist die erste Zeile
 * Das ist die zweite Zeile
 * Das ist die dritte Zeile
 * Das ist die vierte Zeile
 * Das ist die zweite Zeile
 */
public class Ex03Test {

  @Test
  public void testBufferedReader01() throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("DemoFile.txt"));
    String line = null;
    while((line = br.readLine()) != null){
      if(line.contains("zweite")){
        System.out.println(line);
        break;
      }
    }
    br.close();


    new BufferedReader(new FileReader("DemoFile.txt"))
        .lines()
        .filter(l->l.contains("zweite"))
        .findAny()
        .ifPresent(System.out::print);
  }

  @Test
  public void testBufferedReader02() throws Exception {
    BufferedReader br = new BufferedReader(new FileReader("DemoFile.txt"));
    String line = null;
    while((line = br.readLine()) != null){
      if(line.contains("zweite")){
        System.out.println(line);
      }
    }
    br.close();

    new BufferedReader(new FileReader("DemoFile.txt"))
        .lines()
        .filter(l->l.contains("zweite"))
        .forEach(System.out::println);
  }
}
