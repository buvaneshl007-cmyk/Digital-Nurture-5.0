public class Ex07_TypeCastingExample {
    public static void main(String[] args) {
      
        double myDouble = 9.78;
        int    fromDouble = (int) myDouble;
        System.out.println("double value     : " + myDouble);
        System.out.println("Cast to int      : " + fromDouble);  // decimal part truncated

        System.out.println();

        
        int    myInt     = 5;
        double fromInt   = (double) myInt;
        System.out.println("int value        : " + myInt);
        System.out.println("Cast to double   : " + fromInt);
    }
}