package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactList extends JFrame {
    private JPanel ContactListPanel;
    private JButton addContactButton;
    private JTable contactsTable;
    private JButton exportDataButton;
public ContactList() {

    setContentPane(ContactListPanel);
    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    setLocationRelativeTo(null);
    //setVisible(true);
    setSize(700,600);

    //TODO: load data from db

    addContactButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //open next form (Add Contact)
            new AddContact().setVisible(true);

        }
    });
    exportDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: open window dialog so that user will choose where to save the file
            //TODO: export all data from db to txt file

        }
    });
}
}
