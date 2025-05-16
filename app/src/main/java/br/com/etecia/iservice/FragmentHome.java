package br.com.etecia.iservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {
    //Variáveis de controle
    RecyclerView recHomeLojas;
    AdaptadorModeloCardLoja adpLojas;

    //Variáveis de informação
    List<ObjCardLoja> listaCardLoja;
    List<ObjCardServicoPp> listaServicos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);;

        //Apresentação Java + XML
        recHomeLojas = view.findViewById(R.id.recHomeLojas);

        //Instancias
        listaCardLoja = new ArrayList<>();
        listaServicos = new ArrayList<>();
        adpLojas = new AdaptadorModeloCardLoja(getContext(), listaCardLoja);

        //Configurações iniciais---------------------------------------
        //Adicionando itens para teste
        listaServicos.add(
                new ObjCardServicoPp(R.drawable.foto_imagem, "Corte", 50)
        );
        listaServicos.add(
                new ObjCardServicoPp(R.drawable.foto_imagem, "Corte", 50)
        );
        listaServicos.add(
                new ObjCardServicoPp(R.drawable.foto_imagem, "Corte", 50)
        );
        listaServicos.add(
                new ObjCardServicoPp(R.drawable.foto_imagem, "Corte", 50)
        );




        listaCardLoja.add(
                new ObjCardLoja(R.drawable.foto_usuario, listaServicos, "Barbearia", 5)
        );
        listaCardLoja.add(
                new ObjCardLoja(R.drawable.foto_usuario, listaServicos, "Barbearia", 5)
        );
        listaCardLoja.add(
                new ObjCardLoja(R.drawable.foto_usuario, listaServicos, "Barbearia", 5)
        );
        listaCardLoja.add(
                new ObjCardLoja(R.drawable.foto_usuario, listaServicos, "Barbearia", 5)
        );


        //Configuração RecicleVieww
        recHomeLojas.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recHomeLojas.setAdapter(adpLojas);




        return view;
    }
}