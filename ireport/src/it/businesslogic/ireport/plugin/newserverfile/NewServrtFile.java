/*
 * NewServrtFile.java
 *
 * Created on 2009年10月14日, 上午9:01
 */

package it.businesslogic.ireport.plugin.newserverfile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import it.businesslogic.ireport.gui.JReportFrame;
import it.businesslogic.ireport.gui.MainFrame;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import com.chinacreator.ireport.AddedOperator;
import com.chinacreator.ireport.IreportConstant;
import com.chinacreator.ireport.IreportUtil;
import com.chinacreator.ireport.MyReportProperties;
import com.chinacreator.ireport.component.DialogFactory;
import com.chinacreator.ireport.rmi.IreportFile;
import com.chinacreator.ireport.rmi.IreportRmiClient;

/**
 *
 * @author  Administrator
 */
public class NewServrtFile extends javax.swing.JFrame {
	private String myReportName = "";
    /** Creates new form NewServrtFile */
    public NewServrtFile() {
        initComponents();
        if(!IreportUtil.isBlank(myReportName)){
        	jTextField1.setText(myReportName);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
   
        // 初始化报表选择列表
        jComboBox1 =  new JComboBox();
        JInternalFrame[] jif = MainFrame.getMainInstance().getJMDIDesktopPane().getAllFrames();
        JReportFrame jf = null;
        for (int i = 0; i < jif.length; i++) {
        	String repid = null;
			if(jif[i]!=null && jif[i] instanceof JReportFrame){
				jf = (JReportFrame) jif[i];
				String path = jf.getReport().getFilename();
				repid = IreportUtil.getIdFromReportPath(path);
				if(!IreportUtil.isRemoteFile(repid)){
					//这里将文件名与文件全路径创建一个映射
					MyReportProperties.setProperties(repid+"TEMP-ME", path);
					jComboBox1.addItem(repid);
					if(jf.isSelected()){
						myReportName = repid;
						jComboBox1.setSelectedItem(repid);
					}
				}

			}

		}
        
        jComboBox1.addActionListener(
        		new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						JComboBox jb = (JComboBox) e.getSource();
						String selectI = (String) jb.getSelectedItem();
						jTextField1.setText(selectI);
					}

        		}
        );

        
        
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        
        jTextField3.setEditable(false);
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "JDBC连接报表", "javabean数据源报表"}));
        ItemListener itl = new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				String select = (String) jComboBox2.getSelectedItem();
				System.out.println(select);
			}
        };
        
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/it/businesslogic/ireport/plugin/newserverfile/box_upload.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("宋体", 1, 14)); // NOI18N
        jLabel2.setText("本地报表文件上传至服务器");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("当前非服务器报表");

        jLabel4.setText("报表名称");

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("报表描述");

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("业务类别");

        jButton3.setText("选择...");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jLabel7.setText("创建者");

        jLabel8.setText("报表类型");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jTextField4)
                    .add(jTextField2)
                    .add(jTextField1)
                    .add(jComboBox1, 0, 211, Short.MAX_VALUE)
                    .add(jTextField3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel6)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(42, 42, 42)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(this.getClass().getResource("/it/businesslogic/ireport/plugin/newserverfile/add.gif"))); // NOI18N
        jButton1.setText("添加至服务器");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("取消");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(44, 44, 44)
                .add(jLabel1)
                .add(56, 56, 56)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 285, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jSeparator2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .add(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:选择树
   	 this.setVisible(false);
	 FormClassTree.getFrame(this);
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:添加
    	JFrame father = this;
    	father.setVisible(false);
		//new Thread( new Runnable() {
			//public void run() {
				try {
				IreportRmiClient.getInstance();
		    	IreportFile ifi = new IreportFile();
		    	String selectFilePath = MyReportProperties.getStringProperties((jComboBox1.getSelectedItem()+"TEMP-ME"));
		    	if(IreportUtil.isBlank(selectFilePath)){
		    		DialogFactory.alert("你未选择报表文件");
		    		father.setVisible(true);
		    		return;
		    	}
		    	ifi.setContent(IreportUtil.fileToBytes(selectFilePath));
		    	String username = MyReportProperties.getStringProperties(IreportConstant.USERNAME);
		    	if(IreportUtil.isBlank(username)){
		    		DialogFactory.alert("你未登录");
		    		father.setVisible(true);
		    		return;
		    	}
		    	ifi.setCreator(username);
		    	String reportname = jTextField1.getText();
		    	if(IreportUtil.isBlank(reportname)){
		    		DialogFactory.alert("你未填写报表名称");
		    		father.setVisible(true);
		    		return;
		    	}
		    	ifi.setFileName(reportname);
		    	String note = jTextField2.getText();
		    	ifi.setNote(note);
		    	String ecid= MyReportProperties.getStringProperties(IreportConstant.EFORM_TREE_SELECT);
		    	if(IreportUtil.isBlank(ecid)){
		    		DialogFactory.alert("你未选择业务类别");
		    		father.setVisible(true);
		    		return;
		    	}
		    	System.out.println(ifi.getContent().length+username+note+reportname);
		    	ecid = ecid.split("#")[0];
		    	ifi.setEc_id(ecid);

		    	String returnid = null;
		    	returnid = IreportRmiClient.getRmiRemoteInterface().addNewReport(ifi);
		    	if(IreportUtil.isBlank(returnid)){
		    		AddedOperator.log("新建文件"+reportname+"到服务器失败", IreportConstant.ERROR_);
		    		DialogFactory.showErrorMessageDialog(this,"新建文件"+reportname+"到服务器失败","新建错误");
		    		return;
		    	}
		    	//这样打开的文件也必须是锁定的
		    	IreportRmiClient.getInstance();
		        boolean b = IreportRmiClient.getRmiRemoteInterface().lockReport(IreportUtil.getReportLock(returnid));
		          if(!b){
		        	  AddedOperator.log("锁定服务器端文件出错，你的后续修改将对服务器文件无效", IreportConstant.ERROR_);
		        	  DialogFactory.showErrorMessageDialog(null, "锁定服务器端文件出错，你的后续修改将对服务器文件无效", "锁定错误");
		        }

		        //文件锁定后需要存储信息到内存，以备能成功保存以及解锁
		        String localName = IreportUtil.getIdFromReportPath(selectFilePath);
		        MyReportProperties.setProperties(localName+IreportConstant.LOCAL_TO_SERVER, returnid);
		    	AddedOperator.log("成功将本地文件"+selectFilePath+"保存到服务器,在服务器端得ID为["+returnid+"]", IreportConstant.RIGHT_);
				father.dispose();
				} catch (Exception e) {

					e.printStackTrace();
					DialogFactory.showErrorMessageDialog(null, "新建服务器文件时出错:\n"+e.getMessage(), "错误");
					father.setVisible(true);
				}
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:取消
    	this.setVisible(true);
    	this.dispose();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NewServrtFile dialog = new NewServrtFile();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration
    
}
