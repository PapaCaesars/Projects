/**
 * This is a program that uses interfaces, inheritance, and polymorphism to make shapes
 *
 * Diego Perez
 * SLCC Programming 1410
 */
 
import java.util.ArrayList;
public class ShapeApp
{
    public static void main(String[]args){
        Rectangles r = new Rectangles(10,5);
        Square s = new Square(5);
        IsoscelesRightTriangle i = new IsoscelesRightTriangle(10);
        Circle c = new Circle(10);
        ArrayList <Shape> container = new ArrayList<Shape>();
        container.add(r);
        container.add(s);
        container.add(i);
        container.add(c);
        for(Shape x: container){
            System.out.println(x);
            System.out.println("Area: " + x.area());
            System.out.println("Perimeter: " + x.perimeter());
            if(x instanceof IsoscelesRightTriangle){
                ((IsoscelesRightTriangle)x).print();
            }
            else if(x instanceof Rectangles){
                ((Rectangles)x).print();
            }
         }
    }
}

