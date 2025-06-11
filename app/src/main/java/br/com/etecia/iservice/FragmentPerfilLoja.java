package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FragmentPerfilLoja extends Fragment {

    //Variáveis de Informação
    ObjCardLoja minhaLoja = ControllerMaster.getControllerMaster().getInformacoesPerfil().getMinhaLoja();
    ControllerMaster cM = ControllerMaster.getControllerMaster();
    TextView txtNome, txtDescricao, txtEstado, txtCidade, txtLograd, txtRua, txtNumero, txtComplemento;
    ImageView imgLoja;
    private long id;

    //Variáveis de controle
    RecyclerView recyclerView;
    AdaptadorPerfilLojaServicos adpLojasServicos;
    CardView cardAdicionarServico;
    InCriarServ inCriarServ;
    ControllerMaster contMaster = ControllerMaster.getControllerMaster();

    // Banco Local
    DAOLocalEndereco daoLocalEndereco;
    DAOLocalLoja daoLocalLoja;
    DAOLocalService daoLocalService;

    //Construtor com id
    public FragmentPerfilLoja(long id) {
        this.id = id;
    }
    //Construtor vazio
    public FragmentPerfilLoja() {
    }


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
        cardAdicionarServico = view.findViewById(R.id.cardAdicionarServico);


        imgLoja = view.findViewById(R.id.imgPerfilLoja);

        recyclerView = view.findViewById(R.id.recPerfilLojaServicos);

        //Instancias
        daoLocalEndereco = new DAOLocalEndereco(getContext());
        daoLocalLoja = new DAOLocalLoja(getContext());
        daoLocalService = new DAOLocalService(getContext());

        //minha loja
        minhaLoja = minhaLoja(id);
        adpLojasServicos = new AdaptadorPerfilLojaServicos(getContext(), meusServicos(minhaLoja));


        ObjEndereco endereco = meuEndereco(minhaLoja.getCodigLoja());

        //Controlando informaçoes-----------------------------------------

        txtNome.setText(minhaLoja.getNomeLoja());
        txtDescricao.setText(minhaLoja.getDescricao());

        if (minhaLoja.getImgLoja() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(minhaLoja.getImgLoja(), 0, minhaLoja.getImgLoja().length);
            imgLoja.setImageBitmap(bitmap);
        }else {
            imgLoja.setImageResource(R.drawable.foto_imagem);
        }




        if ( endereco != null ) {
            //Colocando informações
            //Com endereço
            if ( minhaLoja.isTemEndereco() ) {


                txtEstado.setText(endereco.getEstado());
                txtCidade.setText(endereco.getCidade());
                txtLograd.setText(endereco.getBairro());
                txtRua.setText(endereco.getRua());
                txtNumero.setText(endereco.getNumero());
                txtComplemento.setText(endereco.getComplemento());
            }
        }

        // Card adicionar novo serviço
        cardAdicionarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCriarServico dialog = new DialogCriarServico(minhaLoja.getCodigLoja(), new InCriarServ() {
                    @Override
                    public void salvar(ObjCardServicoPp servicoPp) {
                        //guarda no banco
                        daoLocalService.inserirService(servicoPp);
                    }
                });
                dialog.show(getChildFragmentManager(), "criarServ");
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adpLojasServicos = new AdaptadorPerfilLojaServicos(getContext(), meusServicos(minhaLoja));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));;
        recyclerView.setAdapter(adpLojasServicos);
        adpLojasServicos.notifyDataSetChanged();
    }

    //busca endereço no banco
    private ObjEndereco meuEndereco(long codigo) {



        List<ObjEndereco> listaEnd = new ArrayList<>(daoLocalEndereco.readEndereco());
        ObjEndereco endereco;

        for (int i = 0; i < listaEnd.size(); i++) {
            if(codigo == listaEnd.get(i).getCodigoLoja());{
                return endereco = listaEnd.get(i);
            }
        }
        return endereco = new ObjEndereco();
    }

    //Busca loja no banco
    private ObjCardLoja minhaLoja(long codigo) {

        List<ObjCardLoja> listaLoja = new ArrayList<>(daoLocalLoja.readLojas());
        ObjCardLoja loja;

        for (int i = 0; i < listaLoja.size(); i++) {
            if (codigo == listaLoja.get(i).getCodUsuario()) {
                loja = listaLoja.get(i);
                return loja;
            }
        }
        return  new ObjCardLoja();
    }

    // Busca os serviços no banco
    private List<ObjCardServicoPp> meusServicos(ObjCardLoja loja) {
        List<ObjCardServicoPp> list = new ArrayList<>(daoLocalService.readService());
        List<ObjCardServicoPp> listFinal = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            if (loja.getCodigLoja() == list.get(i).getCodigoLoja()) {
                listFinal.add(list.get(i));
            }
        }

        return listFinal;
    }


}