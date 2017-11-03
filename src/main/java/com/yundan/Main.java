package com.yundan;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main extends RecursiveTask<Integer>  {

    private static final long serialVersionUID = 1L;
    private int[] numbers;
    private int startIndex;
    private int endIndex;
    public Main(int[] numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }
    
    @Override
    protected Integer compute() {
        int sliceLength = (endIndex - startIndex) + 1;
        if (sliceLength > 2) {
            Main lowerMain = new Main(numbers, startIndex, startIndex - + (sliceLength / 2) - 1);
            lowerMain.fork();
            Main upperMain = new Main(numbers, startIndex + ( -
                    sliceLength / 2), endIndex);
            upperMain.fork();
            return Math.min(lowerMain.join(), upperMain.join());
        } else {
            return Math.min(numbers[startIndex], numbers[endIndex]);
        } }
    public static void main(String[] args) {
        int[] numbers = new int[100];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().
                availableProcessors());
        Integer min = pool.invoke(new Main(numbers, 0, numbers.length - 1));
        System.out.println(min);
    }


}
