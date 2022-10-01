public class Circle implements Shape{
    private int radius;
    public Circle(int r){
        radius = r;
    }
    public double perimeter(){
        return 2 * Math.PI * radius;
    }
    public String toString(){
        return "Circle(" + radius +")";
    }
    public double area(){
        return Math.PI * Math.pow(radius,2);
    }
}

