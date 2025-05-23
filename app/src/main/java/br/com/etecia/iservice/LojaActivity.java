package br.com.etecia.iservice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class LojaActivity extends AppCompatActivity {

    //Variáveis de informação
    TextView txtNomeLoja, txtEnderecoLoja, txtDescricao;
    ImageView imgFotoLoja;
    String codigo;
    ObjCardLoja loja;

    //Variáveis de controle

    AdaptadorLoja adaptadorLoja;
    RecyclerView recServicosLoja;
    List<ObjCardServicoPp> listService;
    ControllerMaster contMaster = ControllerMaster.getControllerMaster();
    Button btnContatar;
    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.loja_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Apresentação Java + XML
        txtNomeLoja = findViewById(R.id.txtNomeLoja);
        txtEnderecoLoja = findViewById(R.id.txtEnderecoLoja);
        txtDescricao = findViewById(R.id.txtDescricao);
        imgFotoLoja = findViewById(R.id.imgFotoLoja);
        recServicosLoja = findViewById(R.id.recServicosLoja);
        btnContatar = findViewById(R.id.btnContatar);

        //Instancias
        listService = new ArrayList<>();

        //pegando string que foram salvas, na escolha da loja
        codigo = getIntent().getStringExtra("emailEscolhido");

        //Metodo para coletar a loja correta
        loja = contMaster.localizaadorLojas(codigo).getMinhaLoja();

        String numero;

        numero = String.valueOf(loja.getEnderecoLoja().getNumero());


        //Setando as imformaçoes de acordo com o card selecionado
        txtNomeLoja.setText(loja.getNomeLoja());
        imgFotoLoja.setImageResource(loja.getImgLoja());
        txtDescricao.setText(loja.getDescricao());
        txtEnderecoLoja.setText(loja.getEnderecoLoja().getRua() + " " + numero);

        //Passando a lista
        listService = new ArrayList<>(loja.getListaServico());

        //Configurando recycleView

        adaptadorLoja = new AdaptadorLoja(getApplicationContext(), loja.getListaServico());


        recServicosLoja.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

    }
}