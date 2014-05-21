package org.rapidpm.demo.javamagazin.article005;

/**
 * Created by Sven Ruppert on 19.05.2014.
 */
public abstract class TimeCounterExecutor {

   public abstract void doIT();


  public void countMS(){
    long start = System.nanoTime();
    doIT();
    long stop = System.nanoTime();

    System.out.println("[ms] = " + (stop-start)/1000/1000);

  }



}
