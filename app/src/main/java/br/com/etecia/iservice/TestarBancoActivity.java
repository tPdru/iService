package br.com.etecia.iservice;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TestarBancoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ObjTeste> listaTeste;

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
        listaTeste = new ArrayList<>();


        // Inicializa o ApiRequest com o contexto da aplicação
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.init(getApplicationContext());

        DAOUsuario usu = new DAOUsuario();
        List<ObjPerfil> listaPerf = new ArrayList<>();
        listaPerf = usu.readPerfil(getApplicationContext());

        //Toast.makeText(this, listaPerf.get(0).getNome(), Toast.LENGTH_SHORT).show();


        /*for (int i = 0; i < listaPerf.size(); i++) {
            listaTeste.add(new ObjTeste(listaPerf.get(i).getNome()));
        }*/





        listaTeste.add(new ObjTeste("Hugo"));
        listaTeste.add(new ObjTeste("Hugo"));
        listaTeste.add(new ObjTeste("Hugo"));
        listaTeste.add(new ObjTeste("Hugo"));




        AdaptadorTeste adpTeste = new AdaptadorTeste(getApplicationContext(), listaTeste);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        recyclerView.setAdapter(adpTeste);


    }
}