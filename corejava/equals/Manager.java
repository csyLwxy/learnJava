package corejava.equals;

public class Manager extends Employee{
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day){
        super(name, salary, year, month, day);
        bonus = 0;
    }

    @Override
    public double getSalary() {
        // TODO Auto-generated method stub
        return super.getSalary() + bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public boolean equals(Object otherObject){
        if(!super.equals(otherObject)){
            return false;
        }
            Manager other = (Manager) otherObject;
            // super.equals checked that this and other belong to the same class
            return bonus == other.bonus;
    }

    public int hashCode(){
        return super.hashCode() + 17 * Double.hashCode(bonus);
    }

    public String toString(){
        return super.toString() + "[bonus=" + bonus + "]";
    }
}