public class MobileApp implements Observer {
    public void update(String stockSymbol, double price) {
        System.out.println("[MobileApp] " + stockSymbol + " is now $" + price);
    }
}
