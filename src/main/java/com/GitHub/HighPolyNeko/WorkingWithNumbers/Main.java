package com.GitHub.HighPolyNeko.WorkingWithNumbers;

import java.util.*;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);


        List<Integer> filtredIntList = sort(intList);
        System.out.println(filtredIntList);
        StreamMain.main();
    }

    public static List<Integer> sort(List<Integer> intList) {
        Supplier<List<Integer>> filteredIntListSupplier = () -> {
            List<Integer> result = new ArrayList<>();
            for (Integer integer : intList) {
                if (integer > 0) {
                    result.add(integer);
                }
            }
            return result;
        };

        Supplier<List<Integer>> filteredIntListSupplier2 = () -> {
            List<Integer> result = new ArrayList<>();
            for (Integer integer : filteredIntListSupplier.get()) {
                if (integer % 2 ==0) {
                    result.add(integer);
                }
            }
            return result;
        };

        Supplier<List<Integer>> filteredIntListSupplier3 = () -> {
            List<Integer> result = filteredIntListSupplier2.get();

            Collections.sort(result);

            return result;
        };

        return filteredIntListSupplier3.get();
    }

}
