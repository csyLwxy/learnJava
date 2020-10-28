package corejava.clone;

public class CloneTest{
    public static void main(String[] args) {
        try{
            Employee original = new Employee("wxy", 10000);
            original.setHireDay(2000, 1, 1);
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002, 2, 2);
            System.out.println("original=" + original);
            System.out.println("copy=" + copy);
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
    }
}