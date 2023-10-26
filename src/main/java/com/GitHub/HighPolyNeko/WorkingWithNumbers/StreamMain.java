package com.GitHub.HighPolyNeko.WorkingWithNumbers;

import java.util.Arrays;
import java.util.List;

public class StreamMain {
    public static void main() {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        System.out.println(intList.stream()
                .filter(x -> x > 0)
                .filter(x -> x % 2 == 0)
                .sorted()
                .toList());
    }
}
