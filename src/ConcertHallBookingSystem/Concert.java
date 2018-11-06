package ConcertHallBookingSystem;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Concert extends JPanel {
    
    private Seat[] seats = new Seat[90];
    
    private String title = null;
    private String date = null;
    private float goldPrice = 0.0f;
    private float silverPrice = 0.0f;
    private float bronzePrice = 0.0f;
    
    private BlockHolder holder;
    
    public Concert(){
        
    }
  
    public Concert(Seat[] seats, String title, String date, float goldPrice,
            float silverPrice, float bronzePrice){
        
        this.seats = seats;
        this.title = title;
        this.date = date;
        this.goldPrice = goldPrice;
        this.silverPrice = silverPrice;
        this.bronzePrice = bronzePrice;
        
        GoldSeat.price = goldPrice;
        SilverSeat.price = silverPrice;
        BronzeSeat.price = bronzePrice;
        
        super.setLayout(new GridBagLayout());
        holder = new BlockHolder(this.seats);//place blocks that have been created from blockholder
        configureLayout();
    }   
   
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getGoldPrice() {
        return goldPrice;
    }

    public void setGoldPrice(float goldPrice) {
        this.goldPrice = goldPrice;
    }

    public float getSilverPrice() {
        return silverPrice;
    }

    public void setSilverPrice(float silverPrice) {
        this.silverPrice = silverPrice;
    }

    public float getBronzePrice() {
        return bronzePrice;
    }

    public void setBronzePrice(float bronzePrice) {
        this.bronzePrice = bronzePrice;
    }
    
    private void configureLayout(){
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.BOTH;//spread seats out???/
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1.0;
        gc.weighty = 3.0;
 
        add(holder, gc);//visably add seats to User Interface
        
        Stage stage = new Stage();
        
        //position for stage
        gc.weighty = 1.0;
        gc.gridx = 0;
        gc.gridy = 0;
        
        add(stage, gc);
    }

    public Seat[] getSeats(){
        return holder.getSeats();
    }
}
