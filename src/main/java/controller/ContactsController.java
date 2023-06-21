package controller;

import model.ContactModel;
import config.DBConfig;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ContactsController implements AutoCloseable{
    private final Connection connection;
    private Statement statement = null;


    public ContactsController(final String connString) throws SQLException {
            // create a connection to the database
        connection = DriverManager.getConnection(DBConfig.connectionString);
        //connection.Open();-> den uparxei
            //Statement query = connection.createStatement();

    }

    public void deleteAll() throws SQLException {
        String query = "Delete from contacts;" +
                "DELETE FROM SQLITE_SEQUENCE WHERE name='contacts';";

        //PreparedStatement prstmt = connection.prepareStatement(query);
        //prstmt.executeUpdate();
        statement.executeQuery(query);

    }

    public List<ContactModel> getAll() {
        try {
            String query = "SELECT * FROM contacts";
            this.statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String dateOfBirth = resultSet.getString("date_of_birth");
                int phoneNumber = resultSet.getInt("phone_number");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String notes = resultSet.getString("notes");

                JOptionPane.showMessageDialog(null, id +" "+ name +" "+ surname +" "+ dateOfBirth +" "+ phoneNumber +" "+ email +" "+ address +" "+ city +" "+ notes , "InfoBox: Title" , JOptionPane.INFORMATION_MESSAGE);

            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new ArrayList<>();
    }

    public ContactModel save(ContactModel contactModel) {


        return contactModel;
    }

    public List<ContactModel> insertFromFile(File fileFromUser){


        return new ArrayList<>();
    }

    @Override
    public void close() throws Exception {
            if (connection != null) {
                connection.close();
            }
    }
}
