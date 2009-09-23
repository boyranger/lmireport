package it.businesslogic.ireport.plugin.login;

import it.businesslogic.ireport.plugin.IReportPlugin;

import javax.swing.JFrame;

public class LoginOptionPlugin extends IReportPlugin {

	@Override
	public void call() {

		java.awt.EventQueue.invokeLater(new Runnable() {
	         public void run() {
	                new LoginOptions(it.businesslogic.ireport.gui.MainFrame.getMainInstance() , true).setVisible(true);
	            }
	        });

	}

	public static void main(String[] args) {
		new LoginOptionPlugin().call();
		final JFrame  f = new JFrame("传说中的frame");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.setVisible(true);
		//new LoginOptions(f,true);
		java.awt.EventQueue.invokeLater(new Runnable() {
	         public void run() {
	                new LoginOptions(f , true).setVisible(true);
	            }
	        });

	}

}
