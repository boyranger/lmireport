package it.businesslogic.ireport.examples.chart;

import java.awt.Font;

import net.sf.jasperreports.engine.JRChart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.renderer.category.CategoryItemRenderer;

/**
 *
 * @author gtoffoli
 */
public class IReportCustomizer implements net.sf.jasperreports.engine.JRChartCustomizer {


    public void customize(JFreeChart jFreeChart, JRChart jRChart) {

        CategoryItemRenderer renderer = jFreeChart.getCategoryPlot().getRenderer();
        renderer.setPositiveItemLabelPosition(
                new ItemLabelPosition( ItemLabelAnchor.OUTSIDE12, org.jfree.ui.TextAnchor.BOTTOM_CENTER) );
        renderer.setItemLabelFont(new Font("SansSerif", Font.BOLD, 10));
        renderer.setItemLabelsVisible(true);
    }

}
