package br.com.etecia.iservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;


public class FragmentPerfilUsuario extends Fragment {

    TextView txtNome, txtEmail, txtNomeFt;

    //ImageView imgPerfil;


    private void carregarImagemLoca(){
        try{
            FileInputStream fis = requireContext().openFileInput("perfil.png");
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            fis.close();

            ImageView imgPerfil = getView().findViewById(R.id.imgPerfil);
            imgPerfil.setImageBitmap(bitmap);
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_usuario_layout, container, false);


        //Apresentação Java + XML
        txtNome = view.findViewById(R.id.txtPerfilNome);
        txtEmail = view.findViewById(R.id.txtPerfilEmail);
        txtNomeFt = view.findViewById(R.id.txtPerfilNomeFt);
        //imgPerfil = view.findViewById(R.id.imgPerfil);


        //Pegando as informações do perfil
        ObjPerfil meuPerfil = ControllerMaster.getControllerMaster().getInformacoesPerfil();

        //Preenchendo as informações do perfil
        informacoesPerfil(meuPerfil);

        carregarImagemLoca();

        return view;
    }

    //Função para preencher informações do perfil
    private void informacoesPerfil(ObjPerfil meuPerfil){
        txtNome.setText(meuPerfil.getNome());
        txtEmail.setText(meuPerfil.getEmail());
        txtNomeFt.setText(meuPerfil.getNome());
    }
}