package com.GitHub.HighPolyNeko.Calculator;

import java.util.*;
import java.util.function.*;

public class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;

    // в данном месте выкидывается исключение тк происходит деление на 0
    BinaryOperator<Integer> devide1 = (x, y) -> {
        if (y == 0) {
            // выкидываю свою ошибку чтоб перевести ее на русский
            throw new ArithmeticException("Деление на 0!");
        }
        return x / y;
    };

    // в данном месте выкидывается исключение тк происходит деление на 0
    BinaryOperator<Integer> devide2 = (x, y) -> {
        if (y == 0) {

            // вариант 2 (но переменная с должна быть не int а Integer)
            return null;
        }
        return x / y;
    };

    BinaryOperator<Optional<Integer>> devide3 = (x, y) -> {
        if (x.isEmpty() || y.isEmpty() || y.get() == 0) {
            return Optional.empty();
        }
        return Optional.of(x.get() / y.get());
    };


    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;


    Predicate<Integer> isPositive = x -> x > 0;

    Consumer<Integer> println = System.out::println;
}
