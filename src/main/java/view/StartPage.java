package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class StartPage extends JFrame{
   public StartPage() {
       letsStartButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //TODO: close previous form
               //this.dispose();
               //new StartPage().setVisible(false);

               //open next form (Contact List)
               new ContactList().setVisible(true);

           }

         /*  private void dispose() {
           frame.setVisible(false);
               frame.dispose();
           }
           */

       });
       browseButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //TODO: open window dialog to search for file to upload

           }
       });
       uploadButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //TODO: "upload" file to db
               //TODO: open next form (Contact List)

           }
       });
   }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Start Page");
        frame.setContentPane(new StartPage().StartPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton letsStartButton;
    private JTextField uploadedFilePath;
    private JButton browseButton;
    private JButton uploadButton;
    private JPanel StartPagePanel;
}
