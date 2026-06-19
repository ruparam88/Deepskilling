interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPalGateway {
    public void makePayment(double amount) {}
}

class StripeGateway {
    public void charge(double amount) {}
}

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway gateway;
    public PayPalAdapter(PayPalGateway gateway) { this.gateway = gateway; }
    public void processPayment(double amount) { gateway.makePayment(amount); }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway gateway;
    public StripeAdapter(StripeGateway gateway) { this.gateway = gateway; }
    public void processPayment(double amount) { gateway.charge(amount); }
}

public class AdapterPatternExample {
    public static void main(String[] args) {
        PaymentProcessor processor = new PayPalAdapter(new PayPalGateway());
        processor.processPayment(100.0);
    }
}