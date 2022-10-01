public class Rectangles implements Shape, Printable {
    protected int length;
    protected int width;
    public Rectangles(int l, int w){
        length = l;
        width = w;
    }
    public String toString(){
        return "Rectangle" + "(" + length + "x" + width +")";
    }
    public double area(){
        return length * width;
    }
    public double perimeter(){
        return (length * 2) + (width * 2);
    }
    public void print(){
        for(int i = 0; i < width; i++){
            System.out.print("*");
            System.out.print(" ");
        }
        System.out.println("");
        for (int j = 0; j< length-2; j++){
            for (int i = 0; i < width; i++){
                if(i == 0){
                    System.out.print("*");
                }
                else if(i == width-1){
                    System.out.print(" ");
                    System.out.print("*");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
        for(int i = 0; i < width; i++){
            System.out.print("*");
            System.out.print(" ");
        }
        System.out.println();
    }
}


