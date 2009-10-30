/*
 * BasicIReportConnectionEditor.java
 *
 * Created on March 27, 2007, 9:31 AM
 */

package it.businesslogic.ireport.connection.gui;

import it.businesslogic.ireport.IReportConnection;
import it.businesslogic.ireport.IReportConnectionEditor;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  gtoffoli
 */
public class BasicIReportConnectionEditor extends javax.swing.JPanel implements IReportConnectionEditor {
    
    IReportConnection iReportConnection = null;
    
    /** Creates new form BasicIReportConnectionEditor */
    public BasicIReportConnectionEditor() {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabelPropertiesTable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCustomProperties = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        jLabelPropertiesTable.setText("IReportConnection properties");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 5, 0, 3);
        add(jLabelPropertiesTable, gridBagConstraints);

        jTableCustomProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableCustomProperties.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCustomPropertiesMouseClicked1(evt);
            }
        });

        jScrollPane1.setViewportView(jTableCustomProperties);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        add(jScrollPane1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void jTableCustomPropertiesMouseClicked1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCustomPropertiesMouseClicked1
        //jButtonRemoveProp.setEnabled( jTableCustomProperties.getSelectedRow() >= 0 );
    }//GEN-LAST:event_jTableCustomPropertiesMouseClicked1

    public void setIReportConnection(IReportConnection c) {
        
        this.iReportConnection = c;
        DefaultTableModel dtm = ((DefaultTableModel)jTableCustomProperties.getModel());
        dtm.setRowCount(0);
        if (iReportConnection != null)
        {
            HashMap map = iReportConnection.getProperties();
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext())
            {
                Object key = iterator.next();
                dtm.addRow(new Object[]{key, map.get(key)});
                //jTableCustomProperties.setValueAt(key, row, 0);
                //jTableCustomProperties.setValueAt(, row, 1);
            }
        }
        
        jTableCustomProperties.updateUI();
    }

    public IReportConnection getIReportConnection() {
        
        if (iReportConnection != null)
        {
            HashMap map = new HashMap();
            for (int i=0; i<jTableCustomProperties.getRowCount(); ++i)
            {
                Object key = jTableCustomProperties.getValueAt(i,0);
                Object value = jTableCustomProperties.getValueAt(i,1);
                
                if (key != null)
                map.put( key, jTableCustomProperties.getValueAt(i,1) );
            }
            
            iReportConnection.loadProperties( map );
        }
        
        return iReportConnection;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelPropertiesTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCustomProperties;
    // End of variables declaration//GEN-END:variables
    
}
