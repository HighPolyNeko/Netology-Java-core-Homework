package com.GitHub.HighPolyNeko.PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        System.out.println("Найти количество несовершеннолетних (т.е. людей младше 18 лет). " + persons.stream().filter(person -> person.getAge() < 18).count());
        System.out.println("Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет). " +
                persons.stream()
                        .filter(person -> (person.getAge() > 18) && (person.getAge() < 27))
                        .filter(person -> person.getSex() == Sex.MAN)
                        .map(Person::getFamily)
                        .toList()
        );
        System.out.println("Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке. " +
                persons.stream()
                        .filter(person -> (person.getAge() > 18) && (person.getAge() < (person.getSex() == Sex.MAN ? 65 : 60)) )
                        .filter(person -> person.getEducation() == Education.HIGHER)
                        .sorted(Comparator.comparing(Person::getFamily))
                        .toList()
        );
    }
}
