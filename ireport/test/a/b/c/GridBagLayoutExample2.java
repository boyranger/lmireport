package a.b.c;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class GridBagLayoutExample2 extends JPanel {

	public GridBagLayoutExample2() {

		this.setLayout(new GridBagLayout());
		this.setOpaque(true);
		GridBagConstraints c = new GridBagConstraints();
		JButton b = new JButton("One");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(15,10,4,4);
		this.add(b, c);// button 1 added
		c.gridy++;
		b = new JButton("Two");
		this.add(b, c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 2;
		b = new JButton("Three");
		this.add(b, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 4;
		c.gridheight = 1;
		this.add(new JTextField(35), c);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("GridBagLayout 2");
		JPanel p = new GridBagLayoutExample2();
		f.getContentPane().add(p);
		f.pack();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}