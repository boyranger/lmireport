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
 * ExportPreferencesDialog.java
 *
 * Created on 5 maggio 2005, 18.26
 *
 */

package it.businesslogic.ireport.gui.export;

import it.businesslogic.ireport.gui.sheet.*;
import java.util.*;
import it.businesslogic.ireport.util.I18n;
import it.businesslogic.ireport.util.Misc;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

/**
 *
 * @author  Administrator
 */
public class ExportPreferencesDialog extends javax.swing.JDialog {

    private CategorySheetPanel categorySheetPanel = null;
    private java.util.Properties defaultValues = null;
    /** Creates new form ExportPreferencesDialog */
    public ExportPreferencesDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        applyI18n();    
        categorySheetPanel = new CategorySheetPanel();
        defaultValues = new java.util.Properties();

        //---------------------------------------------------------------------------------------------------
        
        categorySheetPanel.addSheetProperty( I18n.getString("elementPropertiesDialog.tab.Common","Common"),
                new SheetProperty("CHARACTER_ENCODING",I18n.getString("reportOptions.Encoding","Character encoding"), SheetProperty.STRING));
        defaultValues.setProperty("CHARACTER_ENCODING", "");
        categorySheetPanel.addSheetProperty( I18n.getString("elementPropertiesDialog.tab.Common","Common"),
                new SheetProperty("OFFSET_X",I18n.getString("reportOptions.offsetX","Offset X"), SheetProperty.INTEGER));
        defaultValues.setProperty("OFFSET_X", "0");
        categorySheetPanel.addSheetProperty( I18n.getString("elementPropertiesDialog.tab.Common","Common"),
                new SheetProperty("OFFSET_Y",I18n.getString("reportOptions.offsetY","Offset Y"), SheetProperty.INTEGER));
        defaultValues.setProperty("OFFSET_Y", "0");
        
        //---------------------------------------------------------------------------------------------------
        
        // Adding all properties...
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("PDF_IS_ENCRYPTED",I18n.getString("isEncrypted","Is Encrypted"), SheetProperty.BOOLEAN));
        defaultValues.setProperty("PDF_IS_ENCRYPTED", "false");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("PDF_IS_128_BIT_KEY",I18n.getString("is128BitKey", "Is 128 Bit Key"), SheetProperty.BOOLEAN));
        defaultValues.setProperty("PDF_IS_128_BIT_KEY", "false");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("PDF_USER_PASSWORD",I18n.getString("userPassword", "User Password"), SheetProperty.PASSWORD));
        defaultValues.setProperty("PDF_USER_PASSWORD", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("PDF_OWNER_PASSWORD",I18n.getString("ownerPassword", "Owner Password"), SheetProperty.PASSWORD));
        defaultValues.setProperty("PDF_OWNER_PASSWORD", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("PDF_PERMISSIONS",I18n.getString("permissions", "Permissions"), SheetProperty.INTEGER));
        defaultValues.setProperty("PDF_PERMISSIONS", "0");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty(JRPdfExporterParameter.PROPERTY_COMPRESSED,I18n.getString("isCompressed","Is Compressed"), SheetProperty.BOOLEAN));
        defaultValues.setProperty(JRPdfExporterParameter.PROPERTY_COMPRESSED, "false");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty(JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS,I18n.getString("isCreatingBatchModeBookmarks","Is Creating Batch Mode Bookmarks"), SheetProperty.BOOLEAN));
        defaultValues.setProperty(JRPdfExporterParameter.PROPERTY_CREATE_BATCH_MODE_BOOKMARKS, "false");
        
        ComboBoxSheetProperty combo = new ComboBoxSheetProperty(JRPdfExporterParameter.PROPERTY_PDF_VERSION,I18n.getString("pdfVersion","Pdf version"));
        Vector tags = new Vector();
        tags.add(new Tag("","Default"));
        tags.add(new Tag("2","Version 2"));
        tags.add(new Tag("3","Version 3"));
        tags.add(new Tag("4","Version 4"));
        tags.add(new Tag("5","Version 5"));
        tags.add(new Tag("6","Version 6"));
        combo.setTags(tags);
        categorySheetPanel.addSheetProperty("PDF Exporter", combo);
        defaultValues.setProperty(JRPdfExporterParameter.PROPERTY_PDF_VERSION, "");
        
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("METADATA_TITLE",I18n.getString("metadataTitle","Metadata Title"), SheetProperty.STRING));
        defaultValues.setProperty("METADATA_TITLE", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("METADATA_AUTHOR",I18n.getString("metadataAuthor","Metadata Author"), SheetProperty.STRING));
        defaultValues.setProperty("METADATA_AUTHOR", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("METADATA_SUBJECT",I18n.getString("metadataSubject","Metadata Subject"), SheetProperty.STRING));
        defaultValues.setProperty("METADATA_SUBJECT", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("METADATA_KEYWORDS",I18n.getString("metadataKeywords","Metadata Keywords"), SheetProperty.STRING));
        defaultValues.setProperty("METADATA_KEYWORDS", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty("METADATA_CREATOR",I18n.getString("metadataCreator","Metadata Creator"), SheetProperty.STRING));
        defaultValues.setProperty("METADATA_CREATOR", "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty(JRPdfExporterParameter.PROPERTY_FORCE_LINEBREAK_POLICY,I18n.getString("forceLinebreakPolicy","Force linebreak policy"), SheetProperty.STRING));
        defaultValues.setProperty(JRPdfExporterParameter.PROPERTY_FORCE_LINEBREAK_POLICY, "");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty(JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES,I18n.getString("forceSVGShapes","Force SVG Shapes"), SheetProperty.BOOLEAN));
        defaultValues.setProperty(JRPdfExporterParameter.PROPERTY_FORCE_SVG_SHAPES, "true");
        categorySheetPanel.addSheetProperty("PDF Exporter", new SheetProperty(JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT,I18n.getString("PDFJavaScript","PDF JavaScript"), SheetProperty.STRING));
        defaultValues.setProperty(JRPdfExporterParameter.PROPERTY_PDF_JAVASCRIPT, "");
                
        //---------------------------------------------------------------------------------------------------
        
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IMAGES_MAP","Images Map Object", SheetProperty.STRING));
        defaultValues.setProperty("HTML_IMAGES_MAP", "");
        ((SheetProperty)categorySheetPanel.getSheetProperty("HTML_IMAGES_MAP")).setReadOnly(true);
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IMAGES_DIR","Images Directory", SheetProperty.STRING));
        defaultValues.setProperty("HTML_IMAGES_DIR", "");
        ((SheetProperty)categorySheetPanel.getSheetProperty("HTML_IMAGES_DIR")).setReadOnly(true);
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IMAGES_DIR_NAME","Images Directory Name", SheetProperty.STRING));
        defaultValues.setProperty("HTML_IMAGES_DIR_NAME", "");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IS_OUTPUT_IMAGES_TO_DIR","Is Output Images to Directory Flag", SheetProperty.BOOLEAN));
        defaultValues.setProperty("HTML_IS_OUTPUT_IMAGES_TO_DIR", "false");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IMAGES_URI","Images URI", SheetProperty.STRING));
        defaultValues.setProperty("HTML_IMAGES_URI", "");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_HTML_HEADER","HTML Header", SheetProperty.STRING));
        defaultValues.setProperty("HTML_HTML_HEADER", "");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_BETWEEN_PAGES_HTML","Between Pages HTML", SheetProperty.STRING));
        defaultValues.setProperty("HTML_BETWEEN_PAGES_HTML", "");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_HTML_FOOTER","HTML Footer", SheetProperty.STRING));
        defaultValues.setProperty("HTML_HTML_FOOTER", "");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS","Is Remove Empty Space Between Rows", SheetProperty.BOOLEAN));
        defaultValues.setProperty("HTML_IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS", "false");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IS_WHITE_PAGE_BACKGROUND","Is White Page Background", SheetProperty.BOOLEAN));
        defaultValues.setProperty("HTML_IS_WHITE_PAGE_BACKGROUND", "ture");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty("HTML_IS_USING_IMAGES_TO_ALIGN","Is Using Images To Align", SheetProperty.BOOLEAN));
        defaultValues.setProperty("HTML_IS_USING_IMAGES_TO_ALIGN", "true");
        
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD,"Is Wrap Break Word", SheetProperty.STRING));
        defaultValues.setProperty(JRHtmlExporterParameter.PROPERTY_WRAP_BREAK_WORD, "");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT,"Size Unit", SheetProperty.STRING));
        defaultValues.setProperty(JRHtmlExporterParameter.PROPERTY_SIZE_UNIT, "px");
        categorySheetPanel.addSheetProperty("HTML Exporter", new SheetProperty(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES,"Export frames as nested tables", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JRHtmlExporterParameter.PROPERTY_FRAMES_AS_NESTED_TABLES, "true");

        //---------------------------------------------------------------------------------------------------
        
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty("XLS_IS_ONE_PAGE_PER_SHEET","Is One Page per Sheet", SheetProperty.BOOLEAN));
        defaultValues.setProperty("XLS_IS_ONE_PAGE_PER_SHEET", "false");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty("XLS_IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS","Is Remove Empty Space Between Rows", SheetProperty.BOOLEAN));
        defaultValues.setProperty("XLS_IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS", "false");
        
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty("XLS_IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS","Is Remove Empty Space Between Columns", SheetProperty.BOOLEAN));
        defaultValues.setProperty("XLS_IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS", "false");
        
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty("XLS_IS_WHITE_PAGE_BACKGROUND","Is White Page Background", SheetProperty.BOOLEAN));
        defaultValues.setProperty("XLS_IS_WHITE_PAGE_BACKGROUND", "true");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty("XLS_IS_AUTO_DETECT_CELL_TYPE","Auto Detect Cell Type (deprecated)", SheetProperty.BOOLEAN));
        defaultValues.setProperty("XLS_IS_AUTO_DETECT_CELL_TYPE", "false");
        
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_DETECT_CELL_TYPE,"Detect Cell Type (New)", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_DETECT_CELL_TYPE, "false");

        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_CREATE_CUSTOM_PALETTE,"Create Custom Palette", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_CREATE_CUSTOM_PALETTE, "true");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_CREATE_CUSTOM_PALETTE,"Create Custom Palette", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_FONT_SIZE_FIX_ENABLED, "false");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_MAXIMUM_ROWS_PER_SHEET,"Maximum Rows Per Sheet", SheetProperty.INTEGER));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_MAXIMUM_ROWS_PER_SHEET, "0");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_IGNORE_GRAPHICS,"Is Ignore Graphics", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_IGNORE_GRAPHICS, "false");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_COLLAPSE_ROW_SPAN,"Is Collapse Row Span", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_COLLAPSE_ROW_SPAN, "false");
        categorySheetPanel.addSheetProperty("XLS Exporter", new SheetProperty(JExcelApiExporterParameter.PROPERTY_IGNORE_CELL_BORDER,"Is Ignore Cell Border", SheetProperty.BOOLEAN));
        defaultValues.setProperty(JExcelApiExporterParameter.PROPERTY_IGNORE_CELL_BORDER, "false");
        
        //---------------------------------------------------------------------------------------------------
        
        categorySheetPanel.addSheetProperty("XML Exporter", new SheetProperty("XML_IS_EMBEDDING_IMAGES","Is Embedding Images Flag", SheetProperty.BOOLEAN));
        defaultValues.setProperty("XML_IS_EMBEDDING_IMAGES", "true");
        categorySheetPanel.addSheetProperty("XML Exporter", new SheetProperty("XML_DTD_LOCATION","DTD Location", SheetProperty.STRING));
        defaultValues.setProperty("XML_DTD_LOCATION", "");

        //---------------------------------------------------------------------------------------------------
        
        categorySheetPanel.addSheetProperty("Text Exporter", new SheetProperty("TXT_PAGE_ROWS","Rows per page", SheetProperty.INTEGER));
        defaultValues.setProperty("TXT_PAGE_ROWS", "61");
        categorySheetPanel.addSheetProperty("Text Exporter", new SheetProperty("TXT_PAGE_COLUMNS","Columns per page", SheetProperty.INTEGER));
        defaultValues.setProperty("TXT_PAGE_COLUMNS", "255");
        categorySheetPanel.addSheetProperty("Text Exporter", new SheetProperty("TXT_ADD_FORM_FEED","Add FORM FEED", SheetProperty.BOOLEAN));
        defaultValues.setProperty("TXT_ADD_FORM_FEED", "true");

        //---------------------------------------------------------------------------------------------------
                
        categorySheetPanel.addSheetProperty("CSV Exporter", new SheetProperty("CSV_FIELD_DELIMITER","Field Delimiter", SheetProperty.STRING));
        defaultValues.setProperty("CSV_RECORD_DELIMITER", ",");
        categorySheetPanel.addSheetProperty("CSV Exporter", new SheetProperty(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER,"Record Delimiter", SheetProperty.STRING));
        defaultValues.setProperty(JRCsvExporterParameter.PROPERTY_RECORD_DELIMITER, "\n");
        
        //---------------------------------------------------------------------------------------------------
        
        categorySheetPanel.addSheetProperty("JR Text Exporter", new SheetProperty("JRTXT_CHARACTER_WIDTH","Character Width", SheetProperty.INTEGER));
        defaultValues.setProperty("JRTXT_CHARACTER_WIDTH", "10");
        categorySheetPanel.addSheetProperty("JR Text Exporter", new SheetProperty("JRTXT_CHARACTER_HEIGHT","Character Height", SheetProperty.INTEGER));
        defaultValues.setProperty("JRTXT_CHARACTER_HEIGHT", "20");
        categorySheetPanel.addSheetProperty("JR Text Exporter", new SheetProperty("JRTXT_PAGE_WIDTH","Page Width", SheetProperty.INTEGER));
        defaultValues.setProperty("JRTXT_PAGE_WIDTH", "225");
        categorySheetPanel.addSheetProperty("JR Text Exporter", new SheetProperty("JRTXT_PAGE_HEIGHT","Page Height", SheetProperty.INTEGER));
        defaultValues.setProperty("JRTXT_PAGE_HEIGHT", "61");
        categorySheetPanel.addSheetProperty("JR Text Exporter", new SheetProperty("JRTXT_BETWEEN_PAGES_TEXT","Between Pages Text", SheetProperty.STRING));
        defaultValues.setProperty("JRTXT_BETWEEN_PAGES_TEXT", "\n\n");

        
        jPanel1.add("Center", categorySheetPanel);
        categorySheetPanel.setShowResetButton(false);
        
        //((SheetProperty)categorySheetPanel.getSheetProperty("HTML_IMAGES_DIR")).setShowResetButton(true); 
        ((SheetProperty)categorySheetPanel.getSheetProperty("HTML_IMAGES_DIR_NAME")).setShowResetButton(true);
        categorySheetPanel.recreateSheet();
        
        loadConfiguration();

        Misc.centerFrame( this );

        javax.swing.KeyStroke escape =  javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0, false);
        javax.swing.Action escapeAction = new javax.swing.AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                jButtonCancelActionPerformed(e);
            }
        };

        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);


        //to make the default button ...
        this.getRootPane().setDefaultButton(this.jButtonSave);
    }

    public void loadConfiguration()
    {
        java.util.Properties props = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getProperties();

        Enumeration p_props = categorySheetPanel.getProperties().elements();

        while (p_props.hasMoreElements())
        {
            SheetProperty sp = (SheetProperty)p_props.nextElement();
            String property_name = sp.getKeyName();

            if (props.getProperty(property_name) != null)
            {
                sp.setValue( props.getProperty(property_name) );
            }
            else if (defaultValues.getProperty(property_name) != null)
            {
                sp.setValue( defaultValues.getProperty(property_name) );
            }
        }
    }

    public void saveConfiguration()
    {
        java.util.Properties props = it.businesslogic.ireport.gui.MainFrame.getMainInstance().getProperties();

        Enumeration p_props = categorySheetPanel.getProperties().elements();

        while (p_props.hasMoreElements())
        {
            SheetProperty sp = (SheetProperty)p_props.nextElement();
            String property_name = sp.getKeyName();
            if (sp.getValue() != null && (sp.getValue()+"").length() > 0) props.setProperty(property_name, sp.getValue()+"");
            else props.remove(property_name);
        }

        it.businesslogic.ireport.gui.MainFrame.getMainInstance().saveiReportConfiguration();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        jPanel2.add(jPanel3, gridBagConstraints);

        jButtonSave.setText("Save");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 0);
        jPanel2.add(jButtonSave, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel2.add(jButtonCancel, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        this.saveConfiguration();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExportPreferencesDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }

    public CategorySheetPanel getCategorySheetPanel() {
        return categorySheetPanel;
    }

    public void setCategorySheetPanel(CategorySheetPanel categorySheetPanel) {
        this.categorySheetPanel = categorySheetPanel;
    }

    public javax.swing.JButton getJButtonCancel() {
        return jButtonCancel;
    }

    public void setJButtonCancel(javax.swing.JButton jButtonCancel) {
        this.jButtonCancel = jButtonCancel;
    }

    public javax.swing.JButton getJButtonSave() {
        return jButtonSave;
    }

    public void setJButtonSave(javax.swing.JButton jButtonSave) {
        this.jButtonSave = jButtonSave;
    }

    public javax.swing.JPanel getJPanel1() {
        return jPanel1;
    }

    public void setJPanel1(javax.swing.JPanel jPanel1) {
        this.jPanel1 = jPanel1;
    }

    public javax.swing.JPanel getJPanel2() {
        return jPanel2;
    }

    public void setJPanel2(javax.swing.JPanel jPanel2) {
        this.jPanel2 = jPanel2;
    }

    public javax.swing.JPanel getJPanel3() {
        return jPanel3;
    }

    public void setJPanel3(javax.swing.JPanel jPanel3) {
        this.jPanel3 = jPanel3;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    public void applyI18n(){
                // Start autogenerated code ----------------------
                jButtonCancel.setText(I18n.getString("exportPreferencesDialog.buttonCancel","Cancel"));
                jButtonSave.setText(I18n.getString("exportPreferencesDialog.buttonSave","Save"));
                // End autogenerated code ----------------------
                jButtonCancel.setMnemonic(I18n.getString("exportPreferencesDialog.buttonCancelMnemonic","c").charAt(0));
                jButtonSave.setMnemonic(I18n.getString("exportPreferencesDialog.buttonSaveMnemonic","s").charAt(0));
    }
    private Object I18;
}
