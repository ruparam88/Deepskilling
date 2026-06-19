import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(double price);
}

interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private double price;

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public void register(Observer o) { observers.add(o); }
    public void deregister(Observer o) { observers.remove(o); }
    public void notifyObservers() {
        for (Observer o : observers) o.update(price);
    }
}

class MobileApp implements Observer {
    public void update(double price) {}
}

class WebApp implements Observer {
    public void update(double price) {}
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();
        stockMarket.register(new MobileApp());
        stockMarket.register(new WebApp());
        stockMarket.setPrice(100.50);
    }
}