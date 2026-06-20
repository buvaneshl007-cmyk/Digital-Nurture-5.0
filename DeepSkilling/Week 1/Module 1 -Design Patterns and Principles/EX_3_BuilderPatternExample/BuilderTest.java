public class BuilderTest {
    public static void main(String[] args) {
        Computer gamingPc = new Computer.Builder()
                .setCpu("Intel i9")
                .setRam("32GB")
                .setStorage("2TB SSD")
                .setGpu("RTX 4090")
                .build();

        Computer officePc = new Computer.Builder()
                .setCpu("Intel i5")
                .setRam("8GB")
                .setStorage("256GB SSD")
                .build();

        System.out.println(gamingPc);
        System.out.println(officePc);
    }
}
