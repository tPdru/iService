package br.com.etecia.iservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentPerfilUsuario extends Fragment {

    TextView txtNome, txtEmail, txtNomeFt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_usuario_layout, container, false);


        //Apresentação Java + XML
        txtNome = view.findViewById(R.id.txtPerfilNome);
        txtEmail = view.findViewById(R.id.txtPerfilEmail);
        txtNomeFt = view.findViewById(R.id.txtPerfilNomeFt);


        //Pegando as informações do perfil
        ObjPerfil meuPerfil = ControllerMaster.getControllerMaster().getinformacoesPerfil();

        //Preenchendo as informações do perfil
        informacoesPerfil(meuPerfil);

        return view;
    }

    //Função para preencher informações do perfil
    private void informacoesPerfil(ObjPerfil meuPerfil){
        txtNome.setText(meuPerfil.getNome());
        txtEmail.setText(meuPerfil.getEmail());
        txtNomeFt.setText(meuPerfil.getNome());
    }
}