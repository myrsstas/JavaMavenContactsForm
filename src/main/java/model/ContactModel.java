//Application development in popular integrated development environments © 2023 by Myrsini Stasinou is licensed under CC BY 4.0
package model;

import java.util.Arrays;
import java.util.List;

public class ContactModel {
    private Integer id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String city;
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public ContactModel(Integer id, String name, String surname, String dateOfBirth, String phoneNumber, String email, String address, String city, String notes) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.notes = notes;
    }

    public String toFileRecord() {
        final List<String> contactFields = Arrays.asList(
                id.toString(),
                name,
                surname,
                dateOfBirth,
                phoneNumber,
                email,
                address,
                city,
                notes
        );

        final StringBuilder record = new StringBuilder();
        for (String field : contactFields) {
            record.append(field).append(";");
        }

        return record.toString();
    }

    public static ContactModel fromFileRecord(final String record) {
        final String[] fields = record.split(";");
        return new ContactModel(
                0,
                fields[1],
                fields[2],
                fields[3],
                fields[4],
                fields[5],
                fields[6],
                fields[7],
                fields[8]
        );
    }
}
