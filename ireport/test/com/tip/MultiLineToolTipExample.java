package com.tip;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolTip;

public class MultiLineToolTipExample extends JFrame {
	public MultiLineToolTipExample() {
		super("Multi-Line   ToolTip   Example");
		JButton button = new JButton("Hello,   world") {
			public JToolTip createToolTip() {
				MultiLineToolTip tip = new MultiLineToolTip();
				tip.setComponent(this);
				return tip;
			}
		};
		button.setToolTipText("Hello\nworld");
		getContentPane().add(button);
	}

	public static void main(String args[]) {
		MultiLineToolTipExample f = new MultiLineToolTipExample();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setSize(300, 100);
		f.show();
	}
}