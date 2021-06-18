import monitor.Monitor;

public class Main {

    public static void main(String[] args) {
        System.out.println("diagnostico");
        Monitor monitor = new Monitor();
        monitor.monitorear();
        System.out.println("resultado: " + monitor.getResultado());
    }

}
