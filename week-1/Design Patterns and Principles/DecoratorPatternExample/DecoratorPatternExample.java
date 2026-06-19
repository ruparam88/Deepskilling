interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    public void send(String message) {}
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier notifier;
    public NotifierDecorator(Notifier notifier) { this.notifier = notifier; }
    public void send(String message) { notifier.send(message); }
}

class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) { super(notifier); }
    public void send(String message) {
        super.send(message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) { super(notifier); }
    public void send(String message) {
        super.send(message);
    }
}

public class DecoratorPatternExample {
    public static void main(String[] args) {
        Notifier notifier = new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()));
        notifier.send("Hello");
    }
}