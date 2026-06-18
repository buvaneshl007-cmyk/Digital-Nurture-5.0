import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
class MathOperations {
    public int square(int n)       { return n * n; }
    public int cube(int n)         { return n * n * n; }
    private String secret()        { return "Reflection unlocked!"; }
    public double power(double base, int exp) { return Math.pow(base, exp); }
}

public class Ex39_ReflectionAPI {
    public static void main(String[] args) throws Exception {

        Class<?> clazz = Class.forName("MathOperations");
        System.out.println("Class name: " + clazz.getName());
        System.out.println("\nDeclared methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.print("  " + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
            Parameter[] params = m.getParameters();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getType().getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        Object instance = clazz.getDeclaredConstructor().newInstance();

   
        Method square = clazz.getMethod("square", int.class);
        Object result = square.invoke(instance, 7);
        System.out.println("\nsquare(7) via reflection = " + result);

        Method power = clazz.getMethod("power", double.class, int.class);
        System.out.println("power(2.0, 10) via reflection = " + power.invoke(instance, 2.0, 10));
      Method secretMethod = clazz.getDeclaredMethod("secret");
        secretMethod.setAccessible(true);
        System.out.println("secret() via reflection = " + secretMethod.invoke(instance));
    }
}