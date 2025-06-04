package br.com.etecia.iservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FragmentHome extends Fragment {
    //Variáveis de controle
    RecyclerView recHomeLojas;
    AdaptadorModeloCardLoja adpLojas;
    ControllerMaster contMaster = ControllerMaster.getControllerMaster();

    //Variáveis de informação
    List<ObjCardLoja> listaCardLoja;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);;

        //Apresentação Java + XML
        recHomeLojas = view.findViewById(R.id.recHomeLojas);

        //Instancias
        listaCardLoja = new ArrayList<>(contMaster.getListaLojas());
        adpLojas = new AdaptadorModeloCardLoja(getContext(), listaCardLoja);

        //Configurações iniciais---------------------------------------

        //Configuração RecicleVieww
        recHomeLojas.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recHomeLojas.setAdapter(adpLojas);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        //adicionando lojas atravez do banco
        /** Atualizaçaõ via banco online
        DAOLoja daoLoja = new DAOLoja();
        daoLoja.readLojas(requireContext(), new InRespostaPerfil() {
            @Override
            public void listaReadPerfil(List<ObjPerfil> listaPerfils) {

            }
            @Override
            public void listaReadLoja(List<ObjCardLoja> listaLojas) {

                if (listaLojas != null) {
                    if (listaCardLoja != null) {
                        listaCardLoja.clear();
                    }
                    listaCardLoja.addAll(listaLojas);
                    adpLojas.atualizarListaAgendados(listaLojas);
                    Toast.makeText(getContext(), listaLojas.get(0).getNomeLoja(), Toast.LENGTH_SHORT).show();
                }

            }
        });
         */

    }
}