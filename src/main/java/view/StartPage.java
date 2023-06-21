package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame{
   public StartPage() {
       letsStartButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {


           }
       });
       browseButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

           }
       });
       uploadButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

           }
       });
   }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Start Page");
        frame.setContentPane(new StartPage().StartPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton letsStartButton;
    private JTextField uploadedFilePath;
    private JButton browseButton;
    private JButton uploadButton;
    private JPanel StartPagePanel;
}
