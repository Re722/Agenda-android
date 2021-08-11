package com.example.renaldo.agendadecontatos.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.renaldo.agendadecontatos.data.Contact;
import com.example.renaldo.agendadecontatos.data.ContactsRepository;

public class DetailViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private LiveData<Contact> mContact;
    private ContactsRepository mRepository;

    public DetailViewModel (Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
    }

    public LiveData<Contact> getContactById(int id) {
        mContact = mRepository.getContactById(id);
        return mContact;
    }

    public void delete() { mRepository.delete(mContact.getValue()); }
}


