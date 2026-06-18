public class Ex30_PatternMatchingSwitch {

    static String describe(Object obj) {
        return switch (obj) {
            case Integer i  -> "Integer with value: " + i;
            case Double  d  -> "Double with value: "  + d;
            case String  s  -> "String with value: \"" + s + "\" (length=" + s.length() + ")";
            case Boolean b  -> "Boolean: " + b;
            case null       -> "null value";
            default         -> "Unknown type: " + obj.getClass().getSimpleName();
        };
    }

    public static void main(String[] args) {
        System.out.println(describe(42));
        System.out.println(describe(3.14));
        System.out.println(describe("Hello"));
        System.out.println(describe(true));
        System.out.println(describe(null));
        System.out.println(describe(new int[]{1, 2, 3}));
    }
}