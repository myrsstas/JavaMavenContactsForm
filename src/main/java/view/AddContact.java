package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Calendar;

public class AddContact extends JFrame {
    private JPanel AddContactPanel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField addressTextField;
    private JTextField cityTextField;
    private JTextArea notesTextArea;
    private JButton nextEntryButton;
    private JButton backToListButton;
    private JPanel jpCalendar;

   /* public void getContactDetails(nameTextField, surnameTextField, dateChooser, phoneNumberTextField, emailTextField, addressTextField, cityTextField, notesTextArea){
        String nameText = nameTextField.getText();
        String surnameText = surnameTextField.getText();
        String dateOfBirth =  dateChooser.getDate().toString();
        String phonenumberText = phoneNumberTextField.getText();
        String emailText = emailTextField.getText();
        String addressText = addressTextField.getText();
        String cityText = cityTextField.getText();
        String notesText = notesTextArea.getText();
    }*/

    public AddContact(){
        setContentPane(AddContactPanel);
        //TODO: change exit to dispose??
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        //setVisible(true);
        setSize(600,500);

        //TODO: make method for clear text in form

        Calendar calDay=Calendar.getInstance();
        JDateChooser dateChooser = new JDateChooser(calDay.getTime());
        dateChooser.setDateFormatString("yyyy-MM-dd");
        jpCalendar.add(dateChooser);

        nextEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO: get all contact details
                //TODO: pass info to controller for insert sql query
                //TODO: clear fields

            }
        });
        backToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: clear fields

                //TODO: reload form ContactList
                new ContactList().setVisible(true);
            }
        });
        phoneNumberTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //TODO: filtrare ta koumpia pou patiountai kai na grafontai mexri 10 noumera
                super.keyPressed(e);

            }
        });


        emailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //TODO: checkare an to email einai eggyro
            }
        });



       /* jpCalendar.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(LocalDateTime.now().equals(evt.getPropertyName())){


                }

            }
        });*/

       /* public static void ClearFields() {

            nameTextField.setText("");
            surnameTextField.setText("");
            phoneNumberTextField.setText("");
            emailTextField.setText("");
            addressTextField.setText("");
            cityTextField.setText("");
            notesTextArea.setText("");
            //calDay.setTime(LocalDate.now());

        }
*/

    }

  /* public static void main(String[] args) {
        JFrame frame = new JFrame("AddContact");
        frame.setContentPane(new AddContact().AddContactPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/
}
