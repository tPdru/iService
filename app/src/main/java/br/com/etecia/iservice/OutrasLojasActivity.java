package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class OutrasLojasActivity extends AppCompatActivity {

    //Variáveis de informação
    TextView txtNome, txtDescricao;
    ImageView imgFotoLoja;
    String codigo;
    ObjCardLoja loja;

    //Variáveis de controle
    RecyclerView recyclerView;
    List<ObjCardServicoPp> listService;
    ControllerMaster contMaster = ControllerMaster.getControllerMaster();
    Button btnContatar;
    MaterialToolbar materialToolbar;
    AdaptadorPerfilLojaServicos adtLojaServicos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.outras_lojas_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Apresentação Java + XML
        txtNome = findViewById(R.id.txtOutrasLojasNome);
        txtDescricao = findViewById(R.id.txtOutrasLojasDescricao);
        recyclerView = findViewById(R.id.recOutrasLojasServicos);
        imgFotoLoja = findViewById(R.id.imgOutrasLojas);
        materialToolbar = findViewById(R.id.matTooBarOutrasLojas);

        //Instancias
        listService = new ArrayList<>(contMaster.getInformacoesPerfil().getMinhaLoja().getListaServico());//Copiando a lista do controller
        adtLojaServicos = new AdaptadorPerfilLojaServicos(getApplicationContext(), listService);

        //pegando string que foram salvas, na escolha da loja
        codigo = getIntent().getStringExtra("emailEscolhido");

        //Metodo para coletar a loja correta
        //loja = contMaster.localizaadorLojas(codigo).getMinhaLoja();

        //Setando as imformaçoes de acordo com o card selecionado
        txtNome.setText(loja.getEmailDono());
        txtDescricao.setText(loja.getDescricao());
        imgFotoLoja.setImageResource(loja.getImgLoja());

        //Passando a lista
        listService = new ArrayList<>(loja.getListaServico());

        //Configurando recycleView

        // Botões -----------------------------------------------------------
        // botão arow back
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });


    }

}