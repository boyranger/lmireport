/*
 * ConnectionDialog.java
 *
 * Created on March 26, 2007, 3:52 PM
 */

package it.businesslogic.ireport.connection.gui;

import it.businesslogic.ireport.IReportConnection;
import it.businesslogic.ireport.IReportConnectionEditor;
import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.gui.sheet.Tag;
import it.businesslogic.ireport.util.I18n;
import it.businesslogic.ireport.util.Misc;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.chinacreator.ireport.javabeandatasource.JavaBeanRemoteDataSourceConnectionEditor;

/**
 *
 * @author  gtoffoli
 */
public class ConnectionDialog extends javax.swing.JDialog {
    
    protected IReportConnection iReportConnection = null;
    protected IReportConnectionEditor iReportConnectionEditor = null;
    
    private int dialogResult = JOptionPane.CANCEL_OPTION;
    private int step = 0;
    /**
     * Creates new form ConnectionDialog
     */
    public ConnectionDialog(java.awt.Frame parent, boolean isNewDatasource) {
        super(parent, true);
        initAll(isNewDatasource);
    }
    
    /**
     * Creates new form ConnectionDialog
     */
    public ConnectionDialog(java.awt.Dialog parent, boolean isNewDatasource) {
        super(parent, true);
        initAll(isNewDatasource);
    }
    
    
    public void initAll(boolean isNewDatasource)
    {
        initComponents();
        if (isNewDatasource)
        {
            this.setStep(0);
            DefaultListModel dlm = new DefaultListModel();
            List types = MainFrame.getMainInstance().getConnectionImplementations();
            for (int i=0; i<types.size(); ++i)
            {
                String cName = "" + types.get(i);

                try {
                    IReportConnection c = (IReportConnection)Class.forName(cName,true, MainFrame.getMainInstance().getReportClassLoader()).newInstance();
                    String sName = c.getDescription(); 
                            //(String)c.getMethod("getConnectionTypeName",new Class[]{}).invoke(null, new Object[]{});
                    dlm.addElement(new Tag(c, sName));
                    
                } catch (Throwable tw)
                {
                    tw.printStackTrace();
                }
            }
            jList1.setModel(dlm);
            this.jButtonSave.setEnabled(false);
            if (jList1.getModel().getSize() > 0)
            {
                jList1.setSelectedIndex(0);
            }
        }
        
        
        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCancelActionPerformed(e);
            }
        };
       
        applyI18n();
        
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);


        
        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonSave);
        
        this.pack();
        Misc.centerFrame(this);
    }
    
    
    public void setIReportConnection(IReportConnection conn)
    {
        setIReportConnection(conn, false);
    }
    
    private void setIReportConnection(IReportConnection conn, boolean isNew)
    {
        if (conn == null) return;
        this.iReportConnection = cloneConnection(conn);
        this.jTextFieldName.setText( iReportConnection.getName());
        this.jLabelTitle.setText( conn.getDescription() );
        
        jButtonSave.setText( I18n.getString("connectionDialog.buttonOK","Save"));
        jButtonTest.setEnabled(true);
        this.setStep(1);
        jPanelRoot.removeAll();
        
        iReportConnectionEditor = iReportConnection.getIReportConnectionEditor();
        //LIMAO add
        if(iReportConnectionEditor instanceof JavaBeanRemoteDataSourceConnectionEditor){
        	//��ȡ������
        	((JavaBeanRemoteDataSourceConnectionEditor)iReportConnectionEditor).setFather(this);
        }
        //------
        if (!isNew)
        {
            iReportConnectionEditor.setIReportConnection( iReportConnection );
        }
        
        jPanelCustomComponent.add((java.awt.Component)iReportConnectionEditor);
        jPanelRoot.add( jPanelModifyDatasource );
        jPanelRoot.updateUI();
        jTextFieldName.requestFocusInWindow();
    }
    
    public IReportConnection getIReportConnection()
    {
         return this.iReportConnection;
    }
     
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelModifyDatasource = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanelCustomComponent = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelRoot = new javax.swing.JPanel();
        jPanelNewDatasource = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jSeparator2 = new javax.swing.JSeparator();
        jPanelButtons = new javax.swing.JPanel();
        jButtonTest = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        jPanelModifyDatasource.setLayout(new java.awt.GridBagLayout());

        jLabelName.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelModifyDatasource.add(jLabelName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelModifyDatasource.add(jTextFieldName, gridBagConstraints);

        jSeparator3.setMinimumSize(new java.awt.Dimension(0, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanelModifyDatasource.add(jSeparator3, gridBagConstraints);

        jPanelCustomComponent.setLayout(new java.awt.BorderLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelModifyDatasource.add(jPanelCustomComponent, gridBagConstraints);

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabelTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/businesslogic/ireport/icons/datasource/datasource.png")));
        jLabelTitle.setText("<html>Datasource");
        jLabelTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabelTitle.setOpaque(true);
        jLabelTitle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jLabelTitle, gridBagConstraints);

        jSeparator1.setMinimumSize(new java.awt.Dimension(0, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator1, gridBagConstraints);

        jPanelRoot.setLayout(new java.awt.BorderLayout());

        jPanelRoot.setPreferredSize(new java.awt.Dimension(440, 400));
        jPanelNewDatasource.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Select the datasource type");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanelNewDatasource.add(jLabel2, gridBagConstraints);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 4, 4);
        jPanelNewDatasource.add(jScrollPane1, gridBagConstraints);

        jPanelRoot.add(jPanelNewDatasource, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanelRoot, gridBagConstraints);

        jSeparator2.setMinimumSize(new java.awt.Dimension(0, 2));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator2, gridBagConstraints);

        jPanelButtons.setLayout(new java.awt.GridBagLayout());

        jPanelButtons.setMinimumSize(new java.awt.Dimension(50, 30));
        jPanelButtons.setPreferredSize(new java.awt.Dimension(50, 30));
        jButtonTest.setText("Test");
        jButtonTest.setEnabled(false);
        jButtonTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTestActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanelButtons.add(jButtonTest, gridBagConstraints);

        jButtonSave.setText("Next >");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanelButtons.add(jButtonSave, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        jPanelButtons.add(jButtonCancel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanelButtons, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestActionPerformed

        IReportConnection tmpIReportConnection = this.iReportConnectionEditor.getIReportConnection();
        if (tmpIReportConnection != null)
        {
            try {
                tmpIReportConnection.test();
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonTestActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked

        if (SwingUtilities.isLeftMouseButton(evt) && evt.getClickCount() == 2)
        {
            jButtonSaveActionPerformed(null);
        }
        
    }//GEN-LAST:event_jList1MouseClicked

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        if (getStep() == 0)
        {
            if (jList1.getSelectedValue() == null) return;
            IReportConnection ic = (IReportConnection)(((Tag)jList1.getSelectedValue()).getValue());
            this.setIReportConnection( ic , true);
            return ;
        }
        else
        {
            if (this.jTextFieldName.getText().trim().length() == 0) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    I18n.getString("messages.connectionDialog.invalidName","Please insert a valid connection name!"),
                    I18n.getString("messages.connectionDialog.invalidNameCaption","Invalid connection name!"),
                    javax.swing.JOptionPane.WARNING_MESSAGE );
                return;
            }
            IReportConnection tmpIReportConnection = this.iReportConnectionEditor.getIReportConnection();
            if (tmpIReportConnection == null) return;
            tmpIReportConnection.setName(jTextFieldName.getText() );
            this.iReportConnection = tmpIReportConnection;
            this.setDialogResult(JOptionPane.OK_OPTION);
            this.setVisible(false);
            this.dispose();
        }
        
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged

        jButtonSave.setEnabled( jList1.getSelectedIndex() >= 0);
    }//GEN-LAST:event_jList1ValueChanged

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed

        this.setVisible(false);
        this.dispose();
        
    }//GEN-LAST:event_jButtonCancelActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    public void applyI18n()
    {
        jButtonSave.setText( I18n.getString("next","Next >"));
        jButtonCancel.setText( I18n.getString("connectionDialog.buttonCancel","Cancel"));
        jButtonTest.setText( I18n.getString("connectionDialog.buttonOK1","Test"));
        jLabelTitle.setText( I18n.getString("connectionDialog.newDatasourceTitle","New datasource"));
        
        jLabelName.setText(I18n.getString("connectionDialog.label1","Name"));
        setTitle(I18n.getString("connectionDialog.title","Connections properties"));
        
        jButtonCancel.setMnemonic(I18n.getString("connectionDialog.buttonCancelMnemonic","c").charAt(0));
        jButtonSave.setMnemonic(I18n.getString("connectionDialog.buttonOKMnemonic","o").charAt(0));
        jButtonTest.setMnemonic(I18n.getString("connectionDialog.buttonOK1Mnemonic","t").charAt(0));
                
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getDialogResult() {
        return dialogResult;
    }

    public void setDialogResult(int dialogResult) {
        this.dialogResult = dialogResult;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonTest;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelCustomComponent;
    private javax.swing.JPanel jPanelModifyDatasource;
    private javax.swing.JPanel jPanelNewDatasource;
    private javax.swing.JPanel jPanelRoot;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables
    
    
    /**
     * This method close the connection instancing a new connection of the same tipe and loading/storing the
     * properties...  newConn.loadProperties(  conn.getProperties() );
     *
     * We assume that the specific connection has a null constructor.
     * 
     */
    public IReportConnection cloneConnection(IReportConnection conn)
    {
        if (conn == null) return null;
        Class clazz = conn.getClass();
        
        try {
            IReportConnection newConn = (IReportConnection)clazz.newInstance();
            newConn.loadProperties(  conn.getProperties() );
            newConn.setName(conn.getName());
            return newConn;
        } catch (Throwable t) {
        }    
        
        return null;
    }
}
