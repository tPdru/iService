package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;


public class FragmentPerfilUsuario extends Fragment {

    TextView txtNome, txtEmail, txtNomeFt;

    ImageView imgPerfil;

    Button btnSairConta;
    ControllerMaster contMaster = ControllerMaster.getControllerMaster();

    //Pegando as informações do perfil
    ObjPerfil meuPerfil = ControllerMaster.getControllerMaster().getInformacoesPerfil();

    //carregando dados do perfil
    byte[] imageBytes = meuPerfil.getFoto();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_usuario_layout, container, false);


        //Apresentação Java + XML
        txtNome = view.findViewById(R.id.txtPerfilNome);
        txtEmail = view.findViewById(R.id.txtPerfilEmail);
        txtNomeFt = view.findViewById(R.id.txtPerfilNomeFt);
        imgPerfil = view.findViewById(R.id.imgPerfil);
        btnSairConta = view.findViewById(R.id.btnSairDaConta);

        //Preenchendo as informações do perfil
        informacoesPerfil(meuPerfil);

        //Botões ---------------------
        //Botão sair da conta
        btnSairConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contMaster.logout();
                startActivity(new Intent(getContext(), SplashActivity.class));
            }
        });


        return view;
    }

    //Função para preencher informações do perfil
    private void informacoesPerfil(ObjPerfil meuPerfil){
        txtNome.setText(meuPerfil.getNome());
        txtEmail.setText(meuPerfil.getEmail());
        txtNomeFt.setText(meuPerfil.getNome());
        if ( meuPerfil.getFoto() != null ) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0,imageBytes.length);
            imgPerfil.setImageBitmap(bitmap);
        }else {
            imgPerfil.setImageResource(R.drawable.foto_usuario);
        }


    }
}