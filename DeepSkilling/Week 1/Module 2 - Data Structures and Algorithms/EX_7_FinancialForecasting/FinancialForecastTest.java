import java.util.HashMap;
import java.util.Map;

public class FinancialForecastTest {
    public static void main(String[] args) {
        double presentValue = 10000.0;
        double growthRate = 0.07; // 7% annual growth
        int years = 10;

        double recursiveResult = FinancialForecast.futureValue(presentValue, growthRate, years);
        System.out.printf("Recursive future value after %d years: $%.2f%n", years, recursiveResult);

        Map<Integer, Double> cache = new HashMap<>();
        double memoizedResult = FinancialForecast.futureValueMemoized(presentValue, growthRate, years, cache);
        System.out.printf("Memoized future value after %d years: $%.2f%n", years, memoizedResult);

        double iterativeResult = FinancialForecast.futureValueIterative(presentValue, growthRate, years);
        System.out.printf("Iterative future value after %d years: $%.2f%n", years, iterativeResult);

        System.out.println("\nYear-by-year forecast (using memoized cache):");
        for (int y = 1; y <= years; y++) {
            double value = FinancialForecast.futureValueMemoized(presentValue, growthRate, y, cache);
            System.out.printf("Year %2d: $%.2f%n", y, value);
        }
    }
}
