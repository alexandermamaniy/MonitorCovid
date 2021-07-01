import monitor.Monitor;

public class Main {

    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        monitor.monitorear();
        System.out.println("resulted: " + monitor.getResultado());
    }
}
