public class Ex37_InspectBytecode {

    static int multiply(int a, int b) {
        return a * b;
    }

    static String classify(int n) {
        if (n > 0)      return "Positive";
        else if (n < 0) return "Negative";
        else            return "Zero";
    }

    public static void main(String[] args) {
        System.out.println("3 * 4 = " + multiply(3, 4));
        System.out.println(classify(5));
        System.out.println(classify(-2));
        System.out.println(classify(0));
    }
}

