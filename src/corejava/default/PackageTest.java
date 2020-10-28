package corejava.test;

import corejava.com.wxy.corejava.Employee;
// the Employee class is defined in that packag

import static java.lang.System.*;

public class PackageTest{
    public static void main(String[] args) {
        Employee harry = new Employee("Harry HAcker", 50000, 1980, 10, 1);

        harry.raiseSalary(5);

        // because of the import statement, we don't have to use System.out
        out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
    }
}