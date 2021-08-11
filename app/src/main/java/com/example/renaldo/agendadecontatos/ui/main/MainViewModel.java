package com.example.renaldo.agendadecontatos.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.renaldo.agendadecontatos.data.Contact;
import com.example.renaldo.agendadecontatos.data.ContactsRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private LiveData<List<Contact>> mAllContacts;
    private ContactsRepository mRepository;

    public MainViewModel (Application application) {
        super(application);
        mRepository = new ContactsRepository(application);
        mAllContacts = mRepository.getAllContacts();
    }
    public LiveData<List<Contact>> getAllContacts() { return mAllContacts; }
}

