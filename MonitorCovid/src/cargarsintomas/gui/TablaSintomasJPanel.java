package cargarsintomas.gui;

import monitor.Sintoma;
import monitor.Sintomas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TablaSintomasJPanel extends JPanel {
    private final JScrollPane tableScollPanel;
    private final DefaultTableModel dataTable;
    private Sintomas sintomas;

    public TablaSintomasJPanel(Sintomas sintomas){
        this.sintomas = sintomas;
        dataTable = new DefaultTableModel();
        JTable table = new JTable(dataTable);
        tableScollPanel = new JScrollPane(table);
        dataTable.addColumn("Nombre sintoma");
        dataTable.addColumn("Categoria");

        TableRowSorter<DefaultTableModel> sorTable = new TableRowSorter<>(dataTable);
        table.setRowSorter(sorTable);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
        sorTable.setSortKeys(sortKeys);

        add(tableScollPanel);
    }

    public void addRow(String[] row){
        dataTable.insertRow(0, row);
    }
    private void clear(){
        int rowCount = dataTable.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dataTable.removeRow(i);
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        tableScollPanel.setBounds(0, 0, 600,300);
        dataTable.insertRow(0, new Object[]{"", ""});
        clear();
        for(Sintoma sintoma: sintomas){
            String name = sintoma.toString();
            String categoria = sintoma.getClass().getName().split("\\.")[1];
            dataTable.insertRow(0, new Object[]{name, categoria });
        }
    }
}
