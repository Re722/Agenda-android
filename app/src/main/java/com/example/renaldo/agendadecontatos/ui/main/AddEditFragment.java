package com.example.renaldo.agendadecontatos.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.renaldo.agendadecontatos.R;
import com.example.renaldo.agendadecontatos.data.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.renaldo.agendadecontatos.MainActivity.CONTACT_ID;
import  static com.example.renaldo.agendadecontatos.MainActivity.NEW_CONTACT;

public class AddEditFragment extends Fragment {

    private int contactID;
    private boolean addingNewContact;
    private FloatingActionButton saveContactFAB;

    private TextInputLayout nameTextInputLayout;
    private  TextInputLayout phoneTextInputLayout;
    private  TextInputLayout emailTextInputLayout;

    private AddEditViewModel addEditViewModel;

    private AddEditViewModel mViewModel;

    public static AddEditFragment newInstance() {
        return new AddEditFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.add_edit_fragment, container, false);

        nameTextInputLayout = view.findViewById(R.id.nameTextInputLayout);
        phoneTextInputLayout = view.findViewById(R.id.phoneTextInputLayout);
        emailTextInputLayout = view.findViewById(R.id.emailTextInputLayout);

        saveContactFAB =view.findViewById(R.id.saveButton);
        saveContactFAB.setOnClickListener(saveContactButtonClicked);

        Bundle arguments= getArguments();
        contactID= arguments.getInt(CONTACT_ID);
        if(contactID == NEW_CONTACT){
            addingNewContact=true;
        }else {
            addingNewContact=false;
        }
        return view;
    }
    private final View.OnClickListener saveContactButtonClicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((InputMethodManager) getActivity().getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getView().getWindowToken(), 0);
            saveContact();

        }
   };

    private void saveContact() {
        // TODO ler dados inseridos
        // TODO salvar dados
        // solicita o retorno à tela anterior
        String name = nameTextInputLayout.getEditText().getText().toString();
        String phone = phoneTextInputLayout.getEditText().getText().toString();
        String email = emailTextInputLayout.getEditText().getText().toString();
        // caso for adição de uma novo contato
        if (addingNewContact == true) {
            // cria um contato sem um ID pois ele será adicionado automaticamente no banco de dados
            Contact contact = new Contact(name, phone, email);
            // solicita a ViewModel a inserção do novo contato
            addEditViewModel.insert(contact);
        } else {
            // cria um contato com o mesmo ID e atualiza o seus valores
            Contact contact = new Contact(contactID, name, phone, email);
            // solicita a ViewModel a atualização do contato
            addEditViewModel.update(contact);
        }

            Navigation.findNavController(getView()).popBackStack();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addEditViewModel = new ViewModelProvider(this).get(AddEditViewModel.class);
        // TODO: Use the ViewModel
        if (addingNewContact == false) {
            // usa a ViewModel para solicitar a busca pelo novo contato
            addEditViewModel.getContactById(contactID).observe(getViewLifecycleOwner(), new Observer<Contact>() {
                @Override
                public void onChanged(@Nullable final Contact contact) {
                    // atualiza as informações da tela com os dados do contato lido
                    nameTextInputLayout.getEditText().setText(contact.getName());
                    phoneTextInputLayout.getEditText().setText(contact.getPhone());
                    emailTextInputLayout.getEditText().setText(contact.getEmail());
                }
            });
        }

    }

}