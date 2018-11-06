package ConcertHallBookingSystem;

public class GoldSeatingBlock extends SeatingBlock {
    
    //Gold/Silver/Bronze Seating Block holds seats
    
    public GoldSeatingBlock(Seat[] seats){
        super(seats);//call from SuperClass(SeatingBlock)
    } 
    
    //enables this child class to override this method within the parent class
    @Override
    protected void createEmptySeats(){
        //create 30 Gold Seats with for loop
        for(int i = 0; i < 30; ++i)
        {
            seats[i] = new GoldSeat();
        }
                  
        int i = 0;
        int row = 0;
        int column = 0;

        /*while there are 3 rows within the 10 columns assign the row letter and seat number,
        to a seat and increment column and row. */
        while(row < 3)
        {
            while(column < 10)
                {
                    assignRowLetter(row, seats[i]);
                    assignSeatNumber(column, seats[i]);
                    ++column;
                    ++i;
                }
           
            column = 0;// needs to go to the next row
            ++row;
        }
    
    }//end createEmptySeats method
    
    @Override
    protected void assignRowLetter(int row, Seat seat){
    //With regards to each row, assign row letter
        switch(row)
        {
            case 0:
                seat.setRowLetter("A");
                break;
            case 1:
                seat.setRowLetter("B");
                break;
            case 2:
                seat.setRowLetter("C");
        }
    }

    @Override
    protected void copySeatInformation(Seat[] seats)
    {
        int i = 0;
        
        /* Iterate through the array of 90 seats which contains information for 
         * seats that were on the file. Seat number and aisle letter 
         * are compared with the 30 empty seats. If seat and row number match, 
         * the name is copied and the seat is set to booked. 
         */
        while(i < 90 && seats[i] != null)
        {
            if(seats[i] instanceof GoldSeat)
            {
                
                String letter = seats[i].getRowLetter();
                int num = seats[i].getSeatNumber();
                
                for(int k = 0; k < 30; ++k){
                    if( this.seats[k].getRowLetter().matches(letter) )
                    {
                        if(this.seats[k].getSeatNumber() == num)
                        {
                            this.seats[k].setName(seats[i].getName());
                            this.seats[k].setIsBooked(true);
                            ((GoldSeat) this.seats[k]).setIsWinner( ((GoldSeat)seats[i]).getIsWinner() );
                        }
                    }
                }//end for loop
            
            }//end if statement
            ++i;
        }//end while loop
    
    }//end method copySeatInformation

}//end class GoldSeatingBlock
