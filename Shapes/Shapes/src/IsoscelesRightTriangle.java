public class IsoscelesRightTriangle implements Shape, Printable {
    private int leg;
    public IsoscelesRightTriangle(int l){
        leg = l;
    }
    public double hypotenuse(){
         return Math.sqrt(Math.pow(leg,2) + Math.pow(leg,2));
    }
    public String toString(){
        return "IsoscelesRightTriangle(" + leg + ")";
    }
    public double perimeter(){
        return leg + leg + hypotenuse();
    }
    public double area(){
        return (0.5 * leg * leg);
    }
    public void print(){
        for(int h = 0; h < leg-1; h ++){
            System.out.print("*");
            for(int i = 0; i < leg; i ++){
                if(i == (h-1)){
                    System.out.print(" *");
                }
                if(i < (h-1)){
                    System.out.print("  ");
                }
            }
            System.out.println("");
        }
        for(int i = 0; i < leg; i++){
            System.out.print("*");
            System.out.print(" ");
        }
        System.out.println();
    }
}

