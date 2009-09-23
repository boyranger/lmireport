package a.b.c;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ImageFrame extends javax.swing.JFrame {
 private Canvas canvas;
 private JButton smaller;
 private JButton bigger;
 private float scale=1f;
 /**
 * Auto-generated main method to display this JFrame
 */
 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable() {
   public void run() {
    ImageFrame inst = new ImageFrame();
    inst.setLocationRelativeTo(null);
    inst.setVisible(true);
   }
  });
 }
 
 public ImageFrame() {
  super();
  initGUI();
 }
 Image image;
 private void initGUI() {
  try {
   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
   getContentPane().setLayout(null);
   {
    Toolkit tk=getToolkit();
    
    image=Toolkit.getDefaultToolkit().createImage("d:\t\tree.png");
    //image = ImageIO.read(new File("d:\t\tree.png"));
    
    
    System.out.println(image);
    //这里的地址填上你图片的位置
   }
   {
    canvas = new Canvas(){
     public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2d=(Graphics2D) g;
      AffineTransform at=new AffineTransform();
      at.scale(scale,scale);
      g2d.setTransform(at);
      g2d.drawImage(image,at,ImageFrame.this);
     }
     
    };
    getContentPane().add(canvas);
    canvas.setBounds(0, 0, 500, 514);
   }
   {
    bigger = new JButton();
    getContentPane().add(bigger);
    bigger.setText("放   大");
    bigger.setBounds(79, 526, 109, 28);
    bigger.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
      if(scale<5)
       scale+=0.1;
      canvas.repaint();
     }});
   }
   {
    smaller = new JButton();
    getContentPane().add(smaller);
    smaller.setText("缩   小");
    smaller.setBounds(310, 526, 109, 28);
    smaller.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
      if(scale>0.5)
       scale-=0.1;
      canvas.repaint();
     }});
   }
   pack();
   setSize(510, 600);
  } catch (Exception e) {
   e.printStackTrace();
  }
 }

} 