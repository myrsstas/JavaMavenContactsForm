package view;

import javax.swing.*;

public class StartPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("StartPage");
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
