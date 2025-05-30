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

public class TestarBancoActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    AdaptadorTeste adpTeste;

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

        listaTeste = new ArrayList<>();

        ObjTeste obj = new ObjTeste();
        obj.setNome("Hugo");

        listaTeste.add(obj);




        recyclerView = findViewById(R.id.recTeste);

        adpTeste = new AdaptadorTeste(getApplicationContext(), listaTeste);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        recyclerView.setAdapter(adpTeste);





    }
}