package ConcertHallBookingSystem;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;

/* A block holder is basically a grid layout with 1 column and 3 rows 
 * i.e. 3 cells. Each cell contains a seating block; gold in the top cell, 
 * silver in the middle and bronze at the bottom
 */

public class BlockHolder extends JPanel {
     
    private Seat[] seats;
    
    private SeatingBlock goldBlock;
    private SeatingBlock silverBlock;
    private SeatingBlock bronzeBlock;
    
    public BlockHolder(Seat[] seats){
        super.setLayout(new GridBagLayout());
        
        //create three blocks of seats
        goldBlock = new GoldSeatingBlock(seats);
        silverBlock = new SilverSeatingBlock(seats);
        bronzeBlock = new BronzeSeatingBlock(seats);
        
        configureLayout();
    }
    
    private void configureLayout(){
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.VERTICAL;
        
        gc.gridx = 0;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        
        addBlock(goldBlock, gc, 0);
        addBlock(silverBlock, gc, 1);
        addBlock(bronzeBlock, gc, 2);//add within seating block holder
    }
    
    private void addBlock(SeatingBlock block, GridBagConstraints gc, int column){
        gc.gridy = column;
        super.add(block, gc);
    }

    //copies the most up to date seats for an individual block into an array 
    //which contains all seats
    private void setThirtySeats(Seat[] seats){
                
        int i = 0;
        int j = 0;
        
        //finds the next position to add more seats
        while(this.seats[j] != null && j < 90){
               ++j;
        }

        /*
        while there are 30 seats, and if some (or all) of the seats are booked, then fill
        what is found into Seats array
        */
        while(i < 30 && seats[i] != null)
        {
            if(seats[i].isBooked() == true)
            {
                this.seats[j] = seats[i];
                ++j;
            }
            ++i;
        } 
    }
    
    //taking 30 seats and putting it into an array of 90
    private void setAllSeats(){
        this.seats = new Seat[90];
        setThirtySeats(goldBlock.getSeats());
        setThirtySeats(silverBlock.getSeats());
        setThirtySeats(bronzeBlock.getSeats());
    }
    
    public Seat[] getSeats(){
        setAllSeats();
        return seats;
    }    

}//end class BlockHolder
