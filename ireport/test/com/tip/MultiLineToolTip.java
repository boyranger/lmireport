package com.tip;
import   java.awt.*;   
  import   javax.swing.*;   
    
  public   class   MultiLineToolTip   extends   JToolTip   {   
      public   MultiLineToolTip()   {       
          setUI(new   MultiLineToolTipUI());   
      }   
  } 