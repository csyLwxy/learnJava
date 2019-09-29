public class Circle extends Shape{
    private int radius;
    private final double pi = 3.14;

    public Circle(int r){
        radius = r;
    }

    public double getArea(){
        return pi * radius * radius;
    }
}