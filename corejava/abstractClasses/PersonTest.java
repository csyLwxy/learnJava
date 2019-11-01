package abstractClasses;

public class PersonTest{
    public static void main(String[] args){
        Person[] people = new Person[2];

        // fill the people array with Stuent and Employee objects
        people[0] = new Employee("Harry Hacker", 50000, 1998, 5, 2);
        people[1] = new Student("Wang Ge", "math");

        // print out names and descriptions of all Person objects
        for(Person p : people){
            System.out.println(p.getName() + "," + p.getDescription());
        }
    }
}