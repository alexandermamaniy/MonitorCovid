package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class SintomasTablaJPanel extends JPanel {
    private JTable sintomasTable;
    private JScrollPane scrollPane;
    private DefaultTableModel table ;
    public SintomasTablaJPanel(Sintomas sintomas) {
        this.setBackground(Color.BLUE);
        sintomasTable = new JTable();
        sintomasTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table = new DefaultTableModel();
        table.addColumn("Sintomas");
        actualizar(sintomas);
        sintomasTable = new JTable(table);
        this.sintomasTable.setEnabled(false);
        TableRowSorter<DefaultTableModel> sorTable = new TableRowSorter<>(table);
        sintomasTable.setRowSorter(sorTable);
        TableRowSorter<DefaultTableModel> sortTable = new TableRowSorter<DefaultTableModel>(table);

        scrollPane = new JScrollPane(sintomasTable);
        this.add(scrollPane);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        scrollPane.setBounds(0, 0, 200,200);
    }
    public void actualizar(Sintomas sintomas) {
        limpiar();
        for (Sintoma sintoma: sintomas){
            table.addRow(new String[]{sintoma.toString()});//, sintoma.getClass().getName().split("\\.")[1]});
        }
    }
    private void limpiar(){
        int rowCount = table.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            table.removeRow(i);
        }
    }
}