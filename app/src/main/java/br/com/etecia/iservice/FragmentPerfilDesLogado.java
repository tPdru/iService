package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentPerfilDesLogado extends Fragment {

    Button btnLogar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_des_logado_layout, container, false);

        //Apresentação Java + XML
        btnLogar = view.findViewById(R.id.btnPerfilDeslogado);

        //Botões
        //Botão levar para tela de login
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

        return view;
    }
}