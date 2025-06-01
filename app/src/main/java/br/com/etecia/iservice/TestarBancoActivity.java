package br.com.etecia.iservice;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestarBancoActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    List<ObjTeste> listaTeste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.testar_banco_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recTeste);


        // Inicializa o ApiRequest com o contexto da aplicação
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.init(getApplicationContext());

        DAOUsuario usu = new DAOUsuario();
        List<ObjPerfil> listaPerf = new ArrayList<>();



        //Adicionando itens de forma manual
        listaTeste.add(new ObjTeste("Hugo"));
        listaTeste.add(new ObjTeste("Hugo"));
        listaTeste.add(new ObjTeste("Hugo"));
        listaTeste.add(new ObjTeste("Hugo"));


        AdaptadorTeste adpTeste = new AdaptadorTeste(getApplicationContext(), listaTeste);

        //configurando o recyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(adpTeste);


        //pegando a lista atravez de uma interface (A interface tem que cer passada como parametro na classe que envia a informação)
        usu.readPerfil(getApplicationContext(), new InRespostaPerfil() {
            @Override
            public void listaReadPerfil(List<ObjPerfil> listaPerfils) {
                //Testando se a liosta esta vazia
                if ( listaPerfils != null ) {
                    //Passando as informações para a lista do recycleView
                    for (int i = 0; i < listaPerfils.size(); i++) {
                        listaTeste.add(new ObjTeste(listaPerfils.get(i).getNome()));
                    }
                    adpTeste.notifyDataSetChanged();
                }

            }

            @Override
            public void listaReadLoja(List<ObjCardLoja> listaLojas) {

            }
        });


    }

}