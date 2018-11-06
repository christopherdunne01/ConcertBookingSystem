package ConcertHallBookingSystem;

public class SilverSeatingBlock extends SeatingBlock{
    
    public SilverSeatingBlock(Seat[] seats){
        super(seats);
    }
    
    @Override
    protected void createEmptySeats(){
        for(int i = 0; i < 30; ++i){
            seats[i] = new SilverSeat();
        }
                  
        int i = 0;
        int row = 0;
        int column = 0;

        while(row < 3)
        {
            while(column < 10)
                {
                    assignRowLetter(row, seats[i]);
                    assignSeatNumber(column, seats[i]);
                    ++column;
                    ++i;
                }
            
            column = 0;
            ++row;
        }//end while loop
    
    }//end createEmptySeats
    
    @Override
    protected void assignRowLetter(int row, Seat seat){
        switch(row)
        {
            case 0:
                seat.setRowLetter("D");
                break;
            case 1:
                seat.setRowLetter("E");
                break;
            case 2:
                seat.setRowLetter("F");
        }
   
    }
    
    @Override
    protected void copySeatInformation(Seat[] seats){
        int i = 0;
        
        while(i < 90 && seats[i] != null)
        {
            if(seats[i] instanceof SilverSeat)
            {
                
                String letter = seats[i].getRowLetter();
                int num = seats[i].getSeatNumber();
                
                for(int k = 0; k < 30; ++k)
                {
                    if(this.seats[k].getRowLetter().matches(letter))
                    {
                        if(this.seats[k].getSeatNumber() == num)
                        {
                            this.seats[k].setName(seats[i].getName());
                            this.seats[k].setIsBooked(true);
                        }
                    }
                
                }//end for loop
            
            }//end if statement
            ++i;
        }//end while loop

    }//end copySeatInformation

}//end SilverSeatingBlock class
