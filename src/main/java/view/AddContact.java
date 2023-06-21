package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    Calendar calDay=Calendar.getInstance();
    JDateChooser dateChooser = new JDateChooser(calDay.getTime());

    public AddContact(){
        setContentPane(AddContactPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(700,600);

        dateChooser.setDateFormatString("dd/MM/yyyy");
        jpCalendar.add(dateChooser);

        nextEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

  /* public static void main(String[] args) {
        JFrame frame = new JFrame("AddContact");
        frame.setContentPane(new AddContact().AddContactPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }*/
}
