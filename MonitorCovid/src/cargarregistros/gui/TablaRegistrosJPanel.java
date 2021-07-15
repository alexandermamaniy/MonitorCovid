package cargarregistros.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class TablaRegistrosJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;
    private final Sintomas sintomas;

    public TablaRegistrosJPanel(Sintomas sintomas){
        this.sintomas = sintomas;
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Categoria");
        dataTable.addColumn("Nombre Sintoma");

        TableRowSorter<DefaultTableModel> sorTable = new TableRowSorter<>(dataTable);
        table.setRowSorter(sorTable);
        add(tableScollPanel);
    }

    private void clear(){
        int rowCount = dataTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dataTable.removeRow(i);
        }

    }

    public void actualizarTabla(Sintomas sintomas){
        clear();

        for(Sintoma s: sintomas) {
            String categoria = s.getClass().getSimpleName();
            String nombre = s.toString();
            dataTable.insertRow(0, new Object[]{categoria, nombre });
        }
    }

    public void paintComponent(Graphics g){
        tableScollPanel.setBounds(0, 0, 400,230);
        dataTable.insertRow(0, new Object[]{"", ""});
        clear();
        for(Sintoma s: sintomas) {
            String categoria = s.getClass().getSimpleName();
            String nombre = s.toString();
            dataTable.insertRow(0, new Object[]{categoria, nombre });
        }
    }
}
