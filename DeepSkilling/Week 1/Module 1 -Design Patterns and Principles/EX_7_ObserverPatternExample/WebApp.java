public class WebApp implements Observer {
    public void update(String stockSymbol, double price) {
        System.out.println("[WebApp] " + stockSymbol + " is now $" + price);
    }
}
