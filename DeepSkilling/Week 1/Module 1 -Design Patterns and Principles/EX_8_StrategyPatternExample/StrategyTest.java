public class StrategyTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234567890123456"));
        context.executePayment(100.0);

        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(50.0);
    }
}
