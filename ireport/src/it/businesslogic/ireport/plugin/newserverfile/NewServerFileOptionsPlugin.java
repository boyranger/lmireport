/*
 * Copyright (C) 2005 - 2008 JasperSoft Corporation.  All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from JasperSoft,
 * the following license terms apply:
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 *
 * This program is distributed WITHOUT ANY WARRANTY; and without the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/gpl.txt
 * or write to:
 *
 * Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330,
 * Boston, MA  USA  02111-1307
 *
 *
 *
 *
 * OracleOptionsPlugin.java
 *
 * Created on 21 maggio 2004, 7.36
 *
 */

package it.businesslogic.ireport.plugin.newserverfile;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 *
 * @author  Administrator
 */
public class NewServerFileOptionsPlugin extends it.businesslogic.ireport.plugin.IReportPlugin {

    /** Creates a new instance of HelloWorld */
    public NewServerFileOptionsPlugin() {
    }

    public void call() {
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	JFrame frame = null;
            	frame = new JFrame("新建本地文件到服务器");
            	frame.setLayout(new BorderLayout());
        		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        		// Add content to the window.
        		frame.add(new NewServerFileOptions(frame),BorderLayout.CENTER);

        		// Display the window.
        		//frame.m
        		frame.setMaximumSize(new Dimension(400,300));
        		frame.setPreferredSize(new Dimension(400,300));
        		frame.pack();
        		it.businesslogic.ireport.util.Misc.centerFrame(frame);
        		frame.setVisible(true);
            }
        });
    }*/
    	
    	 java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
                 final JFrame dialog = new NewServrtFile();
                 dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                     public void windowClosing(java.awt.event.WindowEvent e) {
                    	 dialog.dispose();
                     }
                 });
                 it.businesslogic.ireport.util.Misc.centerFrame(dialog);
                 dialog.setTitle("新建本地文件到服务器");
                 dialog.setVisible(true);
             }
         });
    }
    public void configure() {

    }

}
