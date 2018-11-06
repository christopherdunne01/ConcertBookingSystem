package ConcertHallBookingSystem;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BookingWindow extends JDialog {
    
    private GridBagLayout gLayout;
    private Seat seat;
    private JTextField nameInput = new JTextField(20); 
    private JButton bookUnbook = new JButton("Book");
    private JLabel bookedBy = new JLabel("Currently Unbooked");
    
            
    private JPanel pane = new JPanel();
    private JLabel nameLabel = new JLabel("Name");
        
    private JLabel aisle = new JLabel();
    private JLabel seatNumber = new JLabel();
        
    private String pass = "No";
    private String freeProgramme = "No";
        
    private JLabel priceLabel = new JLabel();
           
    private JLabel stagePass = new JLabel();
    private JLabel programme = new JLabel();
    
    public BookingWindow(Seat seat){
        super();
        this.seat = seat;
        gLayout = new GridBagLayout();
        configureLayout();
        setSize(500, 250);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(seat);
        setVisible(true);
    }
    
    private void configureLayout(){
        
        pane.setLayout(gLayout);//add GridBagLayout to panel
        
        aisle.setText( "Aisle: " + seat.getRowLetter() );//get RowLetter from seat class and put it into label
        seatNumber.setText( "Number: " + seat.getSeatNumber() );
        priceLabel.setText( "Price: " + (Float.toString(seat.getPrice())) );

        nameInput.setText(seat.getName());//getting the name and putting it in the label to show who has booked it
        
        //If ticket type is gold, collect winners and change String pass from no to yes
        if(seat instanceof GoldSeat)
        {
            if( ((GoldSeat)seat).getIsWinner() )
            {
                pass = "Yes";
            }
        }
        
        //collect all Silver ticket types, and set all programs from no to yes
        if(seat instanceof SilverSeat){
            freeProgramme = "Yes";
        }
       
        /*if seat is currently booked use bookUnbook Jlabel and set to Unbook.
        Take name from Seat and embed it inside bookedBy JLabel*/
        if(seat.isBooked()){
            bookUnbook.setText("Unbook");
            bookedBy.setText("Currently booked by" + " " + seat.getName()); 
        }  
                
        stagePass.setText("Back-stage pass: " + pass);
        programme.setText("Free Programme: " + freeProgramme);
        
        addBookListener();
        addNameChangeListener();
        addLabels();
    }
    
    //set labels, fields and pane in appropriate positions
    private void addLabels(){
        addItem(pane, nameLabel, 0, 0);  
        addItem(pane, nameInput, 0, 1);
        addItem(pane, bookUnbook, 0, 2);
        addItem(pane, bookedBy, 1, 1);
        addItem(pane, priceLabel, 1, 0);
        addItem(pane, aisle, 2, 0);
        addItem(pane, seatNumber, 2, 1);
        addItem(pane, stagePass , 3, 0);
        addItem(pane, programme, 3, 1);

        add(pane);
    }        
    
    
    private void addBookListener(){
        
        /*if seats is booked and is bronze, show dialog not allowing user to unbook. In regards
        to Silver and Gold, */
        bookUnbook.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                if(seat.isBooked())
                {
                    
                    if(seat instanceof BronzeSeat)
                    {
                        JOptionPane unbookWarningDialog = new JOptionPane();
                        
                        unbookWarningDialog.showMessageDialog(null, 
                            "Bronze seats cannot be unbooked!", null, 
                            JOptionPane.PLAIN_MESSAGE);
                    } else 
                    {

                        seat.unBook();//regardless is silver or gold they have an unbook method
                        bookUnbook.setText("Book");//Set to book
                        nameInput.setText(seat.getName());
                        bookedBy.setText("Currently unbooked"); 
                        pass = "No";
                        stagePass.setText("Back-stage pass: " + pass);
                        
                        changed();//call method to disble button
                    }
                    
                } else 
                {
                    seat.setName(nameInput.getText());
                    bookedBy.setText("Currently booked by" + " " + seat.getName()); 
                    seat.book();
                                           
                    //if gold seat and they win backstage pass, set to yes and and add to stagePass JLabel.
                    if(seat instanceof GoldSeat)
                    {
                        if(((GoldSeat) seat).getIsWinner())//casting applies here as getIsWinner is in GoldSeat and not Seat
                        {
                            pass = "Yes";
                            stagePass.setText("Back-stage pass: " + pass);
                        } else 
                        {
                            pass = "No";
                            stagePass.setText("Back-stage pass: " + pass);
                        }
                    }//end if
                        
                    bookUnbook.setText("Unbook");//set to unbook when they book a seat
               
                }//end ef-else
            
            }//end actionPerformed
      
        });
    
    }
    
    /* to ensure the user doesn't try and book with no name, this inteface 
     * calls the changed() method whenever the user types something, which then
     * checks to see if the textbox is blank; if it is, the book button is 
     * disabled 
     */
    private void addNameChangeListener(){      
        changed(); 

        nameInput.getDocument().addDocumentListener(new DocumentListener() {
            
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
            
            //remove letter from textbox
            public void removeUpdate(DocumentEvent e) {
                changed();
            }
            
            
            //add letter to text box
            public void insertUpdate(DocumentEvent e) {
                changed();
            }    
        });
    }//end addNameChangeListener
    
    //if no input for name disable "book" button, until name typed
    private void changed() {
        if ( nameInput.getText().matches("") && !seat.isBooked() )
        {
            bookUnbook.setEnabled(false);
        } else 
        {
            bookUnbook.setEnabled(true);
        }
    }
    
    private void addItem(JPanel panel, Component label, int row, int column){
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = row;
        gc.gridx = column;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        panel.add(label, gc);
    }
}
