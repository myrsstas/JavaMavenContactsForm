package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import config.DBConfig;
import controller.ContactsController;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;

public class StartPage extends JFrame {
    private final ContactsController contactsController;
    private JButton letsStartButton;
    private JTextField uploadedFilePath;
    private JButton browseButton;
    private JButton uploadButton;
    private JPanel startPagePanel;

    public StartPage(final ContactsController contactsController) {

        setContentPane(startPagePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

        this.contactsController = contactsController;

        letsStartButton.addActionListener(e -> openNextForm());

        browseButton.addActionListener(e -> chooseFileFromUser());

        uploadButton.addActionListener(e -> {
            uploadFileToDatabase();
            openNextForm();
        });

    }

    private void chooseFileFromUser() {
        //open window dialog to search for file to upload
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false); //shows only files of .txt
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text File", "txt", "text"));
        final int response = fileChooser.showOpenDialog(null); //select file to open
        if (response != JFileChooser.APPROVE_OPTION) {
            return;
        }

        uploadedFilePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
    }

    private void uploadFileToDatabase() {
        final File fileToUpload = new File(uploadedFilePath.getText());
        contactsController.insertFromFile(fileToUpload);
    }

    private void openNextForm() {
        new ContactList(this.contactsController);
        this.setVisible(false);
        this.dispose();

    }

    public static void main(String[] args) throws SQLException {
        final ContactsController contactsController = new ContactsController(DBConfig.connectionString);
        try {
            contactsController.deleteAll();
            contactsController.resetAutoIncrement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        new StartPage(contactsController);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        startPagePanel = new JPanel();
        startPagePanel.setLayout(new GridLayoutManager(4, 3, new Insets(10, 20, 20, 20), -1, -1, true, true));
        final JLabel label1 = new JLabel();
        label1.setText("To start a new list, click on \"Let's Start\"");
        startPagePanel.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        letsStartButton = new JButton();
        letsStartButton.setText("Let's Start");
        startPagePanel.add(letsStartButton, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("If you used this app before, and have a file, please upload it.");
        startPagePanel.add(label2, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        uploadedFilePath = new JTextField();
        uploadedFilePath.setEditable(false);
        uploadedFilePath.setFocusable(false);
        uploadedFilePath.setVisible(true);
        startPagePanel.add(uploadedFilePath, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        browseButton = new JButton();
        browseButton.setText("Browse");
        startPagePanel.add(browseButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        uploadButton = new JButton();
        uploadButton.setText("Upload");
        startPagePanel.add(uploadButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label2.setLabelFor(uploadedFilePath);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return startPagePanel;
    }

}
