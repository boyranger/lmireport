package a.b.c;
import java.awt.*;
  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;
  import javax.swing.*;
 public class Main extends JFrame implements ActionListener
 {
     private ImagePanel imgPane = null;
     private JScrollPane scrollPane = null;
     private JButton zoomin = null ;
     private JButton zoomout = null;
    public Main ()
     {
         super ("JScrollPane Demo");
         imgPane = new ImagePanel ("d:/t/tree.png");
         System.out.println(imgPane);
         imgPane.setPreferredSize (new Dimension (600, 400));
         scrollPane = new JScrollPane (imgPane);
         scrollPane.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scrollPane.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         zoomin = new JButton ("∑≈¥Û");
         zoomout = new JButton ("Àı–°");
         zoomin.setBounds (0,10,60,30);
         zoomout.setBounds (80,10,60,30);
         zoomin.addActionListener (this);
         zoomout.addActionListener (this);
         imgPane.add (zoomout,JLayeredPane.DRAG_LAYER);
         imgPane.add (zoomin,JLayeredPane.DRAG_LAYER);
         this.add (scrollPane);
         setSize (600, 400);
         setDefaultCloseOperation (EXIT_ON_CLOSE);
         setVisible (true);
     }
     public static void main ( String[] args )
     {
    	 try {
    		 new Main ();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
     }
     public void actionPerformed ( ActionEvent e )
     {
         if ((JButton) (e.getSource ()) == zoomin)
         {
            imgPane.enlarge ();
            imgPane.setPreferredSize (imgPane.getPreferredSize ());
             scrollPane.validate ();
         }
         else if ((JButton) (e.getSource ()) == zoomout)
        {
            imgPane.ensmall ();
            imgPane.setPreferredSize (imgPane.getPreferredSize ());
             scrollPane.validate ();
         }
     }
 }
  class ImagePanel extends JLayeredPane
 {
     private Dimension theSize = new Dimension (600, 400);
     private ImageIcon img = null;
     public ImagePanel (String imgpath )
     {
        
    	 super ();
         setLayout (null);
         
         //ImageIcon MyImage = new ImageIcon(ImageIO.read(file_or_url_or_inputstream));
         //Image   imgss   =     Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(imgpath));   
         
         this.img = new ImageIcon (imgpath);
     }
     public void paintComponent ( Graphics g )
     {
         g.clearRect (0, 0, 1024, 768);
         g.drawImage (img.getImage (), 0, 0, theSize.width, theSize.height,null);
     }
     public void enlarge ( )
     {
         theSize.width = (theSize.width * 101) / 100;
         theSize.height = (theSize.height * 101) / 100;
         setSize (theSize);
     }
     public void ensmall ( )
     {
         theSize.width = (theSize.width * 100) / 101;
         theSize.height = (theSize.height * 100) / 101;
         setSize (theSize);
     }
     public Dimension getPreferredSize ( )
     {
         return this.theSize;
     }
 }
 