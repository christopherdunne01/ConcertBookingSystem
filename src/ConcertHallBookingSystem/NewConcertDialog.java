package ConcertHallBookingSystem;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class NewConcertDialog extends JDialog {
    
    private Concert concert;
    private Seat[] seats = new Seat[90];
    
    private JTextField concertTitle = new JTextField(20); 
    private JTextField concertDate = new JTextField(10);
    private JTextField goldPrice = new JTextField(7);
    private JTextField silverPrice = new JTextField(7);
    private JTextField bronzePrice = new JTextField(7); // allow users to enter in Title, Date and prices
    
    private JButton createConcert = new JButton("Create Concert"); 
    private JButton cancel = new JButton("Cancel"); 

    private Frame frame = null;
        
    public NewConcertDialog(Frame frame){
        super();
        this.frame = frame;
        GridBagLayout gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        configureLayout();
        setSize(950, 200);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setListeners();
        setVisible(true);
    }
    
    public NewConcertDialog(){
        super();
        GridBagLayout gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        configureLayout();
        setSize(950, 200);
        setModal(true);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE );
        setListeners();
        setVisible(true);
    }
        
    private void configureLayout(){
        
        JLabel titleL = new JLabel("Enter Concert Name");
        JLabel dateL = new JLabel("Enter Concert Date (dd/mm/yyyy)");
        JLabel goldL = new JLabel("Enter Gold Price");
        JLabel silverL = new JLabel("Enter Silver Price");
        JLabel bronzeL = new JLabel("Enter Bronze Price"); //labels shown on UI
        
        addItem(titleL, 0, 0);
        addItem(concertTitle, 1, 0);
        addItem(dateL, 2, 0);
        addItem(concertDate, 3, 0);
        addItem(goldL, 0, 1);
        addItem(goldPrice, 1, 1);
        addItem(silverL, 2, 1);
        addItem(silverPrice, 3, 1);
        addItem(bronzeL, 4, 1);
        addItem(bronzePrice, 5, 1);
        addItem(createConcert, 0, 2);
        addItem(cancel, 1, 2); // add each item to make them visibile, with appropriate positions
        
    }
    
    private void addItem(Component label, int column, int row){
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = column;
        gc.gridy = row;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(label, gc);
    }
        
    private void setListeners(){
        createConcert.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                
                float goldP = 0.0f;
                float silverP = 0.0f;
                float bronzeP = 0.0f;
                
            /*if numbers entered parse to float. If numbers not entered throw up
              warning message*/
                
                try
                {
                    goldP = Float.parseFloat(goldPrice.getText());
                    silverP = Float.parseFloat(silverPrice.getText());
                    bronzeP = Float.parseFloat(bronzePrice.getText());

                } catch(NumberFormatException ex)
                {
                        
                    JOptionPane floatWarningDialog = new JOptionPane();
                    floatWarningDialog.showMessageDialog(null, "Prices must be numbers!", null, 
                    JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                    
                    
                String date = concertDate.getText();//assign entered date to String date
                
                StringTokenizer st = new StringTokenizer(date);//breaking string into tokens
                
                //A date of more than one token will cause problems when reading
                //from the file, reading from the file expects the date as one 
                //token
                if(st.countTokens() > 1)
                {
                        JOptionPane dateWarningDialog = new JOptionPane();

                        dateWarningDialog.showMessageDialog(null, 
                            "Date must be in the format dd/mm/yyyy", null, 
                            JOptionPane.PLAIN_MESSAGE);
                        return;
                }                
                
              
                concert = new Concert(seats, concertTitle.getText(), date, 
                                    goldP, silverP ,bronzeP);                                
 
                /* There are two circumstances where this class is instantiated;
                 * when the file is empty or when there is already a concert but
                 * the administrator wants to create a new one and remove the 
                 * old one. In the first case a frame is not passed in and is 
                 * therefore null and there is no need to remove the old 
                 * concert.
                 */
                
                if(frame != null)
                {
                    removeOldConcert();
                }
                
                dispose();
            
            }//end actionPerformed            
        
        });//end createConcert
        
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });
        
        addTextChangeListener();
    
    }//end setListeners
    
   
    private void removeOldConcert(){
            frame.removeOldConcert();
            frame.add(concert);//add new concert to frame
            frame.setConcert(concert);
            frame.setVisible(true);//show in frame
    }
    
    public Concert getConcert(){
        return concert;
    }
    
    //cycle through for loop for each of the JTextFields   
    private void addTextChangeListener(){
        
        JTextField[] fields = {concertTitle, concertDate, goldPrice, 
            silverPrice, bronzePrice};
        
        changed();
                
        for(int i = 0; i < 5; ++i)
        {
            
            fields[i].getDocument().addDocumentListener(new DocumentListener() {

                public void changedUpdate(DocumentEvent e) {
                    changed();
                }

                public void removeUpdate(DocumentEvent e) {
                    changed();
                }

                public void insertUpdate(DocumentEvent e) {
                    changed();
                }    
            });
        
        }//end for loop
    
    }//end addTextChangeListener
    
    
    //dependant on what user enters, if null disable create concert button.
    
    private void changed() {
        
        boolean blankFields = (concertTitle.getText().matches("")) ||
                (concertDate.getText().matches("")) || (goldPrice.getText().matches("")) ||
                (silverPrice.getText().matches("")) || (bronzePrice.getText().matches(""));
        
        if (blankFields)
        {
            createConcert.setEnabled(false);
        } else 
        {
            createConcert.setEnabled(true);
        }
    
    }//end changed

}//end NewConcertDialog
