package LambdaExpressions;

import java.util.ArrayList;
import java.util.List;

public class LambdaExpressions {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Alice", 28));
        personList.add(new Student("Bob", 22));
        personList.add(new Worker("Charlie", 35, 2500.0));
        personList.add(new Worker("Diana", 28, 1800.0));
        personList.add(new Student("Eve", 27));
        personList.add(new Person("Frank", 40));

        System.out.println("Persons younger than 18:");
        personList.stream().filter(p->p.getAge() < 40).forEach(System.out::println);

        System.out.println("\nStudents with grade higher than 3.5:");
        personList.stream().filter(p-> p instanceof Student).filter(p -> ((Student) p).getGrade()<3).forEach(System.out::println);

        System.out.println("\nPersons with names starting with 'A':");
        personList.stream().filter(p ->p.getName().startsWith("A")).forEach(System.out::println);

        System.out.println("\nWorkers with salary between 1500 and 3000:");
        // Implement stream operation here

        System.out.println("\nStudents under 25 with grade 4.0 or higher:");
        // Implement stream operation here

        System.out.println("\nWorkers with position not 'Intern' and salary over 1000:");
        // Implement stream operation here

        System.out.println("\nPersons with names longer than 5 letters:");
        // Implement stream operation here

        System.out.println("\nPersons 30 years old or older:");
        // Implement stream operation here

        System.out.println("\nStudents in their final year:");
        // Implement stream operation here

        System.out.println("\nWorkers with more than 5 years at the company:");
        // Implement stream operation here

    }

    private static void filterAndPrint(List<Person> list, FilterCondition condition) {
        for (Person person : list) {
            if (condition.test(person)) {
                System.out.println(person);
            }
        }
    }

    @FunctionalInterface
    interface FilterCondition {
        boolean test(Person p);
    }
}

