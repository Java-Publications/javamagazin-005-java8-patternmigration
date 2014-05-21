package org.rapidpm.demo.javamagazin.article005;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.*;
import static java.util.stream.Collectors.toList;


/**
 * Created by Sven Ruppert on 19.05.2014.
 */
public class Ex06Test {

  private static final long ANZAHL_KURVEN = 1000;   //Anzahl paralleler Pakete
  private static final long ANZAHL_MESSWERTE = 100;   //Anzahl paralleler Pakete
  public static final ExecutorService FIXED_THREAD_POOL = Executors.newFixedThreadPool(40);

  @Test
  public void testParallel() throws Exception {
    new TimeCounterExecutor() {
      @Override
      public void doIT() {
//    Function<List<Integer>, CompletableFuture<List<Double>>> interpolate = (v) -> {
//      CompletableFuture<List<Double>> async = (CompletableFuture<List<Double>>) CompletableFuture.supplyAsync(new Supplier<List<Double>>() {
//        @Override
//        public List<Double> get() {
//          return new WorkLoadGenerator().generate(v);
//        }
//      });
//      return async;
//    };
//    Function<List<Integer>, CompletableFuture<List<Double>>> interpolate = (v) -> {
//      CompletableFuture<List<Double>> async = CompletableFuture.supplyAsync(() -> new WorkLoadGenerator().generate(v));
//      return async;
//    };
//    Function<List<Integer>, CompletableFuture<List<Double>>> interpolate = (v) -> {
//      return CompletableFuture.supplyAsync(() -> new WorkLoadGenerator().generate(v));
//    };
//    Function<List<Integer>, CompletableFuture<List<Double>>> interpolate = (v) -> CompletableFuture.supplyAsync(() -> new WorkLoadGenerator().generate(v));



//    generateDemoValueMatrix().stream()
//        .map(interpolate)
//        .collect(Collectors.toList());
//    List<CompletableFuture<List<Double>>> collect = generateDemoValueMatrix().stream()
//        .map(interpolate)
//        .collect(toList());

//    List<List<Double>> list = generateDemoValueMatrix().stream()
//        .map(interpolate)
//        .map(async -> {
//          try {
//            return async.get();
//          } catch (InterruptedException
//              | ExecutionException e) {
//            e.printStackTrace();
//          }
//          return Collections.<Double>emptyList();
//        });
//        List<List<Double>> list = generateDemoValueMatrix().stream()
//            .map(interpolate)
//            .map(async -> {
//              try {
//                return async.get();
//              } catch (InterruptedException
//                  | ExecutionException e) {
//                e.printStackTrace();
//              }
//              return Collections.<Double>emptyList();
//            }).collect(toList());


        List<List<Double>> list = generateDemoValueMatrix().parallelStream()
            .map(interpolate)
            .map(waitAndGet)
            .collect(toList());

        for (final List<Double> doubles : list) {
          System.out.println(String.valueOf(doubles.size()));
        }
      }

//      private List<Double> waitAndGet(CompletableFuture<List<Double>> async) {
//        try {
//          return async.get();
//        } catch (InterruptedException
//            | ExecutionException e) {
//          e.printStackTrace();
//        }
//        return Collections.<Double>emptyList();
//      }
    }.countMS();
  }

  Function<List<Integer>, CompletableFuture<List<Double>>> interpolate
      = (v) -> supplyAsync(() -> new WorkLoadGenerator().generate(v), FIXED_THREAD_POOL);

  Function<CompletableFuture<List<Double>>, List<Double>> waitAndGet = async ->{
    try {
      return async.get();
    } catch (InterruptedException
        | ExecutionException e) {
      e.printStackTrace();
    }
    return Collections.<Double>emptyList();
  };


//  public static class CompletableFutureFunction<T>
//      implements Function<CompletableFuture<List<T>>, List<T>>{
//    public List<T> apply(CompletableFuture<List<T>> async) {
//      try {
//        return async.get();
//      } catch (InterruptedException
//          | ExecutionException e) {
//        e.printStackTrace();
//      }
//      return Collections.<T>emptyList();
//    }
//  public static interface CompletableFutureFunction
//      extends Function<CompletableFuture<List<Double>>, List<Double>>{
//
//    @Override
//    public default List<Double> apply(CompletableFuture<List<Double>> async) {
//      try {
//        return async.get();
//      } catch (InterruptedException
//          | ExecutionException e) {
//        e.printStackTrace();
//      }
//      return Collections.<Double>emptyList();
//    }
//  }
//



  @Test
  public void testSeriell() throws Exception {
    new TimeCounterExecutor() {
      @Override
      public void doIT() {
        List<List<Integer>> values = generateDemoValueMatrix();
        List<List<Double>> resultMatrix = new ArrayList<>();
        for (final List<Integer> value : values) {
          List<Double> generate = new WorkLoadGenerator().generate(value);
          resultMatrix.add(generate);
        }
        for (final List<Double> doubles : resultMatrix) {
          System.out.println(String.valueOf(doubles.size()));
        }
      }
    }.countMS();
  }

  public List<List<Integer>> generateDemoValueMatrix() {
    return Stream
        .generate(()-> new Random()
            .ints(0, 100)
            .limit(ANZAHL_MESSWERTE)
            .boxed().collect(toList()))
        .limit(ANZAHL_KURVEN)
        .collect(toList());
  }

}
