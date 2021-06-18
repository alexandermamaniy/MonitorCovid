package monitor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Stack;

public class Registros implements Iterable<Registro>, Serializable {
    private Stack<Registro> registros;

    public Registros() {
        registros = new Stack<>();
    }

    public void push(Registro r){
        registros.push(r);
    }

    public Registro peek() {
        return registros.peek();
    }

    public boolean isEmpty(){
        return registros.isEmpty();
    }

    @Override
    public Iterator<Registro> iterator() {
        return registros.iterator();
    }
}
