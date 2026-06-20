public class ProxyImage implements Image {
    private RealImage realImage;
    private final String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        } else {
            System.out.println("Using cached image for " + filename);
        }
        realImage.display();
    }
}
