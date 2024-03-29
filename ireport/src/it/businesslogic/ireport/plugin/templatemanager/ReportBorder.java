/*
 * ReportBorder.java
 *
 * Created on Oct 8, 2007, 1:54:02 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.businesslogic.ireport.plugin.templatemanager;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.border.Border;

/**
 *
 * @author gtoffoli
 */
public class ReportBorder implements Border {

   // private ReportObjectScene scene = null;

    private static Insets insets = new Insets(10, 10, 10, 10);

    public ReportBorder()
    {
       // this(null);
    }

   /* public ReportBorder(ReportObjectScene scene)
    {
        super();
        this.setScene(scene);
    }
*/

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {


    	/*
                int dh = ModelUtils.getDesignHeight(jd);
                int bdh = jd.getBackground().getHeight();
                bdh += jd.getTopMargin();
                bdh += jd.getBottomMargin();

                dh -= bdh;
                dh -= 20;

                paintShadowBorder(g, x, y, width, dh);
                paintShadowBorder(g, x, y+dh+20, width, bdh+20);

                return;*/



        paintShadowBorder(g, x, y, width, height);
    }

    public void paintShadowBorder(Graphics g, int x, int y, int width, int height) {

        // TOP ______________________________________________
        Rectangle2D r = new Rectangle2D.Double(x+10, y, width-20, 10);
        GradientPaint gp = new GradientPaint(
                 0f, (float)(y+2), new Color(0,0,0,0),
                 0f, (float)(y+9.5),  new Color(0,0,0,60)); //


        ((Graphics2D)g).setPaint(gp);
        ((Graphics2D)g).fill(r);

        // BOTTOM ______________________________________________
        r = new Rectangle2D.Double(x+10, y+height-10, width-20, 10);
        gp = new GradientPaint(
                 0f, (float)(r.getY()), new Color(0,0,0,60),
                 0f, (float)(r.getY()+7.5),  new Color(0,0,0,0)); //
        ((Graphics2D)g).setPaint(gp);
        ((Graphics2D)g).fill(r);

        // LEFT ______________________________________________
        r = new Rectangle2D.Double(x, y+10, 10, height-20);
        gp = new GradientPaint(
                 (float)(r.getX()+2),0f,  new Color(0,0,0,0),
                 (float)(r.getX()+9.5), 0f, new Color(0,0,0,60)); //
        ((Graphics2D)g).setPaint(gp);
        ((Graphics2D)g).fill(r);

        // RIGHT ______________________________________________
        r = new Rectangle2D.Double(x+width-10, y+10, 10, height-20);
        gp = new GradientPaint(
                 (float)(r.getX()),0f,  new Color(0,0,0,60),
                 (float)(r.getX()+7.5), 0f, new Color(0,0,0,0)); //
        ((Graphics2D)g).setPaint(gp);
        ((Graphics2D)g).fill(r);

        // TOP LEFT ______________________________________________
        r = new Rectangle2D.Double(x, y, 10, 10);
        RoundGradientPaint rgp = new RoundGradientPaint(x+9.8f, y+9.8f, new Color(0,0,0,60),
                new Point2D.Float(0,6.8f), new Color(0,0,0,0));

        ((Graphics2D)g).setPaint(rgp);
        ((Graphics2D)g).fill(r);

        // TOP RIGHT ______________________________________________
        r = new Rectangle2D.Double(x+width-10, y, 10, 10);
        rgp = new RoundGradientPaint(r.getX()+0.5, r.getY()+9.5f, new Color(0,0,0,60),
                new Point2D.Float(0,6.5f), new Color(0,0,0,0));

        ((Graphics2D)g).setPaint(rgp);
        ((Graphics2D)g).fill(r);

         // BOTTOM RIGHT ______________________________________________
        r = new Rectangle2D.Double(x+width-10,  y+height-10, 10, 10);
        rgp = new RoundGradientPaint(r.getX()+0.5, r.getY()+0.5f, new Color(0,0,0,60),
                new Point2D.Float(0,6.5f), new Color(0,0,0,0));

        ((Graphics2D)g).setPaint(rgp);
        ((Graphics2D)g).fill(r);

        r = new Rectangle2D.Double(x,  y+height-10, 10, 10);
        rgp = new RoundGradientPaint(r.getX()+9.5, r.getY()+0.5f, new Color(0,0,0,60),
                new Point2D.Float(0,6.5f), new Color(0,0,0,0));

        ((Graphics2D)g).setPaint(rgp);
        ((Graphics2D)g).fill(r);



        //((Graphics2D)g).setPaint(Color.RED);
        //((Graphics2D)g).draw(r);
    }

    public Insets getBorderInsets(Component c) {
        return insets;
    }

    public boolean isBorderOpaque() {
        return true;
    }

}
