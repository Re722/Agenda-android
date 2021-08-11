package com.example.renaldo.agendadecontatos.ui.main;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.renaldo.agendadecontatos.data.Contact;
import com.example.renaldo.agendadecontatos.data.ContactsRepository;

public class AddEditViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private LiveData<Contact> contact;
    private ContactsRepository mRepository;

    public AddEditViewModel (Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
    }

    public LiveData<Contact> getContactById(int id) {
        contact = mRepository.getContactById(id);
        return contact;
    }

    public void insert(Contact contact) { mRepository.insert(contact); }

    public void update(Contact contact) { mRepository.update(contact); }


}





