package com.example.renaldo.agendadecontatos.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.renaldo.agendadecontatos.R;
import com.example.renaldo.agendadecontatos.data.Contact;

import com.example.renaldo.agendadecontatos.data.Contact;

import static com.example.renaldo.agendadecontatos.MainActivity.CONTACT_ID;


public class DetailFragment extends Fragment {

    private int contactID;
    // TODO componentes TextView
    private TextView nameTextView;
    private  TextView phoneTextView;
    private  TextView emailTextView;

    private DetailViewModel detailViewModel;

    private DetailViewModel mViewModel;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.detail_fragment, container, false);

        setHasOptionsMenu(true);
        // TODO componentes textview
        nameTextView = (TextView) view.findViewById(R.id.nameTextInputLayout);
        phoneTextView = (TextView) view.findViewById(R.id.phoneTextInputLayout);
        emailTextView = (TextView) view.findViewById(R.id.emailTextInputLayout);

        // acessa a lista de argumentos enviada ao fragmento em busca do ID do contato
        Bundle arguments = getArguments();
        if (arguments != null)
            contactID = arguments.getInt(CONTACT_ID);
        return view;

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // seleciona o menu a ser mostrado no fragmento
        inflater.inflate(R.menu.fragment_details_menu, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                editContact();
                return true;
            case R.id.action_delete:
                deleteContact();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editContact(){
        //cria um pacote de argumentos
        Bundle arguments = new Bundle();
        // adiciona o ID do contato como argumento a ser passado ao fragmento
        arguments.putInt(CONTACT_ID, contactID);
        //solicita a transição de tela para o fragmento de editar um novo contato
        Navigation.findNavController(getView()).navigate(R.id.action_detailFragment_to_addEditFragment, arguments);
    }

    private void deleteContact() {
        // TODO deletar contato
        // solicita o retorno à tela anterior
        detailViewModel.delete();

        Navigation.findNavController(getView()).popBackStack();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.getContactById(contactID).observe(getViewLifecycleOwner(), contact -> {
            nameTextView.setText(contact.getName());
            phoneTextView.setText(contact.getPhone());
            emailTextView.setText(contact.getEmail());
        });
    }

}