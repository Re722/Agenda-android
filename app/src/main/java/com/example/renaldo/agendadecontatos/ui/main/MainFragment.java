package com.example.renaldo.agendadecontatos.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.renaldo.agendadecontatos.R;
import com.example.renaldo.agendadecontatos.data.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
//import java.util.Observer;

import  static com.example.renaldo.agendadecontatos.MainActivity.CONTACT_ID;
import  static com.example.renaldo.agendadecontatos.MainActivity.NEW_CONTACT;

public class MainFragment<list> extends Fragment {

    ContactsAdapter contactsAdapter;

    private MainViewModel mainViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        recyclerView.addItemDecoration(new ItemDivider(getContext()));

        contactsAdapter = new ContactsAdapter(new ContactsAdapter.ContactClickListener() {
            @Override
            public void onClick(int contactID) {
                onContactSelected(contactID);
            }
        });
        recyclerView.setAdapter(contactsAdapter);

        FloatingActionButton addButton = view.findViewById(R.id.addButtom);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddContact();
            }
        });
        return view;
    }

    private void onAddContact() {
        Bundle arguments = new Bundle();
        arguments.putInt(CONTACT_ID, NEW_CONTACT);
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_addEditFragment, arguments);
    }

    private void onContactSelected(int contact_ID) {
        Bundle arguments = new Bundle();
        arguments.putInt(CONTACT_ID, contact_ID);
        Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_detailFragment, arguments);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // configura a observação da lista de contatos para atualizar a lista
        // quando detectada uma mudança
        mainViewModel.getAllContacts().observe(getViewLifecycleOwner(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(@Nullable final List<Contact> contacts) {
                // Atualiza a lista de contatos do adaptador
                contactsAdapter.setContacts(contacts);
            }
        });

    }
}











