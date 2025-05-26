package br.com.etecia.iservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class FragmentPerfilLoja extends Fragment {

    //Variáveis de Informação
    ObjCardLoja minhaLoja = ControllerMaster.getControllerMaster().getInformacoesPerfil().getMinhaLoja();
    ControllerMaster cM = ControllerMaster.getControllerMaster();
    TextView txtNome, txtDescricao, txtEstado, txtCidade, txtLograd, txtRua, txtNumero, txtComplemento;
    ImageView imgLoja;

    //Variáveis de controle
    RecyclerView recyclerView;
    AdaptadorPerfilLojaServicos adpLojasServicos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil_loja_layout, container, false);

        //Apresentação Java + XML
        txtNome = view.findViewById(R.id.txtNomeLoja);
        txtDescricao = view.findViewById(R.id.txtDescricaoLoja);
        txtEstado = view.findViewById(R.id.txtEstadoLoja);
        txtCidade = view.findViewById(R.id.txtCidadeLoja);
        txtLograd = view.findViewById(R.id.txtLogradouroLoja);
        txtRua = view.findViewById(R.id.txtRuaLoja);
        txtNumero = view.findViewById(R.id.txtNumeroLoja);
        txtComplemento = view.findViewById(R.id.txtComplementoLoja);

        imgLoja = view.findViewById(R.id.imgPerfilLoja);

        recyclerView = view.findViewById(R.id.recPerfilLojaServicos);

        //Instancias
        //adpLojasServicos = new AdaptadorPerfilLojaServicos();

        ObjEndereco endereco = minhaLoja.getEnderecoLoja();

        //Controlando informaçoes-----------------------------------------

        if ( cM.getInformacoesPerfil().isTemLoja() ) {
            //Colocando informações
            //Com endereço
            if ( minhaLoja.isTemEndereco() ) {
                txtNome.setText(minhaLoja.getNomeLoja());
                txtDescricao.setText(minhaLoja.getDescricao());
                imgLoja.setImageResource(minhaLoja.getImgLoja());

                txtEstado.setText(endereco.getEstado());
                txtCidade.setText(endereco.getCidade());
                txtLograd.setText(endereco.getLogradouro());
                txtRua.setText(endereco.getRua());
                txtNumero.setText(endereco.getNumero());
                txtComplemento.setText(endereco.getComplemento());
            }else {
                //Sem endereço
                txtNome.setText(minhaLoja.getNomeLoja());
                txtDescricao.setText(minhaLoja.getDescricao());
                imgLoja.setImageResource(minhaLoja.getImgLoja());
            }
        }else {


        }


        return view;
    }
}