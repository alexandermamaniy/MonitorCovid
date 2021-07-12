package cargarregistros.gui;

import cargarregistros.utils.FormatoFecha;
import cargarregistros.utils.FormatoSintomas;
import monitor.Registro;
import monitor.Registros;
import monitor.Sintoma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaRegistrosJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;
    private final Registros registros;

    public TablaRegistrosJPanel(Registros registros){
        this.registros = registros;
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Fecha");
        dataTable.addColumn("Sintomas");
        FormatoFecha formatoFecha = new FormatoFecha();
        FormatoSintomas formatoSintomas = new FormatoSintomas();

        Registros aux = new Registros();
        registros.
        for (Registro res: registros){
            aux.push(registros.);
        }

        for(Registro r: aux) {
            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
            String sintomasPaciente = formatoSintomas.formatoSintomas(r.getSintomas());
            dataTable.addRow(new Object[]{fecha, sintomasPaciente });
            System.out.println("Ingreso,  "+ r);
        }

//        for(Registro r: registros){
//            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
//            String sintomasPaciente = formatoSintomas.formatoSintomas(r.getSintomas());
//            dataTable.addRow(new Object[]{fecha, sintomasPaciente });
//            // invertir
//        }

        add(tableScollPanel);
    }

    private void clear(){
        int rowCount = dataTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dataTable.removeRow(i);
        }

    }

    public void actualizarTabla(){
        clear();
//        for (Registro r: registros){
//            StringBuilder data = new StringBuilder();
//            FormatoFecha f = new FormatoFecha();
//            for (Sintoma s: r.getSintomas()){
//                data.append(s.toString()).append(", ");
//            }
//            addRow(new String[]{f.dateAndHourToString(r.getFecha()), data.toString()});
//        // invertir
//        }

        FormatoFecha formatoFecha = new FormatoFecha();
        FormatoSintomas formatoSintomas = new FormatoSintomas();

        Registros aux = new Registros();
        for (Registro res: registros){
            aux.push(res);
        }

        for(Registro r: aux) {
            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
            String sintomasPaciente = formatoSintomas.formatoSintomas(r.getSintomas());
            dataTable.addRow(new Object[]{fecha, sintomasPaciente });
            System.out.println("Ingreso,  "+ r);
        }
    }

    private void addRow(String[] row){
        dataTable.addRow(row);
    }

    public void paintComponent(Graphics g){
        tableScollPanel.setBounds(0, 0, 600,230);
    }
}
