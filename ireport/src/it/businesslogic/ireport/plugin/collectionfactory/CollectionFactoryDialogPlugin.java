package it.businesslogic.ireport.plugin.collectionfactory;

import it.businesslogic.ireport.plugin.IReportPlugin;
import it.businesslogic.ireport.util.Misc;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: CollectionFactoryDialogPlugin.java 2009 Oct 23, 2009 4:18:23 PM $
 */

//begin CollectionFactoryDialogPlugin.java
public class CollectionFactoryDialogPlugin extends IReportPlugin {

	@Override
	public void call() {
		// TODO Auto-generated method stub
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				final CollectionFactoryDialog dialog = new CollectionFactoryDialog(new javax.swing.JFrame(),
						true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						dialog.dispose();
					}
				});
				Misc.centerFrame(dialog);
				dialog.setVisible(true);
			}
		});
	}

}

//end CollectionFactoryDialogPlugin.java