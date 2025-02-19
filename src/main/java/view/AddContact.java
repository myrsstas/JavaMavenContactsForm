package view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import controller.ContactsController;
import model.ContactModel;
import org.apache.commons.validator.routines.EmailValidator;

public class AddContact extends JFrame {

    private final ContactsController contactsController;


    private JPanel AddContactPanel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField emailTextField;
    private JTextField addressTextField;
    private JTextField cityTextField;
    private JTextArea notesTextArea;
    private JButton nextEntryButton;
    private JButton backToListButton;
    private JPanel jpCalendar;
    private JFormattedTextField phoneNumberFormattedField;

    public JDateChooser dateChooser;


    public AddContact(final ContactsController contactsController) {
        setContentPane(AddContactPanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(600, 500);

        this.contactsController = contactsController;

        SetCalendar();
        //method for clear text in form
        ClearTextFields();
        setPhoneNumberTextField();

        nextEntryButton.addActionListener(e -> {
            //get all contact details
            ContactModel contactDetails = getContactDetails();
            //pass info to controller for insert sql query
            this.contactsController.save(contactDetails);
            //clear fields
            ClearTextFields();
        });
        backToListButton.addActionListener(e -> {
            //clear fields
            ClearTextFields();
            //reload form ContactList
            goToPreviousForm();

        });

        emailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                //checkare an to email einai eggyro
                String email = emailTextField.getText();
                if (email.isEmpty()) {
                    return;
                }
                boolean valid = EmailValidator.getInstance().isValid(email);
                if (!valid) {
                    JOptionPane.showMessageDialog(null, "The " + email + " email you entered is not valid ", "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
                    emailTextField.setText("");
                }
            }
        });

    }


    private void setPhoneNumberTextField() {
        final MaskFormatter formatter;
        try {
            //format for 10digit number
            formatter = new MaskFormatter("##########");
            this.phoneNumberFormattedField.setFormatterFactory(new DefaultFormatterFactory(formatter));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void goToPreviousForm() {
        new ContactList(contactsController);
        this.setVisible(false);
        this.dispose();
    }


    private ContactModel getContactDetails() {
        String nameText = nameTextField.getText();
        String surnameText = surnameTextField.getText();
        //get date from calendar
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirth = dateFormat.format(dateChooser.getDate());
        String todayAsString = dateFormat.format(new Date());
        boolean isTodayBirthDate = dateOfBirth.contentEquals(todayAsString);
        String dateOfBirthToSave = (isTodayBirthDate) ? "-" : dateOfBirth;
        String phonenumberText = phoneNumberFormattedField.getText();
        String emailText = emailTextField.getText();
        String addressText = addressTextField.getText();
        String cityText = cityTextField.getText();
        String notesText = notesTextArea.getText();

        return new ContactModel(null, nameText, surnameText, dateOfBirthToSave, phonenumberText, emailText, addressText, cityText, notesText);
    }

    public void SetCalendar() {
        dateChooser = new JDateChooser(Calendar.getInstance().getTime());
        dateChooser.setDateFormatString("yyyy-MM-dd");
        jpCalendar.add(dateChooser);
        //disable all dates after today
        Date dayAfterToday = new Date(new Date().getTime() + 86400);
        dateChooser.setMaxSelectableDate(dayAfterToday);
    }

    private void ClearTextFields() {
        nameTextField.setText("");
        surnameTextField.setText("");
        phoneNumberFormattedField.setValue(null);
        emailTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        notesTextArea.setText("");

        //reset Jcalendars date to today
        dateChooser.setDate(new Date());

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
        AddContactPanel = new JPanel();
        AddContactPanel.setLayout(new GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Name : ");
        AddContactPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTextField = new JTextField();
        AddContactPanel.add(nameTextField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Surname :");
        AddContactPanel.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        surnameTextField = new JTextField();
        AddContactPanel.add(surnameTextField, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(307, 30), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Date Of Birth : ");
        AddContactPanel.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Phone Number :");
        AddContactPanel.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Email : ");
        AddContactPanel.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailTextField = new JTextField();
        AddContactPanel.add(emailTextField, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(307, 30), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Address : ");
        AddContactPanel.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addressTextField = new JTextField();
        AddContactPanel.add(addressTextField, new GridConstraints(5, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("City : ");
        AddContactPanel.add(label7, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cityTextField = new JTextField();
        AddContactPanel.add(cityTextField, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Notes : ");
        AddContactPanel.add(label8, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        notesTextArea = new JTextArea();
        notesTextArea.setText("");
        AddContactPanel.add(notesTextArea, new GridConstraints(7, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(200, 100), null, 0, false));
        nextEntryButton = new JButton();
        nextEntryButton.setText("Submit and Enter Next Entry ");
        AddContactPanel.add(nextEntryButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backToListButton = new JButton();
        backToListButton.setText("Back To List");
        AddContactPanel.add(backToListButton, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        AddContactPanel.add(spacer1, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        jpCalendar = new JPanel();
        jpCalendar.setLayout(new BorderLayout(0, 0));
        AddContactPanel.add(jpCalendar, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        phoneNumberFormattedField = new JFormattedTextField();
        AddContactPanel.add(phoneNumberFormattedField, new GridConstraints(3, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label1.setLabelFor(nameTextField);
        label2.setLabelFor(surnameTextField);
        label5.setLabelFor(emailTextField);
        label6.setLabelFor(addressTextField);
        label7.setLabelFor(cityTextField);
        label8.setLabelFor(notesTextArea);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return AddContactPanel;
    }

}
