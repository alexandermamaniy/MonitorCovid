package cargarregistros.gui;

import cargarregistros.datos.DatosRegistros;
import cargarregistros.utils.CrearSintomaRegistro;
import cargarregistros.utils.FormatoFecha;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class RegistroJPanel extends JPanel implements ItemListener, ActionListener {
    private final JLabel labelFecha;
    private final JComboBox<String> comboCategoriaSintoma;
    private final JButton buttonAgregarSintoma;
    private final Sintomas sintomasMonitorDisponibles;
    private final Registros registros;
    private final TablaSintomasDeRegistroJPanel tablaSintomasDeRegistroJPanel;
    private final TablaSintomasSeleccionadosJPanel tablaSintomasSeleccionadosJPanel;
    private final TablaFechasRegistrosJPanel tablaFechasRegistrosJPanel;
    private final JPanel jPanel1;
    private final JPanel jPanel2;
    private final VentanaJFrame frameRegistro;
    private final JButton salir;
    private final JLabel labelNombreSintoma;
    private Sintomas sintomasPaciente;

    public RegistroJPanel(Sintomas sintomasMonitorDisponibles, Registros registros, Sintomas sintomasPaciente, VentanaJFrame frameRegistro){
        this.sintomasMonitorDisponibles = sintomasMonitorDisponibles;
        this.sintomasPaciente = sintomasPaciente;
        this.registros = registros;
        this.frameRegistro = frameRegistro;


        jPanel1 = new JPanel();
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Toma de Registros");

        jPanel1.setBorder(bordejpanel);
        add(jPanel1);


        jPanel2 = new JPanel();
        Border bordejpanel2 = new TitledBorder(new EtchedBorder(), "Tabla de Registros");

        jPanel2.setBorder(bordejpanel2);
        add(jPanel2);

        labelNombreSintoma = new JLabel("Sintomas");
        jPanel1.add(labelNombreSintoma);



        FormatoFecha formatoFecha = new FormatoFecha();
        labelFecha = new JLabel("Fecha registro: "+ formatoFecha.dateToString(new Date()) );
        jPanel1.add(labelFecha);
        comboCategoriaSintoma = new JComboBox<>();
        jPanel1.add(comboCategoriaSintoma);

        comboCategoriaSintoma.addItemListener(this);

        ArrayList<String> combo = new ArrayList();

        for(Sintoma s: sintomasMonitorDisponibles ){
            combo.add(s.toString());
        }
        Collections.sort(combo);


        for(String s: combo){
            for (Sintoma ss : sintomasMonitorDisponibles){
                if (ss.toString().equals(s)) {
                    comboCategoriaSintoma.addItem(ss.getClass().getSimpleName() +" - "+ s);
                }
            }
        }

        buttonAgregarSintoma = new JButton("Registrar");
        buttonAgregarSintoma.addActionListener(this);
        jPanel1.add(buttonAgregarSintoma);
        tablaSintomasDeRegistroJPanel = new TablaSintomasDeRegistroJPanel(new Sintomas());
        jPanel2.add(tablaSintomasDeRegistroJPanel);
        tablaFechasRegistrosJPanel = new TablaFechasRegistrosJPanel(registros, tablaSintomasDeRegistroJPanel);
        jPanel2.add(tablaFechasRegistrosJPanel);

        tablaSintomasSeleccionadosJPanel = new TablaSintomasSeleccionadosJPanel();
        jPanel1.add(tablaSintomasSeleccionadosJPanel);

        salir = new JButton("Terminar");
        salir.addActionListener(this);
        add(salir);
    }

    public void itemStateChanged(ItemEvent e){

        if (e.getSource()== comboCategoriaSintoma && e.getStateChange() == ItemEvent.DESELECTED){
            String a = (String)(comboCategoriaSintoma.getSelectedItem());
            a = a.split("-")[1].strip();
            CrearSintomaRegistro crearSintomaRegistro = new CrearSintomaRegistro(sintomasMonitorDisponibles);
            Sintoma s = crearSintomaRegistro.crear(a);
            sintomasPaciente.add(s);
            tablaSintomasSeleccionadosJPanel.addRowSintomas(s);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        jPanel1.setBounds(40, 70, 670,280);
        labelNombreSintoma.setBounds(35, 25, 170,30);
        comboCategoriaSintoma.setBounds(35, 55, 300,30);
        comboCategoriaSintoma.setBackground(Color.WHITE);
        labelFecha.setBounds(350, 55, 300,30);
        buttonAgregarSintoma.setBounds(530, 55, 100,30);
        tablaSintomasSeleccionadosJPanel.setBounds(35, 100, 600,150);

        jPanel2.setBounds(40, 370, 670,300);
        tablaSintomasDeRegistroJPanel.setBounds(235, 35, 400,230);
        tablaFechasRegistrosJPanel.setBounds(35, 35, 200,230);
        salir.setBounds(610, 700, 100,30);

    }

    public void actionPerformed(ActionEvent e) {
        Object botonPulsado = e.getSource();

        if (botonPulsado == buttonAgregarSintoma) {
            int i=0;
            for(Sintoma ignored : sintomasPaciente){
                i++;
            }
            if (i>0){
                DatosRegistros datosRegistros = new DatosRegistros();
                Registro res = new Registro(new Date(), sintomasPaciente);
                registros.push(res);
                datosRegistros.guardarDatosRegistros(registros);
                tablaSintomasSeleccionadosJPanel.clear();
                tablaSintomasDeRegistroJPanel.actualizarTabla(sintomasPaciente);
                tablaFechasRegistrosJPanel.actualizarTabla();
                sintomasPaciente = new Sintomas();
            }
        } else  if ( botonPulsado == salir){
            frameRegistro.setVisible(false);
        }
    }
}
