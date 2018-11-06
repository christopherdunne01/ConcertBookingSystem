package ConcertHallBookingSystem;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/* A seating block is a 12 x 3 'grid' of seats. The middle two columns 6 & 7 are 
 * empty labels used to represent the central aisle
 */
public abstract class SeatingBlock extends JPanel {
         
    protected Seat[] seats = new Seat[30];
    protected JLabel aisle = new JLabel();
     
    protected abstract void createEmptySeats();
    protected abstract void assignRowLetter(int row, Seat seat);
    protected abstract void copySeatInformation(Seat[] seats);
    
    public SeatingBlock(Seat[] seats){
        super(new GridBagLayout());
        createEmptySeats(); //Create Seats from gold, silver and bronze 
        copySeatInformation(seats);//get data from array 
        displaySeats();
    }
    
    public Seat[] getSeats(){
        return seats;
    }
   
    /* Adds each seat to it's respective cell within the 'grid' of seats. 
     * Seats are added right to left, beginning at the top right cell (11, 0), 
     * before moving down to the next row and repeating the right to left 
     * movement
     */
    protected void displaySeats(){
                                      
        GridBagConstraints gc = new GridBagConstraints();
        
        int i = 0;
        int row = 0;
        int column = 11;

        while(row < 3)
        {
            while(column > -1)
            {
                gc.weightx = 1.0;
                gc.weighty = 1.0;
                gc.gridx = column;
                gc.gridy = row;
                gc.insets = new Insets(3,3,3,3);
                if(column == 5 || column == 6)
                {
                    aisle.setPreferredSize(new Dimension(60, 20));
                    add(aisle, gc);
                } else 
                {
                    String seatRowAndNumber = (seats[i].getRowLetter()) + 
                            Integer.toString(seats[i].getSeatNumber());
                    
                    seats[i].setText(seatRowAndNumber);
                    add(seats[i++], gc);  
                }// end if-else
                --column;
            }//end while
            column = 11;
            ++row;
        }//end while        
    
    }//end displaySeats
        
    protected void assignSeatNumber(int num, Seat seat){
        switch(num)
        {
            case 0:
                seat.setSeatNumber(++num);
                break;
            case 1:
                seat.setSeatNumber(++num);
                break;
            case 2:
                seat.setSeatNumber(++num);
                break;
            case 3:
                seat.setSeatNumber(++num);
                break;
            case 4:
                seat.setSeatNumber(++num);
                break;
            case 5:
                seat.setSeatNumber(++num);
                break;
            case 6:
                seat.setSeatNumber(++num);
                break;
            case 7:
                seat.setSeatNumber(++num);
                break;
            case 8:
                seat.setSeatNumber(++num);
                break;
            case 9:
                seat.setSeatNumber(++num);
                break;
        }//end switch
    }//end assignSeatNumber

}//end class
