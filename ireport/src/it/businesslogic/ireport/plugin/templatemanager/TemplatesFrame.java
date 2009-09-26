/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TemplatesDialog.java
 *
 * Created on 19-giu-2009, 14.02.35
 */

package it.businesslogic.ireport.plugin.templatemanager;


import it.businesslogic.ireport.gui.JReportFrame;
import it.businesslogic.ireport.gui.MainFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.chinacreator.ireport.IreportUtil;
import com.chinacreator.ireport.component.DialogFactory;

public class TemplatesFrame extends javax.swing.JDialog {

	public Component com = null;
    Rectangle minimizeBounds = null;
    boolean adjustingValueSlider = false;
    /** Creates new form TemplatesDialog */
    public TemplatesFrame(java.awt.Component parent, boolean modal) {
        //super(parent,modal);
    	//this.
        setTitle("title...");
        initComponents();
        com = this;
       
        jListTemplateItems.setModel(new DefaultListModel());
        jListTemplateItems.setCellRenderer(new TemplateItemActionCellRenderer());

        jSlider1.setMinimum(80);
        jSlider1.setMaximum(700);
        jSlider1.setValue(120);
        jSlider1.getModel().addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

            }
        });

        getContentPane().setBackground(Color.WHITE);
        this.pack();
        this.setMinimumSize(getSize());

        loadItems();

        if (jListTemplateItems.getModel().getSize() > 0)
        {
            jListTemplateItems.setSelectedIndex(0);
        }

        Dimension d = getSize();
        //d.width = d.width;
        //d.height = d.height;
        setSize(d);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        }); 

    }

    public void loadItems() {

        ((DefaultListModel)jListTemplateItems.getModel()).removeAllElements();

        List<TemplateItemAction> actions = TemplateItemAction.getActions();
        TemplateItemAction t = new ReportTemplateItemAction();
        actions.add(t);
        for (TemplateItemAction a : actions)
        {
        		System.out.println(a.getDisplayName());
        	((DefaultListModel)jListTemplateItems.getModel()).addElement(a);
        }
    }

    private void storeWindowSize()
    {
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        templatesPanel1 = new TemplatesPanel();
        jPanel1 = new javax.swing.JPanel();

        jPanel3 = new javax.swing.JPanel();
        jPanelSlider = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();

        //新增按钮
        newWithTemplate = new javax.swing.JButton();
        deleteTemplate = new javax.swing.JButton();
        addTemplate = new javax.swing.JButton();
        modifyTemplate = new javax.swing.JButton();
        editorJrxml =  new javax.swing.JButton();
        
        jButton1 = new javax.swing.JButton();
        jLabelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTemplateItems = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("frametitle"); // NOI18N

        templatesPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                templatesPanel1ComponentResized(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 0);


        jPanelSlider.setLayout(new java.awt.BorderLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/businesslogic/ireport/plugin/templatemanager/small.png"))); // NOI18N

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanelSlider.add(jLabel3, java.awt.BorderLayout.WEST);

        jSlider1.setMaximum(1000);
        jSlider1.setMinimum(80);
        jSlider1.setFocusable(false);
        jSlider1.setMinimumSize(new java.awt.Dimension(120, 23));
        jSlider1.setPreferredSize(new java.awt.Dimension(150, 23));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {

            public void stateChanged(javax.swing.event.ChangeEvent evt) {

                jSlider1StateChanged(evt);
            }
        });
        jSlider1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {

                jSlider1PropertyChange(evt);
            }
        });
        jPanelSlider.add(jSlider1, java.awt.BorderLayout.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/it/businesslogic/ireport/plugin/templatemanager/big.png"))); // NOI18N

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanelSlider.add(jLabel2, java.awt.BorderLayout.EAST);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 150, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(jPanelSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 150, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 23, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .add(0, 0, Short.MAX_VALUE)
                    .add(jPanelSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel1.add(newWithTemplate, new java.awt.GridBagConstraints());

        newWithTemplate.setFont(new java.awt.Font("宋体", 0, 12));
        newWithTemplate.setText("从模板新建"); // NOI18N
        newWithTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            }
        });

        jPanel1.add(addTemplate, new java.awt.GridBagConstraints());

        addTemplate.setFont(new java.awt.Font("宋体", 0, 12));
        addTemplate.setText("添加到服务器"); // NOI18N
        addTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            	JDialog jd = new NewServerTemplate((Frame) com,true);
            	it.businesslogic.ireport.util.Misc.centerFrame(jd);
            	jd.setVisible(true);
            }
        });

        jPanel1.add(editorJrxml, new java.awt.GridBagConstraints());

        editorJrxml.setFont(new java.awt.Font("宋体", 0, 12));
        editorJrxml.setText("编辑该模板"); // NOI18N
        editorJrxml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            	try {
            	File f = new File(MainFrame.IREPORT_TMP_TEMPLATE_DIR+File.separator+ getSelectedTemplateDescriptor()+".xml");
            	if(f==null || !f.exists()){
            		DialogFactory.showErrorMessageDialog(com, "模板文件未找到", "错误");
            		return;
            	}
            	
            	String editorTempFile = MainFrame.IREPORT_TMP_DIR+File.separator+getSelectedTemplateDescriptor()+"_"+IreportUtil.dateFormat("MMddHHmmss", new Date())+".xml";
            	//copy 一个模板文件副本到临时文件夹
            	IreportUtil.bytesToFile(editorTempFile, IreportUtil.fileToBytes(f.getPath()));
            	
            	     setVisible(true);
                     JReportFrame jrf = MainFrame.getMainInstance().openFile( editorTempFile );
                     jrf.setSelected(true);
                    
                 } catch (Exception ex){
                      ex.printStackTrace();
                      DialogFactory.showErrorMessageDialog(com, "编辑模板文件错误"+ex.getMessage(), "错误");
                 }
            }
        });
        
        jPanel1.add(modifyTemplate, new java.awt.GridBagConstraints());

        modifyTemplate.setFont(new java.awt.Font("宋体", 0, 12));
        modifyTemplate.setText("修改"); // NOI18N
        modifyTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            	JDialog jd = new ModifyTemplate( com,true,getSelectedTemplateDescriptor().getDisplayName());
            	it.businesslogic.ireport.util.Misc.centerFrame(jd);
            	jd.setVisible(true);
            }
        });

        jPanel1.add(deleteTemplate, new java.awt.GridBagConstraints());

        deleteTemplate.setFont(new java.awt.Font("宋体", 0, 12));
        deleteTemplate.setText("删除"); 
        deleteTemplate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	
            	TemplateDescriptor st = getSelectedTemplateDescriptor();
            	if(st==null ||  IreportUtil.isBlank(st.getDisplayName())){
            		DialogFactory.showErrorMessageDialog(com, "你未选择需要删除的模板文件","删除错误");
            		return;
            	}
            	int ok= JOptionPane.showConfirmDialog(com, "你确定要删除"+st.getDisplayName()+"模板文件吗?");
            	if(ok==JOptionPane.NO_OPTION){
            		return;
            	}
            	//删除服务器端文件
            	IreportUtil.deleteTemplate(st.getDisplayName());
            	
            }
        });


        jButton1.setFont(new java.awt.Font("宋体", 0, 12));
        jButton1.setText( "关闭"); 
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });



        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jButton1, gridBagConstraints);

        jLabelDescription.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setFont(new java.awt.Font("宋体", 0, 12)); 
        jLabelDescription.setText( "描述"); 
        jLabelDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelDescription.setOpaque(true);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jListTemplateItems.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListTemplateItems.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListTemplateItemsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListTemplateItems);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 240, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(templatesPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .add(jLabelDescription, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)))
        );


        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(jLabelDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(templatesPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void jButtonOpenTemplateActionPerformed(java.awt.event.ActionEvent evt) {

        TemplateItemAction action = (TemplateItemAction) jListTemplateItems.getSelectedValue();
        if (action != null)
        {
            action.performAction(this,TemplateItemAction.BUTTON_OPEN_TEMPLATE);
        }
}

    private void jButtonLaunchWizardActionPerformed(java.awt.event.ActionEvent evt) {
        TemplateItemAction action = (TemplateItemAction) jListTemplateItems.getSelectedValue();
        if (action != null)
        {
            action.performAction(this,TemplateItemAction.BUTTON_LAUNCH_REPORT_WIZARD);
        }
}

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {

            if (adjustingValueSlider || jSlider1.getValue() == templatesPanel1.getIconsSize()) return;
            templatesPanel1.setIconsSize( jSlider1.getValue());


    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(getSelectedTemplateDescriptor().getDisplayName());
    	setVisible(false);
        this.dispose();
    }

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {
        jSlider1.setValue(jSlider1.getMinimum());

    }

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {
        //jSlider1.setValue(jSlider1.getMaximum());
        templatesPanel1.setFullPageView(true);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                adjustingValueSlider = true;
                jSlider1.setValue(templatesPanel1.getIconsSize());
                adjustingValueSlider = false;
            }
        });

    }

    private void jSlider1PropertyChange(java.beans.PropertyChangeEvent evt) {

    }

    private void templatesPanel1ComponentResized(java.awt.event.ComponentEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                adjustingValueSlider = true;
                jSlider1.setValue(templatesPanel1.getIconsSize());
                adjustingValueSlider = false;
            }
        });
    }

    private void jListTemplateItemsValueChanged(javax.swing.event.ListSelectionEvent evt) {
        updateSelection();
    }

    public void updateSelection()
    {
            TemplateItemAction action = (TemplateItemAction) jListTemplateItems.getSelectedValue();
            if (action == null) return;
            templatesPanel1.setVisible(action.getProperty(TemplateItemAction.PROP_SHOW_TEMPLATES) == Boolean.TRUE);
            jLabelDescription.setText(action.getDescription() == null ? "" : action.getDescription());
            //jButtonFinish.setVisible(action.getProperty(TemplateItemAction.PROP_SHOW_FINISH_BUTTON) == Boolean.TRUE);
            //jButtonLaunchWizard.setVisible(action.getProperty(TemplateItemAction.PROP_SHOW_LAUNCH_REPORT_WIZARD_BUTTON) == Boolean.TRUE);
            //jButtonOpenTemplate.setVisible(action.getProperty(TemplateItemAction.PROP_SHOW_OPEN_TEMPLATE_BUTTON) == Boolean.TRUE);
            jPanelSlider.setVisible(action.getProperty(TemplateItemAction.PROP_SHOW_TEMPLATES) == Boolean.TRUE);
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
    	MainFrame.IREPORT_TMP_TEMPLATE_DIR = "C:\\Documents and Settings\\Administrator\\.ireport\\remoteTemplate";
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TemplatesFrame dialog = new TemplatesFrame(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Return the selected template descriptor in the templates panel.
     * Return null is no template is selected
     * @return
     */
    public TemplateDescriptor getSelectedTemplateDescriptor()
    {
        return templatesPanel1.getSelectedTamplate();
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;

    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDescription;

    private javax.swing.JButton newWithTemplate;
    private javax.swing.JButton deleteTemplate;
    private javax.swing.JButton addTemplate;
    private javax.swing.JButton modifyTemplate;
    private javax.swing.JButton editorJrxml;

    private javax.swing.JList jListTemplateItems;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private TemplatesPanel templatesPanel1;
    // End of variables declaration


    public void setVisible(boolean b)
    {
        storeWindowSize();
        super.setVisible(b);
    }


}
