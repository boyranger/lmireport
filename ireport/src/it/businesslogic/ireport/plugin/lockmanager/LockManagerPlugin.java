package it.businesslogic.ireport.plugin.lockmanager;

import com.chinacreator.ireport.component.DialogFactory;

import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.plugin.IReportPlugin;

/**
 *@author 李茂
 *@since  3.0
 *@version $Id: LockManagerPlugin.java 2009 Sep 30, 2009 10:15:09 AM $
 */

//begin LockManagerPlugin.java
public class LockManagerPlugin extends IReportPlugin {

	@Override
	public void call() {
		try {
			// TODO: handle exception
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					final LockDialog dialog = new LockDialog(MainFrame.getMainInstance(),
							true);
					dialog.addWindowListener(new java.awt.event.WindowAdapter() {
						public void windowClosing(java.awt.event.WindowEvent e) {
							dialog.setVisible(false);
							dialog.dispose();
						}
					});
					it.businesslogic.ireport.util.Misc.centerFrame(dialog);	
					dialog.setVisible(true);
				}
			});
		} catch (Exception e) {
			DialogFactory.showErrorMessageDialog(null, "初始化界面出错："+e.getMessage(), "错误");
		}

	}

}

//end LockManagerPlugin.java