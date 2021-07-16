package cargarregistros.gui;

import cargarregistros.utils.FormatoFecha;
import monitor.Registro;
import monitor.Registros;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablaFechasRegistrosJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;
    private final Registros registros;
    private final TablaSintomasDeRegistroJPanel tablaSintomasDeRegistroJPanel;

    public TablaFechasRegistrosJPanel(Registros registros, TablaSintomasDeRegistroJPanel tablaSintomasDeRegistroJPanel){
        this.registros = registros;
        this.tablaSintomasDeRegistroJPanel = tablaSintomasDeRegistroJPanel;
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Fecha");

        TableRowSorter<DefaultTableModel> sorTable = new TableRowSorter<>(dataTable);
        table.setRowSorter(sorTable);

        add(tableScollPanel);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                int row=table.rowAtPoint(e.getPoint());
                int col= table.columnAtPoint(e.getPoint());
                FormatoFecha formatoFecha = new FormatoFecha();

                for(Registro r: registros) {
                    if (formatoFecha.dateAndHourToString(r.getFecha()).equals((String)table.getValueAt(row,col) )){
                        tablaSintomasDeRegistroJPanel.actualizarTabla(r.getSintomas());
                    }
                }
            }
        });
    }

    private void clear(){
        int rowCount = dataTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dataTable.removeRow(i);
        }

    }

    public void actualizarTabla(){
        clear();

        FormatoFecha formatoFecha = new FormatoFecha();

        for(Registro r: registros) {
            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
            dataTable.insertRow(0, new Object[]{fecha });
        }
    }

    public void paintComponent(Graphics g){
        tableScollPanel.setBounds(0, 0, 200,230);
        FormatoFecha formatoFecha = new FormatoFecha();
        dataTable.insertRow(0, new Object[]{""});
        clear();
        for(Registro r: registros) {
            String fecha = formatoFecha.dateAndHourToString(r.getFecha());
            dataTable.insertRow(0, new Object[]{fecha });
        }
    }
}
