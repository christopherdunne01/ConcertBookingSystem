package ConcertHallBookingSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatListener implements ActionListener {
    
    private Seat seat;
    
    public SeatListener(Seat seat){
        this.seat = seat;
    }
    
    public void actionPerformed(ActionEvent e){
        BookingWindow popUp = new BookingWindow(seat);                           
    }

}
