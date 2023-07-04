package controller;

import model.ContactModel;
import config.DBConfig;
import view.AddContact;
import view.ContactList;
import view.StartPage;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ContactsController implements AutoCloseable {
    private final Connection connection;
    private ContactModel contactModel;
    private StartPage startPageView;
    private ContactList contactListView;
    private AddContact addContactView;

    private Statement statement = null;


    public ContactsController(final String connString) throws SQLException {
            // create a connection to the database
        connection = DriverManager.getConnection(DBConfig.connectionString);
        //connection.Open();-> den uparxei
        //Statement query = connection.createStatement();

    }

    public void deleteAll() throws SQLException {
        String query = "Delete from contacts;" +
                "DELETE FROM SQLITE_SEQUENCE WHERE name='contacts'";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.executeUpdate();
        //statement.executeQuery(query);

    }

    public List<ContactModel> getAll() {
        List<ContactModel> contacts = new ArrayList<ContactModel>();
        try {
            String query = "SELECT * FROM contacts";
            this.statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){

                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String dateOfBirth = resultSet.getString("date_of_birth");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String notes = resultSet.getString("notes");

                contacts.add(new ContactModel(id,name, surname, dateOfBirth, phoneNumber,email,address,city,notes));

                JOptionPane.showMessageDialog(null, id +" "+ name +" "+ surname +" "+ dateOfBirth +" "+ phoneNumber +" "+ email +" "+ address +" "+ city +" "+ notes , "InfoBox: Title" , JOptionPane.INFORMATION_MESSAGE);

            }
        //TODO: put whole list into table of Contact List

//            for(ContactModel ContactModel : contacts){
//            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }
    //control model object
    public void getContactModel (String name , String surname, String dateOfBirth, String phoneNumber, String email, String address, String city, String notes){
        contactModel.getName();
        contactModel.getSurname();
        contactModel.getDateOfBirth();
        contactModel.getPhoneNumber();
        contactModel.getEmail();
        contactModel.getAddress();
        contactModel.getCity();
        contactModel.getNotes();

    }

    //control view object
    public void updateView(){
        //addContact.nextEntry
    }


    public ContactModel save(ContactModel contactModel) {

        String name = null;
        String surname= null;
        String dateOfBirth = null;
        String phoneNumber = null;
        String email = null;
        String address = null;
        String city = null;
        String notes = null;

        //TODO: check if textboxes have text in them
        //nameText= "" ? name = "-" : name = nameText  ;
        //surnameText= "" ? surname = "-" :surname = surnameText;

        String query = "insert into contacts (name, surname, date_of_birth, phone_number, email, address, city, notes)  values(?, ?, ?, ?, ?, ?, ?, ?  )";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setString(3,dateOfBirth);
            ps.setString(4,phoneNumber);
            ps.setString(5,email);
            ps.setString(6,address);
            ps.setString(7,city);
            ps.setString(8,notes);

//            this.statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);

            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return contactModel;
    }

    public List<ContactModel> insertFromFile(File fileFromUser){

        //TODO: read file
        //TODO: break whole text into pieces
        //TODO: put pieces into list
        //TODO: open next form (Contact List)
        //TODO: refresh table in Contact List

        return new ArrayList<>();
    }

    @Override
    public void close() throws Exception {
            if (connection != null) {
                connection.close();
            }
    }
}
