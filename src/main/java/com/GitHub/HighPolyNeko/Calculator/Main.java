package com.GitHub.HighPolyNeko.Calculator;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);

        // вариант обработки 1
        try {
            // происходит деление на 0
            int c = calc.devide1.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        // вариант обработки 2
        Integer c = calc.devide2.apply(a, b);
        calc.println.accept(c);

        // вариант обработки 3
        Optional<Integer> c2 = calc.devide3.apply(Optional.of(a), Optional.of(b));
        calc.println.accept(c);

        // мне не нравятся оба этих варианта
        // тк в первом варианте ошибку обрабатывает тот кто пользуется калькулятором,
        // а во втором пользователю приходится менять тип переменной
        // полазив в интернете нашел реализацию через Optional<Integer>,
        // но в этом решении таже проблема что и во втором примере
        // как быть? что лучше использовать? может есть способ лучше который я не нашел?
    }
}
