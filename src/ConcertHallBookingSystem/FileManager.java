package ConcertHallBookingSystem;; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/* This class reads the contents of the text file 
 * and also writes new bookings to the text file
 */

class FileManager {

    private Seat[] seats = new Seat[90];
    
    private float goldPrice = 0.0f;
    private float silverPrice = 0.0f;
    private float bronzePrice = 0.0f;
    private String title;
    private String date;
    private Concert concert;

    public FileManager(){
        
    }

    public void setFileContents(Concert concert){
        this.concert = concert;
        title = concert.getTitle();
        date = concert.getDate();
        goldPrice = concert.getGoldPrice();
        silverPrice = concert.getSilverPrice();
        bronzePrice = concert.getBronzePrice();
        seats = concert.getSeats();
        writeToFile();
    }
     
    public Concert getFileContents(){
        readFromFile();
        return concert;
    }
    
    
    private void readConcertTitle(Scanner in) throws NoConcertException{
        try
        {
            title = in.nextLine();
        } catch(NoSuchElementException e)
        {
            throw new NoConcertException();
        }
    }
    
    private void readConcertDate(Scanner in){
        date = in.next();
    }
    
    private void readPrices(Scanner in){
        goldPrice = in.nextFloat();
        silverPrice = in.nextFloat();
        bronzePrice = in.nextFloat();
    }
    
      
    private void readFromFile(){
       
        Scanner in = null;

        try 
        {
            in = new Scanner(new File("src/ConcertHallBookingSystem/concert.txt"));//read from specified file

                try
                {
                    readConcertTitle(in);
                    readConcertDate(in);
                    readPrices(in);
                    readSeats(in);//read each element of txt file

                    concert = new Concert(seats, title, date, goldPrice, silverPrice, 
                            bronzePrice);

                } catch(NoConcertException c)
                {
                    NewConcertDialog nConcert = new NewConcertDialog();
                    concert = nConcert.getConcert();
                }//end embedded try catch
                
        } catch (FileNotFoundException ex)
        { 
            
            System.out.println(ex.getMessage());
                    System.out.println("in " + System.getProperty("user.dir"));
                    System.exit(1);
        
        }//end try-catch
           
        in.close();  //close file
    
    }//end readFromFile
    
    private void readSeats(Scanner in){
        
        int i = 0;
        
        //while file has next line continue to iterate through it                     
        while(in.hasNext())
        {
            
            //read each element with scanner
            String seatType = in.next(); 
            String name = in.next();
            String rowLetter = in.next();
            int seatNumber = in.nextInt();

            //if chosen ticket type matches specified ticket type assign it to the array with
            //name, seat number and row letter. 
            if(seatType.matches("Gold") || seatType.matches("Winner"))
            {
                seats[i] = new GoldSeat(name, seatNumber, rowLetter);
                if(seatType.matches("Winner"))
                {
                    ((GoldSeat) seats[i]).setIsWinner(true);
                }
            }else if(seatType.matches("Silver"))
            {
                seats[i] = new SilverSeat(name, seatNumber, rowLetter);
            }else if(seatType.matches("Bronze"))
            {
                seats[i] = new BronzeSeat(name, seatNumber, rowLetter);
            }

            i++;
        }//end while loop
    
    }//end readSeats

    
    private void writeToFile(){

        PrintWriter out = null;

        try {

                out = new PrintWriter(new File("src/ConcertHallBookingSystem/concert.txt"));

                String seatType = null;

                //Write title, date and prices to file
                out.println(title);
                out.println(date);
                out.println(goldPrice + " " + silverPrice + " " + bronzePrice); 

                //scan through array of 90
                for(int i = 0; i < 90; i++)
                {

                    /*if seats arent filled break out of array. Otherwise, for Gold, they have either
                      won a backstage pass or just a normal gold ticket. If user chooses Silver or bronze
                      write this to file. */
                        if(seats[i] == null)
                        {
                          break; 
                        } else if (seats[i] instanceof GoldSeat)
                        {

                            if( ((GoldSeat) seats[i]).getIsWinner() )
                            {
                                seatType = "Winner";
                            } else 
                            {
                                seatType = "Gold";
                            }//end embedded if else

                        } else if(seats[i] instanceof SilverSeat)
                        {
                            seatType = "Silver";
                        } else if(seats[i] instanceof BronzeSeat)
                        { 
                            seatType = "Bronze";
                        }//end else-if   

                        out.println( seatType + " " + seats[i].getName() + " " +  
                        (seats[i].getRowLetter()) + " " + (seats[i].getSeatNumber()));

                }//end for loop

            } catch (FileNotFoundException ex)
            { 
                System.out.println(ex.getMessage());
                System.out.println("in " + System.getProperty("user.dir"));
                System.exit(1);
            }//end try catch
                
        out.close();//make sure you can no longer write to the file
        
    }//end method writeToFile

}//end class


