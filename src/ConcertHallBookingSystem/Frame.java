package ConcertHallBookingSystem;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.WindowEvent;
import java.awt.event.*;

public class Frame extends JFrame {   
        
    private FileManager fManager = new FileManager();
    private Concert concert;
    
    private JMenuBar theMenu = new JMenuBar();

    private JMenuItem newConcert = new JMenuItem("New Concert");
    private JMenuItem exit = new JMenuItem("Exit");
    private JMenuItem totalSales = new JMenuItem("Total Sales");   
    private JMenuItem queryByName = new JMenuItem("Query by Name");

    private JMenu concertMenu = new JMenu("Concert");
    private JMenu reportMenu = new JMenu("Report");

    public Frame() {
        
        super("Concert Hall Booking System"); 
   
        this.setSize(1000, 1000);
        
        configureMenu();
        setListeners();
        
        concert = fManager.getFileContents();
        
        //if no concert it would make one with default values
        try
        {
            
            add(concert);
            
        } catch(NullPointerException e){
            concert = new Concert(new Seat[90], "no title", "nodate", 
                20.0f, 15.0f, 10.0f);
        }
        setVisible(true);               
    }//end frame
    
   
    private void setListeners(){
        exit.addActionListener(new ActionListener(){       
            public void actionPerformed(ActionEvent e)
            {
                fManager.setFileContents(concert);//write to file as you "exit"
                System.exit(0);
            }
        });
        
        addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e)
                {
                    fManager.setFileContents(concert); //write to file as you click x(top left)
                    System.exit(0);
                }
        });      
        
        
        totalSales.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                SalesReport reportW = new SalesReport(concert);
            }
        });
        
        newConcert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                NewConcertDialog newConcert = new NewConcertDialog(Frame.this);
            }
        });
        
        queryByName.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                SearchReport reportW = new SearchReport(concert.getSeats());
            }
        });
    
    }//end setListeners
    
    
    private void configureMenu(){
        theMenu = new JMenuBar();

        newConcert = new JMenuItem("New Concert");
        exit = new JMenuItem("Exit");
        totalSales = new JMenuItem("Total Sales");   
        queryByName = new JMenuItem("Query by Name");

        concertMenu = new JMenu("Concert");
        reportMenu = new JMenu("Report");    
        
        concertMenu.add(newConcert);
        concertMenu.add(exit);
        
        reportMenu.add(totalSales);
        reportMenu.add(queryByName);
       
        theMenu.add(concertMenu);
        theMenu.add(reportMenu);
                       
        setJMenuBar(theMenu);
        setVisible(true);
    }
    
    public void removeOldConcert(){
        remove(concert);
    }
    
    public void setConcert(Concert concert){
        this.concert = concert;
    } 

}//end class Frame