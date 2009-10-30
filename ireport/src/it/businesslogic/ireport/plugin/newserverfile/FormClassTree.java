/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package it.businesslogic.ireport.plugin.newserverfile;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.chinacreator.ireport.IreportConstant;
import com.chinacreator.ireport.MyReportProperties;
import com.chinacreator.ireport.component.DialogFactory;
import com.chinacreator.ireport.rmi.IreportRmiClient;
import com.chinacreator.ireport.rmi.ReportFormClass;

/**
 * @author 李茂
 * @since 3.0
 * @version $Id: FormClassTree.java 2009-9-11 下午04:16:25 $
 *
 */
// begin FormClassTree.java
public class FormClassTree extends JPanel implements TreeSelectionListener {
	private JPanel buttonPanel = null;
	private JTree tree;


	private static JFrame frame = null;
	// Optionally play with line styles. Possible values are
	// "Angled" (the default), "Horizontal", and "None".
	private static boolean playWithLineStyle = false;
	private static String lineStyle = "Horizontal";

	private static NewServrtFile nfo=null;
	// Optionally set the look and feel.


	public FormClassTree(NewServrtFile f) {
		super(new BorderLayout());

		nfo = f;
		MyReportProperties.setProperties(IreportConstant.EFORM_TREE_SELECT, IreportConstant.EFORM_TREE_SELECT);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("业务类别");

		tree = new JTree(top);

		initTree(tree, top);

		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		tree.addTreeSelectionListener(this);

		if (playWithLineStyle) {
			System.out.println("line style = " + lineStyle);
			tree.putClientProperty("JTree.lineStyle", lineStyle);
		}

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree);

		Dimension minimumSize = new Dimension(100, 50);

		treeView.setMinimumSize(minimumSize);
		tree.expandPath(new TreePath(top));
		// Add the split pane to this panel.
		buttonPanel = new JPanel();
		JButton add =new JButton("确定");
		JButton exit =new JButton("取消");
		buttonPanel.add(add);
		buttonPanel.add(exit);
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(IreportConstant.EFORM_TREE_SELECT.equals(MyReportProperties.getStringProperties(IreportConstant.EFORM_TREE_SELECT))){
					DialogFactory.showWarnMessageDialog(frame, "你未选择业务类别", "错误");
				}else{
					//System.out.println(tree.getSelectionPath());
					TreePath tp = tree.getSelectionPath();
					Object[] obj = tp.getPath();
					
					String path = "";
					for (int i = 0; i < obj.length; i++) {
						if(i==0){
							continue;
						}
						path = path+obj[i]+"/";
					}
					path = path ==null?"":path.substring(0, path.length()-1);
					//nfo.jTextField3.setText(MyReportProperties.getStringProperties(IreportConstant.EFORM_TREE_SELECT).split("#")[1]);
					nfo.jTextField3.setText(path);
					nfo.setVisible(true);
					frame.dispose();
				}
			}
		});

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				nfo.setVisible(true);
				frame.dispose();
			}
		});


		add(treeView,BorderLayout.CENTER);
		add(buttonPanel,BorderLayout.SOUTH);

		this.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "选择业务类别"));

	}


	private JTree initTree(JTree jt, DefaultMutableTreeNode root) {
		// DefaultMutableTreeNode root = new DefaultMutableTreeNode("业务类别");
		// JTree jt = new JTree(root);
		try {
			IreportRmiClient.getInstance();
			// 查询出最高节点
			List<ReportFormClass> reportFormClass = (List<ReportFormClass>) IreportRmiClient
					.getRmiRemoteInterface().invokeServerMethod(1, "0");

			// 查询ID 为0 的业务节点
			for (int i = 0; i < reportFormClass.size(); i++) {
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(
						reportFormClass.get(i));
				root.add(child);
				recursion(child);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jt;

	}

	private void recursion(DefaultMutableTreeNode father) throws Exception {
		IreportRmiClient.getInstance();
		ReportFormClass r = (ReportFormClass) father.getUserObject();
		List<ReportFormClass> cf = null;
		if (r == null) {
			return;
		}
		try {

			cf = (List<ReportFormClass>) IreportRmiClient
					.getRmiRemoteInterface()
					.invokeServerMethod(1, r.getEc_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cf != null) {
			for (int i = 0; i < cf.size(); i++) {
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(cf.get(i));
				father.add(child);
				recursion(child);
			}
		}
		// 根据ID查询
	}

	public static void getFrame(NewServrtFile f){
		frame = new JFrame("业务类别树");

		// Add content to the window.
		frame.add(new FormClassTree(f));

		// Display the window.
		//frame.m
		frame.setMaximumSize(new Dimension(300,500));
		frame.setPreferredSize(new Dimension(300,500));
		frame.pack();
		it.businesslogic.ireport.util.Misc.centerFrame(frame);
		frame.setVisible(true);
		frame.addWindowListener(
        		new WindowAdapter(){
        			public void windowClosing(WindowEvent evt) {
        				nfo.setVisible(true);
        			}
        		}
        		);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
	/*	if (useSystemLookAndFeel) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println("Couldn't use system look and feel.");
			}
		}*/

		// Create and set up the window.
		frame = new JFrame("业务类别树");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Add content to the window.
		frame.add(new FormClassTree(null));

		// Display the window.
		//frame.m
		frame.setMaximumSize(new Dimension(350,200));
		frame.setPreferredSize(new Dimension(350,200));
		frame.pack();
		it.businesslogic.ireport.util.Misc.centerFrame(frame);
		frame.setVisible(true);
	}

	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		if (node == null || node.isRoot())
			return;

		ReportFormClass nodeInfo = (ReportFormClass) node.getUserObject();
		MyReportProperties.setProperties(IreportConstant.EFORM_TREE_SELECT, nodeInfo.getEc_id()+"#"+nodeInfo.getEc_name());
		
		//MyReportProperties.setProperties(IreportConstant.EFORM_TREE_SELECT_PATH, path );
		
	}

}

// end FormClassTree.java
