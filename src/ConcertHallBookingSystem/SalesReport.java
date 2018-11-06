package ConcertHallBookingSystem;

import java.awt.GridBagConstraints;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.GridBagLayout;

public class SalesReport extends JDialog { 

    private Seat[] seats = new Seat[90];

    private float goldPrice = 20.00f;
    private float silverPrice = 15.00f; 
    private float bronzePrice = 10.00f;

    private float goldTotalPrice = 0.0f;
    private float silverTotalPrice = 0.0f; 
    private float bronzeTotalPrice = 0.0f; 

    private float total = 0.0f;

    private int totalGoldSeats = 0;
    private int totalSilverSeats = 0;
    private int totalBronzeSeats = 0;

    private JLabel totalTextBox;
    private JLabel bronzeText= new JLabel();
    private JLabel silverText= new JLabel();
    private JLabel goldText = new JLabel();

    private JLabel gold;
    private JLabel silver;
    private JLabel bronze;
    private JLabel totalValue;
    
    private JLabel totalGoldSeatsOut;
    private JLabel totalSilverSeatsOut;
    private JLabel totalBronzeSeatsOut;
    
    private JLabel concertTitle;
    private JLabel concertDate;

    private String seatInformation = ""; 


    public SalesReport(Concert concert){
        //Use variables from concert class 
        this.seats = concert.getSeats();
        goldPrice = concert.getGoldPrice();
        silverPrice = concert.getSilverPrice();
        bronzePrice = concert.getBronzePrice();

        setLayout(new GridBagLayout());
        setSize(250, 250);
        setModal(false);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        concertTitle = new JLabel(concert.getTitle());
        concertDate = new JLabel(concert.getDate());

        //change calculateTotalPrice from float to string and display in label
        totalValue = new JLabel("Your total value:");
        totalTextBox = new JLabel(Float.toString(calculateTotalPrice()));
        
        //display how many seats are booked
        totalGoldSeatsOut = new JLabel(totalGoldSeats + "/30");
        totalSilverSeatsOut = new JLabel(totalSilverSeats + "/30");
        totalBronzeSeatsOut = new JLabel(totalBronzeSeats + "/30");

        bronze = new JLabel("Your bronze value:");
        bronzeText.setText(Float.toString(bronzeTotalPrice));
        bronze.setSize(60, 20);


        silver = new JLabel("Your silver value:");
        silverText.setText(Float.toString(silverTotalPrice));
        silver.setSize(60, 20);


        gold = new JLabel("Your gold value:");
        goldText.setText(Float.toString(goldTotalPrice));
        gold.setSize(60, 20);
        
        totalTextBox.setSize(60, 20);

        configureLayoutReport(); //call this method to set layout for each of these labels/textfields/buttons
        setVisible(true); 
    
    }//end SalesReport

     private void configureLayoutReport(){
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1.0;
        
        gc.gridx = 0;
        gc.gridy = 0;
        add(concertTitle, gc);
        
        gc.gridx = 1;
        add(concertDate, gc);
        
        gc.gridx = 0;
        gc.gridy = 1;
        add(bronze, gc);
        
        gc.gridx = 1;
        add(bronzeText, gc);

        gc.gridx = 0;
        gc.gridy = 2; 
        add(silver, gc);

        gc.gridx = 1;
        add(silverText, gc);


        gc.gridx = 0;
        gc.gridy = 3; 
        add(gold, gc);

        gc.gridx = 1;
        add(goldText, gc);


        gc.gridx = 0;
        gc.gridy = 4; 
        add(totalValue, gc);

        gc.gridx = 1;
        add(totalTextBox, gc);
        
        gc.gridx = 2;
        gc.gridy = 1;
        add(totalBronzeSeatsOut, gc);
        
        gc.gridx = 2;
        gc.gridy = 2;
        add(totalSilverSeatsOut, gc);
        
        gc.gridx = 2;
        gc.gridy = 3;
        add(totalGoldSeatsOut, gc);
    
    }//end configureLayoutReport

     
    private float calculateTotalPrice(){
        int i = 0;

        //while array is not null, iterate through the array
        while(i < 90 && seats[i] != null)
        {
            //if array matches seat type add price to TotalPrice and increment
            //total seats
            if(seats[i] instanceof GoldSeat)
            {
                goldTotalPrice += goldPrice;
                ++totalGoldSeats;
            }else if (seats[i] instanceof SilverSeat)
            {
                silverTotalPrice += silverPrice;
                ++totalSilverSeats;
            }else if (seats [i] instanceof BronzeSeat)
            {
                bronzeTotalPrice += bronzePrice;
                ++totalBronzeSeats;
            }

            ++i;
        }//end while loop

        return goldTotalPrice + silverTotalPrice + bronzeTotalPrice;
    
    }//end calculateTotalPrice

}//end salesReport
