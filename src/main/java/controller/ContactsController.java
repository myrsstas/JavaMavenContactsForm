package controller;

import model.ContactModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ContactsController {
    public void deleteAll(){

    }

    public List<ContactModel> getAll() {
        return new ArrayList<>();
    }

    public ContactModel save(ContactModel contactModel) {
        return contactModel;
    }

    public List<ContactModel> insertFromFile(File fileFromUser){
        return new ArrayList<>();

    }



}
