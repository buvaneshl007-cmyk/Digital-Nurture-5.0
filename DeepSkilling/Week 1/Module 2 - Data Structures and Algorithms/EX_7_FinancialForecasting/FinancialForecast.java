import java.util.HashMap;
import java.util.Map;

public class FinancialForecast {

    /**
     * Plain recursive future value calculation.
     * futureValue(present, rate, n) = present * (1 + rate)^n
     * Time complexity: O(n), Space complexity: O(n) due to the call stack.
     */
    public static double futureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return futureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    /**
     * Memoized recursive future value calculation, useful when forecasting
     * many overlapping year ranges repeatedly (e.g., in a UI that recalculates
     * often). Time complexity: O(n) per unique year, O(1) for cached lookups.
     */
    public static double futureValueMemoized(double presentValue, double growthRate, int years,
                                              Map<Integer, Double> cache) {
        if (years == 0) {
            return presentValue;
        }
        if (cache.containsKey(years)) {
            return cache.get(years);
        }
        double result = futureValueMemoized(presentValue, growthRate, years - 1, cache) * (1 + growthRate);
        cache.put(years, result);
        return result;
    }

    /**
     * Iterative version - avoids recursion/call-stack growth entirely.
     * Time complexity: O(n), Space complexity: O(1).
     */
    public static double futureValueIterative(double presentValue, double growthRate, int years) {
        double result = presentValue;
        for (int i = 0; i < years; i++) {
            result *= (1 + growthRate);
        }
        return result;
    }
}
