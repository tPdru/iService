package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentPerfilSemLoja extends Fragment {

    Button btnCriarLoja;
    ControllerMaster contMaster = ControllerMaster.getControllerMaster();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_sem_loja_layout, container, false);

        //Apresentações Java + XML
        btnCriarLoja = view.findViewById(R.id.btnPerfilLojaSemLoja);

        //Botões
        //Botão que leva para tela de login ou cadastrar loja
        btnCriarLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( contMaster.getLoginOn() ){
                    startActivity(new Intent(getContext(), CadastrarLojaActivity.class));
                } else {
                   startActivity(new Intent(getContext(), LoginActivity.class));
                }
            }
        });


        return view;
    }
}