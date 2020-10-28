package corejava.abstractClasses;

public class Student extends Person{

    private String majoy;

    public Student(String name, String majoy) {
        super(name);
        this.majoy = majoy;
    }

    @Override
    public String getDescription() {
        return "a student majoring in" + majoy;
    }

}