package LambdaExpressions;

public class Student extends Person {
    public Student(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Student - " + super.toString();
    }

    public int getGrade(){
        return 1;
    }
}
