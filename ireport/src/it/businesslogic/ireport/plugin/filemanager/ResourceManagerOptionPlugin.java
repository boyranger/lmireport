package it.businesslogic.ireport.plugin.filemanager;

import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.plugin.IReportPlugin;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: ResourceManagerOptionPlugin.java 2009 Sep 30, 2009 3:07:00 PM $
 */

//begin ResourceManagerOptionPlugin.java
public class ResourceManagerOptionPlugin extends IReportPlugin {

	@Override
	public void call() {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final ResourceManager dialog = new ResourceManager(MainFrame.getMainInstance(), true);
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

//end ResourceManagerOptionPlugin.java