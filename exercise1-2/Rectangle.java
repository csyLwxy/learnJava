public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea(){
        return height * width;
    }
}