//Application development in popular integrated development environments © 2023 by Myrsini Stasinou is licensed under CC BY 4.0
package controller;

import model.ContactModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ContactsController implements AutoCloseable {
    private final Connection connection;

    public ContactsController(final String connString) throws SQLException {
        // create a connection to the database
        connection = DriverManager.getConnection(connString);

    }

    public void deleteAll() throws SQLException {
        final String query = "DELETE FROM contacts";

        final PreparedStatement ps = connection.prepareStatement(query);
        ps.executeUpdate();
    }

    public void resetAutoIncrement() throws SQLException {
        final String query = "DELETE FROM SQLITE_SEQUENCE WHERE name='contacts'";

        final PreparedStatement ps = connection.prepareStatement(query);
        ps.executeUpdate();
    }

    public List<ContactModel> getAll() {
        List<ContactModel> contacts = new ArrayList<ContactModel>();
        try {
            String query = "SELECT * FROM contacts";
            Statement statement = connection.createStatement();
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
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public ContactModel save(ContactModel contactModel) {
        //check if textboxes have text in them
        final String name = contactModel.getName().isEmpty() ? "-" : contactModel.getName();
        final String surname= contactModel.getSurname().isEmpty() ? "-" : contactModel.getSurname();
        final String dateOfBirth = contactModel.getDateOfBirth();
        final String phoneNumber = contactModel.getPhoneNumber().isBlank() ? "-" : contactModel.getPhoneNumber();
        final String email = contactModel.getEmail().isEmpty() ? "-" : contactModel.getEmail();
        final String address = contactModel.getAddress().isEmpty() ? "-" : contactModel.getAddress();
        final String city = contactModel.getCity().isEmpty() ? "-" : contactModel.getCity();
        final String notes = contactModel.getNotes().isEmpty() ? "-" : contactModel.getNotes();


        String query = "insert into contacts (name, surname, date_of_birth, phone_number, email, address, city, notes)  values(?, ?, ?, ?, ?, ?, ?, ?  )";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            //match data put by the user into each field for the query
            ps.setString(1,name);
            ps.setString(2,surname);
            ps.setString(3,dateOfBirth);
            ps.setString(4,phoneNumber);
            ps.setString(5,email);
            ps.setString(6,address);
            ps.setString(7,city);
            ps.setString(8,notes);

            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return contactModel;
    }

    public List<ContactModel> insertFromFile(File fileFromUser){
        try {
            Path pathFromUser = fileFromUser.toPath();
            List<String> records = Files.readAllLines(pathFromUser);
            List<ContactModel> contacts = new ArrayList<>();
            for (String record : records) {
                if(record.isBlank()) {
                    continue;
                }
                ContactModel contact = ContactModel.fromFileRecord(record);
                save(contact);
                contacts.add(contact);
            }
            return contacts;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void exportToFile(File fileFromUser) throws IOException {
        List<String> contacts = new ArrayList<>();
        for (ContactModel contact : this.getAll()) {
            String contactRecordWithNewLineSuffix = contact.toFileRecord() + "\n";
            contacts.add(contactRecordWithNewLineSuffix);
        }

        FileWriter fileWriter = new FileWriter(fileFromUser);
        for (String contact : contacts) {
            fileWriter.append(contact);
        }
        fileWriter.close();
    }

    @Override
    public void close() throws Exception {
            if (connection != null) {
                connection.close();
            }
    }
}
