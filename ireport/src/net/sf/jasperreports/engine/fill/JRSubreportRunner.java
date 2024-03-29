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
package net.sf.jasperreports.engine.fill;

import net.sf.jasperreports.engine.JRException;


/**
 * Subreport runner interface.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JRSubreportRunner.java 1229 2006-04-19 10:27:35Z teodord $
 */
public interface JRSubreportRunner
{
	/**
	 * Decides whether the subreport filling has ended or not.
	 * 
	 * @return <code>true</code> iff the subreport filling has not ended
	 */
	boolean isFilling();

	/**
	 * Starts to fill the subreport.
	 * <p>
	 * This method is always called by a thread owning the lock on the subreport filler.
	 * 
	 * @return the result of the fill process
	 * @throws JRException
	 */
	JRSubreportRunResult start() throws JRException;

	/**
	 * Resumes the filling of a subreport.
	 * <p>
	 * This method is called after the fill has been suspended by
	 * {@link #suspend() suspend} and the subreport should continue on the new page. 
	 * <p>
	 * This method is always called by a thread owning the lock on the subreport filler.
	 * 
	 * @return the result of the fill process
	 * @throws JRException
	 */
	JRSubreportRunResult resume() throws JRException;

	/**
	 * Resets the runner, preparing it for a new fill.
	 * 
	 * @throws JRException
	 */
	void reset() throws JRException;

	/**
	 * Cancels the current fill process.
	 * <p>
	 * This method is called when a subreport is placed on a non splitting band
	 * and needs to rewind.
	 * <p>
	 * This method is always called by a thread owning the lock on the subreport filler.
	 * 
	 * @throws JRException
	 */
	void cancel() throws JRException;

	/**
	 * Suspends the current fill.
	 * <p>
	 * This method is called when the subreport reaches the end of a page
	 * and needs to wait for the master to create a new page.
	 * <p>
	 * This method is always called by a thread owning the lock on the subreport filler.
	 * 
	 * @throws JRException
	 */
	void suspend() throws JRException;	
}
