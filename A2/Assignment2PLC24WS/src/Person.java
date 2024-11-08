class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

public static void temp(){
    


    List<Person> personList = new ArrayList<>();
    personList.add(new Person("Alice", 23));
    personList.add(new Person("Bob", 30));
    personList.add(new Person("Charlie", 27));




}

public static void printPersons(List<Person> personList, Predicate<Person> tester) {
    for (Person p : personList) {
        if (tester.test(p)) { // Executes the lambda expression
            System.out.println(p.getName());
        }
    }
}

