package inheritence;

/**
 * This program demonstrates inheritance
 */
public class ManagerTest{
    public static void main(String[] args) {
        // construct a Manager object
        Manager boss = new Manager("wxy", 80000, 1872, 7, 9);
        boss.setBonus(5000);

        Employee[] staff = new Employee[3];

        // fill the staff array with Manager and Employee objects

        staff[0] = boss;
        staff[1] = new Employee("HArry HAcker", 50000, 1899, 5, 4);
        staff[2] = new Employee("Tom Tester", 45000, 1900, 4, 6);

        // print out information about all Employee objects
        for (Employee e : staff){
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
        }
    }
}