package it.businesslogic.ireport.plugin.login;

import it.businesslogic.ireport.gui.MainFrame;
import it.businesslogic.ireport.plugin.IReportPlugin;

import javax.swing.JFrame;

public class LoginOptionPlugin extends IReportPlugin {

	@Override
	public void call() {

		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final Login dialog = new Login(MainFrame.getMainInstance(), true);
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
