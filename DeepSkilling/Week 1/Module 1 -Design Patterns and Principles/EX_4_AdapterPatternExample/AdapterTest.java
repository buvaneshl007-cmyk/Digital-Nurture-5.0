public class AdapterTest {
    public static void main(String[] args) {
        PaymentProcessor payPal = new PayPalAdapter(new PayPalGateway());
        payPal.processPayment(50.0);

        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        stripe.processPayment(50.0);
    }
}
