/*
 * ============================================================================
 * GNU Lesser General Public License
 * ============================================================================
 *
 * JasperReports - Free Java report-generating library.
 * Copyright (C) 2001-2006 JasperSoft Corporation http://www.jaspersoft.com
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * JasperSoft Corporation
 * 303 Second Street, Suite 450 North
 * San Francisco, CA 94107
 * http://www.jaspersoft.com
 */
package net.sf.jasperreports.crosstabs.base;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstab;
import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabDataset;
import net.sf.jasperreports.crosstabs.JRCrosstabGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRExpressionCollector;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.JRVisitor;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.util.JRStyleResolver;

/**
 * Base read-only {@link net.sf.jasperreports.crosstabs.JRCrosstab crosstab} implementation.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRBaseCrosstab.java 2006 2007-12-05 14:28:33Z teodord $
 */
public class JRBaseCrosstab extends JRBaseElement implements JRCrosstab
{
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	public static final String PROPERTY_RUN_DIRECTION = "runDirection";

	protected int id;
	protected JRCrosstabParameter[] parameters;
	protected JRVariable[] variables;
	protected JRExpression parametersMapExpression;
	protected JRCrosstabDataset dataset;
	protected JRCrosstabRowGroup[] rowGroups;
	protected JRCrosstabColumnGroup[] columnGroups;
	protected JRCrosstabMeasure[] measures;
	protected int columnBreakOffset;
	protected boolean repeatColumnHeaders = true;
	protected boolean repeatRowHeaders = true;
	protected byte runDirection;
	protected JRCrosstabCell[][] cells;
	protected JRCellContents whenNoDataCell;
	protected JRCellContents headerCell;
	
	public JRBaseCrosstab(JRCrosstab crosstab, JRBaseObjectFactory factory, int id)
	{
		super(crosstab, factory);
		
		this.id = id;
		
		this.columnBreakOffset = crosstab.getColumnBreakOffset();
		this.repeatColumnHeaders = crosstab.isRepeatColumnHeaders();
		this.repeatRowHeaders = crosstab.isRepeatRowHeaders();
		this.runDirection = crosstab.getRunDirection();
		
		this.dataset = factory.getCrosstabDataset(crosstab.getDataset());
		
		copyParameters(crosstab, factory);		
		copyVariables(crosstab, factory);		
		headerCell = factory.getCell(crosstab.getHeaderCell());
		copyRowGroups(crosstab, factory);		
		copyColumnGroups(crosstab, factory);
		copyMeasures(crosstab, factory);
		copyCells(crosstab, factory);
		
		whenNoDataCell = factory.getCell(crosstab.getWhenNoDataCell());
	}

	/**
	 *
	 */
	public byte getMode()
	{
		return JRStyleResolver.getMode(this, MODE_TRANSPARENT);
	}
	
	private void copyParameters(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabParameter[] crossParameters = crosstab.getParameters();
		if (crossParameters != null)
		{
			parameters = new JRCrosstabParameter[crossParameters.length];
			for (int i = 0; i < parameters.length; i++)
			{
				parameters[i] = factory.getCrosstabParameter(crossParameters[i]);
			}
		}
		
		parametersMapExpression = factory.getExpression(crosstab.getParametersMapExpression());
	}

	private void copyVariables(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRVariable[] vars = crosstab.getVariables();
		if (vars != null)
		{
			variables = new JRVariable[vars.length];
			for (int i = 0; i < vars.length; i++)
			{
				variables[i] = factory.getVariable(vars[i]);
			}
		}
	}

	private void copyRowGroups(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabRowGroup[] crossRowGroups = crosstab.getRowGroups();
		if (crossRowGroups != null)
		{
			this.rowGroups = new JRCrosstabRowGroup[crossRowGroups.length];
			for (int i = 0; i < crossRowGroups.length; ++i)
			{
				this.rowGroups[i] = factory.getCrosstabRowGroup(crossRowGroups[i]);
			}
		}
	}

	private void copyColumnGroups(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabColumnGroup[] crossColumnGroups = crosstab.getColumnGroups();
		if (crossColumnGroups != null)
		{
			this.columnGroups = new JRCrosstabColumnGroup[crossColumnGroups.length];
			for (int i = 0; i < crossColumnGroups.length; ++i)
			{
				this.columnGroups[i] = factory.getCrosstabColumnGroup(crossColumnGroups[i]);
			}
		}
	}

	private void copyMeasures(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabMeasure[] crossMeasures = crosstab.getMeasures();
		if (crossMeasures != null)
		{
			this.measures = new JRCrosstabMeasure[crossMeasures.length];
			for (int i = 0; i < crossMeasures.length; ++i)
			{
				this.measures[i] = factory.getCrosstabMeasure(crossMeasures[i]);
			}
		}
	}

	private void copyCells(JRCrosstab crosstab, JRBaseObjectFactory factory)
	{
		JRCrosstabCell[][] crossCells = crosstab.getCells();
		if (crossCells != null)
		{
			this.cells = new JRCrosstabCell[rowGroups.length + 1][columnGroups.length + 1];
			for (int i = 0; i <= rowGroups.length; i++)
			{
				for (int j = 0; j <= columnGroups.length; ++j)
				{
					this.cells[i][j] = factory.getCrosstabCell(crossCells[i][j]);
				}
			}
		}
	}
	
	public int getId()
	{
		return id;
	}

	public JRCrosstabDataset getDataset()
	{
		return dataset;
	}

	public JRCrosstabRowGroup[] getRowGroups()
	{
		return rowGroups;
	}

	public JRCrosstabColumnGroup[] getColumnGroups()
	{
		return columnGroups;
	}

	public JRCrosstabMeasure[] getMeasures()
	{
		return measures;
	}

	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}

	public void visit(JRVisitor visitor)
	{
		visitor.visitCrosstab(this);
	}

	public int getColumnBreakOffset()
	{
		return columnBreakOffset;
	}

	public boolean isRepeatColumnHeaders()
	{
		return repeatColumnHeaders;
	}

	public boolean isRepeatRowHeaders()
	{
		return repeatRowHeaders;
	}

	public JRCrosstabCell[][] getCells()
	{
		return cells;
	}

	public JRCrosstabParameter[] getParameters()
	{
		return parameters;
	}

	public JRExpression getParametersMapExpression()
	{
		return parametersMapExpression;
	}

	public JRCellContents getWhenNoDataCell()
	{
		return whenNoDataCell;
	}
	
	public static JRElement getElementByKey(JRCrosstab crosstab, String key)
	{
		JRElement element = null;
		
		if (crosstab.getHeaderCell() != null)
		{
			element = crosstab.getHeaderCell().getElementByKey(key);
		}
		
		if (element == null)
		{
			element = getHeadersElement(crosstab.getRowGroups(), key);
		}		

		if (element == null)
		{
			element = getHeadersElement(crosstab.getColumnGroups(), key);
		}
		
		if (element == null)
		{
			if (crosstab instanceof JRDesignCrosstab)
			{
				List cellsList = ((JRDesignCrosstab) crosstab).getCellsList();
				for (Iterator it = cellsList.iterator(); element == null && it.hasNext();)
				{
					JRCrosstabCell cell = (JRCrosstabCell) it.next();
					element = cell.getContents().getElementByKey(key);
				}
			}
			else
			{
				JRCrosstabCell[][] cells = crosstab.getCells();
				for (int i = cells.length - 1; element == null && i >= 0; --i)
				{
					for (int j = cells[i].length - 1; element == null && j >= 0; --j)
					{
						JRCrosstabCell cell = cells[i][j];
						if (cell != null)
						{
							element = cell.getContents().getElementByKey(key);
						}
					}
				}
			}
		}
		
		if (element == null && crosstab.getWhenNoDataCell() != null)
		{
			element = crosstab.getWhenNoDataCell().getElementByKey(key);
		}
		
		return element;
	}

	private static JRElement getHeadersElement(JRCrosstabGroup[] groups, String key)
	{
		JRElement element = null;
		
		if (groups != null)
		{
			for (int i = 0; element == null && i < groups.length; i++)
			{
				JRCellContents header = groups[i].getHeader();
				element = header.getElementByKey(key);
				
				if (element == null)
				{
					JRCellContents totalHeader = groups[i].getTotalHeader();
					element = totalHeader.getElementByKey(key);
				}
			}
		}
		
		return element;
	}

	
	public JRElement getElementByKey(String elementKey)
	{
		return getElementByKey(this, elementKey);
	}

	public JRCellContents getHeaderCell()
	{
		return headerCell;
	}

	public JRVariable[] getVariables()
	{
		return variables;
	}

	
	public byte getRunDirection()
	{
		return runDirection;
	}

	
	public void setRunDirection(byte runDirection)
	{
		byte old = this.runDirection;
		this.runDirection = runDirection;
		getEventSupport().firePropertyChange(PROPERTY_RUN_DIRECTION, old, this.runDirection);
	}

	/**
	 * 
	 */
	public Object clone() 
	{
		return null;//FIXMECLONE: implement this");
	}
}
