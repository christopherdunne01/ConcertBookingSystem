package ConcertHallBookingSystem;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class SearchReport extends JDialog {

    Seat[] seats = new Seat[90];
    private JTextField nameInput = new JTextField();
    private JButton enter = new JButton();
    private JButton cancel = new JButton();
    JLabel nameText = new JLabel();
    JLabel aisleSeat = new JLabel();
    JLabel outBox = new JLabel();
    String name = "";
    JLabel totalTextBox;

    public SearchReport(Seat[] seats) {
        this.seats = seats;
        setLayout(new GridBagLayout());//Layout format we are using
        setSize(600, 200);//size of frame
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        nameText = new JLabel("Enter Name: ");
        enter = new JButton("Enter");
        nameInput = new JTextField(30);
        cancel = new JButton("Cancel");
        aisleSeat = new JLabel("Aisle and Seat Number is:");
        outBox = new JLabel();

        add(nameInput);
        add(nameText);
        add(enter);
        add(cancel);
        add(aisleSeat);
        add(outBox);

        cancelListener();
        setListeners();
        configureLayoutReport();
        setVisible(true);//make frame visible
    }//end SearchReport

    private void configureLayoutReport() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1.0;

        //Name Label
        gc.gridx = 1;
        gc.gridy = 1;
        add(nameText, gc);

        //Name TextField
        gc.gridx = 2;
        gc.gridy = 1;
        add(nameInput, gc);

        //Enter Button
        gc.gridx = 1;
        gc.gridy = 3;
        add(enter, gc);

        //Cancel Button
        gc.gridx = 2;
        gc.gridy = 3;
        add(cancel, gc);

        //aisleSeat Label
        gc.gridx = 1;
        gc.gridy = 4;
        add(aisleSeat, gc);

        //Outbox - displaying aise and seat number
        gc.gridx = 2;
        gc.gridy = 4;
        add(outBox, gc);

    }//end configureLayoutReport

    Scanner sc = new Scanner(System.in);

    private void setListeners() {
        enter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                getAisleSeat();//call this method

            }
        });
    }

    public void cancelListener() {
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                dispose();//close when button clicked
            }
        });
    }

    private void getAisleSeat() {

        String seatInformation = "";

        this.name = nameInput.getText();

        int i = 0;

        String rowSeatNum="";
        
        while (i < 90 && seats[i] != null) 
        {

            /* If the name for a given seat in the array does not match the name 
             * being searched for, move to the next element, however if they do
             * match, proceed to obtain the seat row and number and add it to
             * the seatInformation string which will eventually be display on 
             * screen.
             */
            
            if (!seats[i].getName().matches(name)) 
            {
                i++;
                continue;
            }
            
            //assign row letter and seat number from seat array to rowSeatNum variable
            rowSeatNum = seats[i].getRowLetter();
            rowSeatNum += seats[i].getSeatNumber();

            seatInformation += rowSeatNum + " ";
            i++;
            
        }//end while loop

        outBox.setText(seatInformation);

        // return seatInformation ; 
    
    }//end getAisleSeat

}//end SearchReport
