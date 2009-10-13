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
 * PersonBeansDataSource.java
 * 
 */

package it.businesslogic.ireport.examples;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
import it.businesslogic.ireport.examples.beans.*;
import java.util.*;

public class PersonBeansDataSource extends JRAbstractBeanDataSourceProvider {
  
  	public PersonBeansDataSource() {
  		super(PersonBean.class);
                
        }
        
        public JRField[] getFields(JasperReport report) throws JRException
        {
        	System.out.println("-----------------1111");
            
            if (report != null)
            {
                System.out.println(report);
                JRParameter[] params = report.getParameters();
                for (int i=0; i<params.length; ++i)
                {
                    System.out.println(params[i].getName()+" "+params[i].getDefaultValueExpression());
                }

                String[] properties = report.getPropertyNames();
                for (int i=0; i<properties.length; ++i)
                {
                    System.out.println(properties[i] + " = " + report.getProperty(properties[i]));
                }
            }
            return super.getFields(report);
        }
  
        
        
  	public JRDataSource create(JasperReport report) throws JRException {
  		System.out.println("222222--sdasdasdasdasdasdasdasd---------");
  		ArrayList list = new ArrayList();
  		list.add(new PersonBean("Aldo","dasdasdas"));
  		list.add(new PersonBean("Giovanni","hrthrthrt"));
  		//list.add(new PersonBean("Giacomo","ghjfghjghj"));
        return new JRBeanCollectionDataSource(list);
  	}
 	
  	public void dispose(JRDataSource dataSource) throws JRException {
 		// nothing to do
  	}
  }
