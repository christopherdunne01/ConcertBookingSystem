package ConcertHallBookingSystem;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;

public abstract class Seat extends JButton {
    
            
    protected String name = null;
    protected boolean isBooked = false;
    protected int seatNumber = 0;
    protected String rowLetter = null;
    
    public Seat(){
        super.setPreferredSize(new Dimension(60, 20));
        super.addActionListener(new SeatListener(this));//attatching SeatListener to Seat
    }
    
    protected abstract void unBook();
    protected abstract void book();
    protected abstract float getPrice();
    
    public boolean isBooked(){
        return isBooked;
    }
    
    public void setIsBooked(boolean value){
        this.isBooked = value;             
        setBackground(Color.RED);
    }
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
        
    public String getRowLetter() {
        return rowLetter;
    }

    public void setRowLetter(String rowLetter) {
        this.rowLetter = rowLetter;
    }

}//end Seat Class

