package ConcertHallBookingSystem;

import java.awt.Color;

public class BronzeSeat extends Seat {
    
    public static float price = 0.0f;
    
    public BronzeSeat(String name, int seatNumber, String rowLetter){
        this.name = name;
        this.seatNumber = seatNumber;
        this.rowLetter = rowLetter;
    }

    public BronzeSeat() {
        super.setBackground(Color.ORANGE);//set default colour of seat to bronze/orange
    }
        
    public void setPrice(float price){
        BronzeSeat.price = price;
    }
    
    public float getPrice(){
        return price;
    }
    
    protected void unBook(){
        
    }
    
    public void book(){
        this.isBooked = true;
        setBackground(Color.RED);
    }
}
