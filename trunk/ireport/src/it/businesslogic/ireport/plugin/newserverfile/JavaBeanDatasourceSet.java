/*
 * JavaBeanDatasourceSet.java
 *
 * Created on 2009年10月14日, 下午3:22
 */

package it.businesslogic.ireport.plugin.newserverfile;

import it.businesslogic.ireport.gui.MainFrame;

import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;

import javax.swing.AbstractListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.chinacreator.ireport.AddedOperator;
import com.chinacreator.ireport.IreportConstant;
import com.chinacreator.ireport.IreportUtil;
import com.chinacreator.ireport.MyReportProperties;
import com.chinacreator.ireport.component.DialogFactory;
import com.chinacreator.ireport.rmi.IndexInfo;
import com.chinacreator.ireport.rmi.IreportRmiClient;

/**
 * 
 * @author Administrator
 */
public class JavaBeanDatasourceSet extends javax.swing.JDialog {

	/** Creates new form JavaBeanDatasourceSet */
	public Object[] paramObj = null;

	public JavaBeanDatasourceSet jb = null;

	public JavaBeanDatasourceSet(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		jb = this;
		initComponents();
		removeDate();
		
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList2 = new javax.swing.JList();
		jLabel2 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("输入类名进行检索,可使用通配符?和* ");

		jTextField1.setBackground(new java.awt.Color(255, 255, 255));

		jTextField1.getDocument().addDocumentListener(new DocumentListener() {

			public void changedUpdate(DocumentEvent e) {
				jTextField1TextChanged(e);
			}

			public void insertUpdate(DocumentEvent e) {
				jTextField1TextChanged(e);
			}

			public void removeUpdate(DocumentEvent e) {
				jTextField1TextChanged(e);
			}

		});
		// jScrollPane1.setBorder(null);

		jScrollPane1.setBorder(javax.swing.BorderFactory
				.createTitledBorder("类全名"));

		jList1
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						jList1ValueChanged(evt);
					}
				});

		jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList1.setFont(new Font("Dialog", Font.PLAIN, 12));
		jScrollPane1.setViewportView(jList1);

		// jScrollPane2.setBorder(null);

		jScrollPane2.setBorder(javax.swing.BorderFactory
				.createTitledBorder("方法名"));

		jList2
				.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
					public void valueChanged(
							javax.swing.event.ListSelectionEvent evt) {
						jList2ValueChanged(evt);
					}
				});
		jList2.setFont(new Font("Dialog", Font.PLAIN, 12));

		jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane2.setViewportView(jList2);

		jLabel2.setText("类名");

		jLabel3.setText("方法名");

		jButton1.setIcon(new javax.swing.ImageIcon(this.getClass().getResource(
				"/it/businesslogic/ireport/plugin/newserverfile/docs.gif"))); // NOI18N
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton1MouseClicked(evt);
			}
		});

		jButton2.setText("关闭");
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton2MouseClicked(evt);
			}
		});

		jButton3.setText("确定");
		jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton3MouseClicked(evt);
			}
		});

		jButton4.setText("测试");
		jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton4MouseClicked(evt);
			}
		});

		jButton5.setText("重新建立索引");
		jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton5MouseClicked(evt);
			}
		});

		jLabel4.setText("");

		jLabel5.setText("");

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																org.jdesktop.layout.GroupLayout.TRAILING,
																layout
																		.createSequentialGroup()
																		.add(
																				jScrollPane1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				455,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.add(
																				jScrollPane2,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				158,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(
																jTextField1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																619,
																Short.MAX_VALUE)
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								false)
																						.add(
																								jLabel3,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.add(
																								jLabel2,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								48,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				layout
																						.createParallelGroup(
																								org.jdesktop.layout.GroupLayout.TRAILING)
																						.add(
																								jTextField3,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								567,
																								Short.MAX_VALUE)
																						.add(
																								org.jdesktop.layout.GroupLayout.LEADING,
																								jTextField2,
																								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																								567,
																								Short.MAX_VALUE)))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				jButton1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				24,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.add(
																				88,
																				88,
																				88)
																		.add(
																				jLabel5,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				263,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.UNRELATED)
																		.add(
																				jButton4)
																		.add(
																				57,
																				57,
																				57)
																		.add(
																				jButton3)
																		.addPreferredGap(
																				org.jdesktop.layout.LayoutStyle.RELATED)
																		.add(
																				jButton2))
														.add(
																layout
																		.createSequentialGroup()
																		.add(
																				jLabel1,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				116,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.add(
																				195,
																				195,
																				195)
																		.add(
																				jLabel4,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				178,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.add(
																				18,
																				18,
																				18)
																		.add(
																				jButton5,
																				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																				112,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(jButton5)
														.add(jLabel1)
														.add(
																jLabel4,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																19,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												jTextField1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(
																jScrollPane1,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																311,
																Short.MAX_VALUE)
														.add(
																jScrollPane2,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																311,
																Short.MAX_VALUE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																jLabel2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																18,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																jTextField2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.BASELINE)
														.add(
																jLabel3,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																18,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(
																jTextField3,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(
												layout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.TRAILING)
														.add(jButton1)
														.add(
																layout
																		.createParallelGroup(
																				org.jdesktop.layout.GroupLayout.BASELINE)
																		.add(
																				jButton2)
																		.add(
																				jButton3)
																		.add(
																				jButton4)
																		.add(
																				jLabel5)))
										.addContainerGap()));

		it.businesslogic.ireport.util.Misc.centerFrame(this);
		pack();
	}// </editor-fold>

	private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {
		// TODO add your handling code here:类值变化
		try {
			String classname = (String) jList1.getSelectedValue();
			if (classname != null && classname.indexOf("-") != -1) {
				jTextField2.setText(classname.split("\\s\\-\\s")[1]);
			}
			
			
			IreportRmiClient.getInstance();
			final String[] ms = IreportRmiClient.rmiInterfactRemote.searchClassMethods(jTextField2.getText());
			if (ms == null || ms.length == 0) {
				jList2.setModel(new javax.swing.AbstractListModel() {
					public int getSize() {
						return 0;
					}

					public Object getElementAt(int i) {
						return null;
					}
				});

			} else {
				jList2.setModel(new javax.swing.AbstractListModel() {
					public int getSize() {
						return ms.length;
					}

					public Object getElementAt(int i) {
						return ms[i];
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			jList2.setModel(new javax.swing.AbstractListModel() {
				public int getSize() {
					return 0;
				}

				public Object getElementAt(int i) {
					return null;
				}
			});
		}
		

	}

	private void jList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {
		// TODO add your handling code here:方法名变化
		if (jList2.getSelectedValue() == null) {
			return;
		}
		jTextField3.setText((String) jList2.getSelectedValue());
	}

	private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:重建索引
		IreportRmiClient.getInstance();
		try {
			jLabel4.setForeground(Color.BLACK);
			IndexInfo info = IreportRmiClient.rmiInterfactRemote.indexInfo();
			if (info == null) {
				int i = DialogFactory.showConfirmDialog(this, "第一次创建索引，是否继续？",
						"新建索引", JOptionPane.INFORMATION_MESSAGE);
				if (i != JOptionPane.OK_OPTION) {
					return;
				}
			} else {
				int i = DialogFactory.showConfirmDialog(this, "上次索引信息\n"
						+ "索引于IP：" + info.getIp() + "\n" + "创建时间："
						+ info.getCreatetime() + "\n" + "被索引的文件位置(服务器)："
						+ info.getClassesdir() + "\n" + "索引存放位置(服务器)："
						+ info.getIndexdir() + "\n" + "\n是否重建索引？", "重建索引",
						JOptionPane.INFORMATION_MESSAGE);
				if (i != JOptionPane.OK_OPTION) {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			AddedOperator.log("获取索引信息错误：" + e.getMessage(),
					IreportConstant.ERROR_);
		}

		Thread processThread = null;
		try {

			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					boolean b = false;
					try {
						b = IreportRmiClient.rmiInterfactRemote.generateIndex();
						if (!b) {
							// 0个索引文件
							AddedOperator.log("索引到0个文件..可能你的路径配置错误，请查看服务端输出信息",
									IreportConstant.WARN_);

						} else {
							AddedOperator.log("新建索引成功", IreportConstant.RIGHT_);
						}
					} catch (RemoteException e) {
						e.printStackTrace();
						AddedOperator.log("新建索引错误：" + e.getMessage(),
								IreportConstant.ERROR_);
						DialogFactory.showErrorMessageDialog(jb,
								e.getMessage(), "新建索引错误");
						throw new RuntimeException(e.getMessage());
					}

				}
			}).start();

			final Thread pt = new Thread(new Runnable() {

				public void run() {
					try {
						java.text.NumberFormat nf = java.text.NumberFormat
								.getPercentInstance();
						nf.setMinimumFractionDigits(0);// 小数点后保留几位
						while (true) {
							int[] pp = null;
							try {
								pp = IreportRmiClient.rmiInterfactRemote
										.indexProgress();
							} catch (RemoteException e) {
								e.printStackTrace();
								AddedOperator.log("获取索引进度错误",
										IreportConstant.ERROR_);
							}

							String str = null;
							if (pp[1] != 0 && pp[0] != 0 && pp[1] >= pp[0]) {
								str = "100%";
							} else {
								str = nf.format(new Double(pp[1])
										/ new Double(pp[0]));// 要转化的数
							}

							jb.jLabel4.setText("正在创建索引..." + str);
							if ("100%".equals(str)) {
								break;
								// Thread.currentThread().interrupt();
							}
						}
						try {
							jb.jLabel4.setText("索引创建完毕 100%");
							jb.jLabel4.setForeground(new Color(0,0,0));
							int i = 0;
							while (true) {
								Thread.sleep(20);
								Color c = new Color(i, i, i);
								jb.jLabel4.setForeground(c);
								if (i == 254) {
									jb.jLabel4.setText("");
									break;
								}
								++i;
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
						AddedOperator.log("获取索引进度错误", IreportConstant.ERROR_);
					}
				}

			});
			pt.start();
			processThread = pt;

			// jLabel4.setText("完成");
			// AddedOperator.log("新建索引成功", IreportConstant.RIGHT_);
		} catch (Exception e) {
			try {
				processThread.interrupt();
			} catch (Exception ex) {
			}
			jLabel4.setText("创建索引失败！");
			AddedOperator.log("新建索引错误：" + e.getMessage(),
					IreportConstant.ERROR_);
			DialogFactory
					.showErrorMessageDialog(this, e.getMessage(), "新建索引错误");
		}
	}

	private void jTextField1TextChanged(DocumentEvent e) {
		// TODO add your handling code here:搜索框值变化
		String search = jTextField1.getText();
		if (IreportUtil.isBlank(search)) {
			jList1.setModel(new javax.swing.AbstractListModel() {
				public int getSize() {
					return 0;
				}

				public Object getElementAt(int i) {
					return null;
				}
			});

			jList2.setModel(new javax.swing.AbstractListModel() {
				public int getSize() {
					return 0;
				}

				public Object getElementAt(int i) {
					return null;
				}
			});
			jLabel5.setText("");
			return;
		}

		List<String> lists;
		try {
			// lists = Searcher.searchToArray(new File("F:/index"), search);
			IreportRmiClient.getInstance();
			lists = IreportRmiClient.rmiInterfactRemote.searchIndex(search);
			if (lists == null || lists.size() == 0) {
				jList1.setModel(new javax.swing.AbstractListModel() {
					public int getSize() {
						return 0;
					}

					public Object getElementAt(int i) {
						return null;
					}
				});

				jList2.setModel(new javax.swing.AbstractListModel() {
					public int getSize() {
						return 0;
					}

					public Object getElementAt(int i) {
						return null;
					}
				});
				jLabel5.setText("");
				return;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			AddedOperator
					.log("搜索失败:" + e1.getMessage(), IreportConstant.ERROR_);
			return;
		}

		jList1.setModel(new MylistModel(lists));
		if (jList1.getSelectedValue() == null) {
			jList2.setModel(new javax.swing.AbstractListModel() {
				public int getSize() {
					return 0;
				}

				public Object getElementAt(int i) {
					return null;
				}
			});
		}

		jLabel5.setText("检索到" + (jList1.getModel().getSize()) + "条记录");
	}

	private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:帮助
		JFrame helpFrame = new Help();
		helpFrame.setTitle("帮助");
		it.businesslogic.ireport.util.Misc.centerFrame(helpFrame);
		helpFrame.setVisible(true);
	}

	private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:测试
		String classname = jTextField2.getText();
		String methodname = jTextField3.getText();
		if (IreportUtil.isBlank(classname)) {
			DialogFactory.showErrorMessageDialog(this, "类名必须填写", "错误");
			return;
		}

		if (IreportUtil.isBlank(methodname)) {
			DialogFactory.showErrorMessageDialog(this, "方法名必须填写", "错误");
			return;
		}

		JDialog paramDialog = new Params(this, this,
				true);
		it.businesslogic.ireport.util.Misc.centerFrame(paramDialog);
		paramDialog.setVisible(true);
		if (paramObj != null && paramObj.length == 0) {
			paramObj = null;
		}
		String p = "";
		if (paramObj != null && paramObj.length > 0) {
			for (int i = 0; i < paramObj.length; i++) {
				String thisp = null;
				if (paramObj[i] == null) {
					thisp = "null";
				} else {
					thisp = (String) paramObj[i];
				}
				p = p + thisp;
				if (i != paramObj.length - 1) {
					p = p + ",";
				}
			}
		} else {
			p = null;
		}
		try {
			AddedOperator.log("执行" + classname + "的" + methodname + "方法，参数为"
					+ p, IreportConstant.INFO_);
			IreportRmiClient.getInstance();
			Object object = IreportRmiClient.rmiInterfactRemote.invokeJavaBeanMehtoed(
					classname, methodname, paramObj);
			
			DialogFactory.showInfoMessageDialog(this, "测试成功\n获得返回值："+object, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			DialogFactory.showErrorMessageDialog(this, "测试失败\n"
					+ e.getMessage(), "测试失败");
		}

	}

	private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
		addDate();
	}

	private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
		this.dispose();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JavaBeanDatasourceSet dialog = new JavaBeanDatasourceSet(
						new javax.swing.JFrame(), true);
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
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JList jList1;
	private javax.swing.JList jList2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;

	// End of variables declaration

	private void removeDate() {
		MyReportProperties.removeProperties(IreportConstant.CLASS_NAME);
		MyReportProperties.removeProperties(IreportConstant.METHOD_NAME);
	}

	private void addDate() {
		String classname = jTextField2.getText();
		String methodname = jTextField3.getText();
		if (IreportUtil.isBlank(classname)) {
			DialogFactory.showErrorMessageDialog(this, "类名必须填写", "错误");
			return;
		}

		if (IreportUtil.isBlank(methodname)) {
			DialogFactory.showErrorMessageDialog(this, "方法名必须填写", "错误");
			return;
		}
		MyReportProperties.setProperties(IreportConstant.CLASS_NAME, classname);
		MyReportProperties.setProperties(IreportConstant.METHOD_NAME,
				methodname);
		this.setVisible(false);
		this.dispose();
	}

	class MylistModel extends AbstractListModel {
		List<String> lists;

		MylistModel(List<String> lists) {
			this.lists = lists;
		}

		public Object getElementAt(int index) {

			return lists.get(index);
		}

		public int getSize() {
			// TODO Auto-generated method stub
			return lists.size();
		}
	}
}
