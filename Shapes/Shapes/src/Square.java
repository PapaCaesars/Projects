public class Square extends Rectangles{
    public Square(int l){
        super(l,l);
    }
    public String toString(){
        return "Square(" + super.length + ")";
    }
    public double area(){
        return super.length * super.length;
    }
    public double perimeter(){
        return super.length * 4;
    }
}

