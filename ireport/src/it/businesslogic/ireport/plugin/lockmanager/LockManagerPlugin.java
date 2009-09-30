package it.businesslogic.ireport.plugin.lockmanager;

import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.plugin.IReportPlugin;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: LockManagerPlugin.java 2009 Sep 30, 2009 10:15:09 AM $
 */

//begin LockManagerPlugin.java
public class LockManagerPlugin extends IReportPlugin {

	@Override
	public void call() {
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

	}

}

//end LockManagerPlugin.java