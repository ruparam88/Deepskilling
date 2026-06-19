interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    public RealImage(String filename) { this.filename = filename; loadFromDisk(); }
    private void loadFromDisk() {}
    public void display() {}
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;
    public ProxyImage(String filename) { this.filename = filename; }
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ProxyPatternExample {
    public static void main(String[] args) {
        Image image = new ProxyImage("test.jpg");
        image.display();
    }
}