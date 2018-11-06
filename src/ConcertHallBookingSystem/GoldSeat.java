package ConcertHallBookingSystem;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;

public class GoldSeat extends Seat {
    
    public static float price = 0.0f;
    
    private boolean isWinner = false;
    
    public GoldSeat(String name, int seatNumber, String rowLetter){
        super.setBackground(Color.YELLOW);   
        this.name = name;
        this.seatNumber = seatNumber;
        this.rowLetter = rowLetter;
    }
    
    public GoldSeat(){
        super.setBackground(Color.YELLOW); //set colour of set to yellow     
    }
    
    public boolean getIsWinner(){
        return isWinner;
    }
    
    public void setIsWinner(boolean value){
        this.isWinner = value;
    }
    
    public void setPrice(float price){
        this.price = price;
    }
    
    public float getPrice(){
        return price;
    }
    
    protected void unBook(){
        setBackground(Color.YELLOW);//when seat unbooked show as yellow
        isBooked = false;
        isWinner = false;//considering seat is unbook change both variables to false
        setName(null);//make name redunant now that it is unbooked
    }
    
    public void book(){
        
        //if gold seat is booked, give a 1 in 10 chance of a customer winning a backstage pass
        
        this.isBooked = true;

        Random rand = new Random();
        int randNum = rand.nextInt(10);
        
        if(randNum == 1)
        {
            
            isWinner = true;//change variable to true if they have won
            
            JOptionPane winningDialog = new JOptionPane();//display dialog, informing user they have won pass
            
            winningDialog.showMessageDialog(null, 
                    "Congratulations you've won a backstage pass!", null, 
                    JOptionPane.PLAIN_MESSAGE);
        }
        
        setBackground(Color.RED);//now that it is booked change to red
   
    }//end book method

}//end class GoldSeat
