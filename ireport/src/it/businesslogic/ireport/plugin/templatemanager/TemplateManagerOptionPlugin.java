package it.businesslogic.ireport.plugin.templatemanager;

import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.plugin.IReportPlugin;

/**
 *@author ¿Ó√Ø
 *@since  3.0
 *@version $Id: TemplateManagerOptionPlugin.java 2009 Sep 27, 2009 2:42:49 PM $
 */

//begin TemplateManagerOptionPlugin.java
public class TemplateManagerOptionPlugin extends IReportPlugin {

	@Override
	public void call() {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	   final TemplatesFrame dialog = new TemplatesFrame(MainFrame.getMainInstance(), true);
                   dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                       public void windowClosing(java.awt.event.WindowEvent e) {
                    	   dialog.setVisible(false);
                    	   dialog.dispose();
                       }
                   });
                   dialog.setVisible(true);
            	
            }
		   
    	});

	}

}

//end TemplateManagerOptionPlugin.java