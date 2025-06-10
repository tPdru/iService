package br.com.etecia.iservice;

import android.content.Intent;
import android.net.Uri;
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
    Button btnEntrarEmContato;
    MaterialToolbar topAppBarLoja;

    //Banco Local
    DAOLocalPerfil daoLocalPerfil;
    DAOLocalLoja daoLocalLoja;
    DAOLocalService daoLocalService ;
    DAOLocalEndereco daoLocalEndereco;

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
        btnEntrarEmContato = findViewById(R.id.btnContatar);
        topAppBarLoja = findViewById(R.id.topAppBarLoja);


        //Instancias
        listService = new ArrayList<>();
        daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());
        daoLocalLoja = new DAOLocalLoja(getApplicationContext());
        daoLocalService = new DAOLocalService(getApplicationContext());
        daoLocalEndereco = new DAOLocalEndereco(getApplicationContext());

        //pegando string que foram salvas, na escolha da loja
        codigo = getIntent().getStringExtra("emailEscolhido");

        long id = Long.parseLong(codigo);

        //pegando as lojas do banco
        List<ObjCardLoja> lojas = new ArrayList<>(daoLocalLoja.readLojas());

        //Metodo para coletar a loja correta
        for (int i = 0; i < lojas.size(); i++) {
            if (id == lojas.get(i).getCodigLoja()) {
                loja = lojas.get(i);
                break;
            }
        }


        //Setando as imformaçoes de acordo com o card selecionado
        txtNomeLoja.setText(loja.getNomeLoja());
        //imgFotoLoja.setImageResource(loja.getImgLoja());
        txtDescricao.setText(loja.getDescricao());
        if (loja.isTemEndereco()) {
            txtEnderecoLoja.setText(loja.getEnderecoLoja().getRua());
        }
        if (loja.isTemServicos()) {

        }
        listService = new ArrayList<>(daoLocalService.readService());

        //Configurando recycleView
        adaptadorLoja = new AdaptadorLoja(getApplicationContext(), listService, new InComunicarServPp() {
            @Override
            public void enviarServico(ObjCardServicoPp objServ) {
                DialogDetalhesServico dialog = new DialogDetalhesServico(objServ);
                dialog.show(getSupportFragmentManager(), "DetalhesServico");
            }
        });



        recServicosLoja.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        recServicosLoja.setAdapter(adaptadorLoja);

        topAppBarLoja.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LojaActivity.this, " " + listService.size(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                finish();
            }
        });

        /**btnEntrarEmContato.setOnClickListener(view -> new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatsapp = "";
                if (contMaster.getLoginOn()){
                    List<ObjPerfil> list = new ArrayList<>(daoLocalPerfil.readPerfil());
                    for (int i=0; i < list.size();i++){
                        if (loja.getCodUsuario()==list.get(i).getCodigo()){
                            whatsapp = list.get(i).getCelular();
                            break;
                        }

                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String url = "https://wa.me/" + whatsapp;
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
                else{
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

            }
        });**/


    }
}