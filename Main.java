package CoreJavaLesson2Task2;

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

        Long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> recruit = persons.stream()
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 28)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        List<Person> seniors = persons.stream()
                .filter(person -> person.getAge() > 17)
                .filter(person -> (person.getAge() < 66 && person.getSex() == Sex.MAN) || (person.getAge() < 61 && person.getSex() == Sex.WOMAN))
                .filter(person -> person.getEducation() == Education.HUGHER)
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
    }
}
