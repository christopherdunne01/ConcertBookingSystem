package ConcertHallBookingSystem;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Stage extends JPanel {
    
    private Dimension parentFrameSize;
    
    public Stage(){
        super();
        parentFrameSize = getSize();
        setListener();
        repaint();
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        g2.setColor(new Color(111, 81, 28));//brown

        
        int frameWidth = (int) parentFrameSize.getWidth();
        int frameHeight = (int) parentFrameSize.getHeight();
        
        
        int stageX = 50;
        int stageY = 0;
        int stageWidth = (int) (frameWidth - 100);
        int stageHeight = (int) (frameHeight - 50);
        
        //stage
        Rectangle rect = new Rectangle(stageX, stageY, stageWidth, stageHeight);
        g2.fill(rect);
    }
    
    private void setListener(){
            
        this.addComponentListener(new ComponentListener(){
        
            public void componentHidden(ComponentEvent e) {

            }

            public void componentMoved(ComponentEvent e) {

            }

            public void componentResized(ComponentEvent e) {
                parentFrameSize = getSize();
                repaint();
            }

            public void componentShown(ComponentEvent e) {

            }
        });
   
    }//end setListener

}//end Stage
