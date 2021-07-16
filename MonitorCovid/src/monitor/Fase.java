package monitor;

import java.io.Serializable;
import java.util.Date;

public class Fase implements Serializable {

    private String nombre;
    private int dia;
    private Date ultimoAcceso;

    public Fase(String nombre){
        this.nombre = nombre;
        dia = 0;
        ultimoAcceso = new Date();
    }

    public String getNombre() {
        return nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }
}
