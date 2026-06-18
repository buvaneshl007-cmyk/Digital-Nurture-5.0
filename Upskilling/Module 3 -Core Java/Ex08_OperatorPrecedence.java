// Ex08_OperatorPrecedence.java
// Objective: Explore how Java evaluates expressions.

public class Ex08_OperatorPrecedence {
    public static void main(String[] args) {
        // * and / have higher precedence than + and -
        int r1 = 10 + 5 * 2;           // 5*2=10, then 10+10 = 20
        System.out.println("10 + 5 * 2          = " + r1 + "  (* before +)");

        int r2 = (10 + 5) * 2;         // parentheses first: 15*2 = 30
        System.out.println("(10 + 5) * 2        = " + r2 + "  (parentheses override)");

        int r3 = 20 / 4 + 3 * 2 - 1;   // 20/4=5, 3*2=6, 5+6-1 = 10
        System.out.println("20 / 4 + 3 * 2 - 1 = " + r3);

        int r4 = 10 + 6 % 4;           // % before +: 6%4=2, 10+2 = 12
        System.out.println("10 + 6 % 4          = " + r4 + "  (% before +)");

        boolean r5 = 5 > 3 && 2 < 4;   // relational before logical
        System.out.println("5 > 3 && 2 < 4      = " + r5);
    }
}