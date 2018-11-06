package ConcertHallBookingSystem;

import java.awt.Color;
import javax.swing.JOptionPane;

public class SilverSeat extends Seat {
    
    public static float price = 0.0f;
    
    public SilverSeat(String name, int seatNumber, String rowLetter){
        this.name = name;
        this.seatNumber = seatNumber;
        this.rowLetter = rowLetter;
    }

    public SilverSeat() {
        super.setBackground(Color.GRAY);//set defalt colour to gray/silver
    }
           
    public void setPrice(float price){
        this.price = price;
    }
    
    public float getPrice(){
        return price;
    }

    protected void unBook(){
        setBackground(Color.GRAY);
        isBooked = false;
        setName(null);
    }    
    
    public void book(){
        /* when customer books a seat show dialog informing user they have got a free program*/
        this.isBooked = true;
        JOptionPane freeProgrammeDialog = new JOptionPane();
        
        freeProgrammeDialog.showMessageDialog(null, 
                "This customer is entitled to a free program!", null, 
                JOptionPane.PLAIN_MESSAGE);
        setBackground(Color.RED);
    }

}//end class
