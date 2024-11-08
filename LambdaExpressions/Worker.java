package LambdaExpressions;

public class Worker extends Person {
    private double salary;

    public Worker(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Worker - " + super.toString() + ", Salary: " + salary;
    }
}
