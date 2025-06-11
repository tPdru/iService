package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    DAOLocalService daoLocalService;
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

        //Setando as informações de acordo com o card selecionado

        try {
            // Decodifica os bytes da imagem da loja
            Bitmap originalBitmap = BitmapFactory.decodeByteArray(loja.getImgLoja(), 0, loja.getImgLoja().length);

            // Redimensiona a imagem para 300x300 pixels
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 300, 300, true);

            // Exibe a imagem redimensionada no ImageView
            imgFotoLoja.setImageBitmap(resizedBitmap);
        } catch (Exception e) {
            // Caso ocorra erro, aplica uma imagem padrão
            imgFotoLoja.setImageResource(R.drawable.foto_imagem);
        }

        txtNomeLoja.setText(loja.getNomeLoja());
        txtDescricao.setText(loja.getDescricao());

        if (loja.isTemEndereco()) {
            txtEnderecoLoja.setText(loja.getEnderecoLoja().getRua());
        } else {
            txtEnderecoLoja.setText("  ");
        }

        // ----------------- AQUI COMEÇA O FILTRO DE SERVIÇOS DA LOJA -----------------

        // Limpa a lista para garantir que está vazia
        listService.clear();

        // Recupera todos os serviços salvos localmente
        List<ObjCardServicoPp> todosServicos = daoLocalService.readService();

        // Filtra os serviços que pertencem à loja atual e adiciona à listService
        for (ObjCardServicoPp servico : todosServicos) {
            if (servico.getCodigoLoja() == loja.getCodigLoja()) {
                listService.add(servico);
            }
        }

        // ----------------- FIM DO FILTRO -----------------

        //Configurando RecyclerView com os serviços filtrados
        adaptadorLoja = new AdaptadorLoja(getApplicationContext(), listService, new InComunicarServPp() {
            @Override
            public void enviarServico(ObjCardServicoPp objServ) {
                DialogDetalhesServico dialog = new DialogDetalhesServico(objServ);
                dialog.show(getSupportFragmentManager(), "DetalhesServico");
            }
        });

        // Configura o layout horizontal para o RecyclerView de serviços
        recServicosLoja.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recServicosLoja.setAdapter(adaptadorLoja);

        // Botão voltar na toolbar
        topAppBarLoja.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

        btnEntrarEmContato.setOnClickListener(view -> {
            String btn_whatsapp = "";

            if (contMaster.getLoginOn()) {
                List<ObjPerfil> list = new ArrayList<>(daoLocalPerfil.readPerfil());
                for (ObjPerfil perfil : list) {
                    if (loja.getCodUsuario() == perfil.getCodigo()) {
                        btn_whatsapp = perfil.getCelular();
                        break;
                    }
                }

                if (!btn_whatsapp.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String url = "https://api.whatsapp.com/send?phone=+55" + btn_whatsapp + "&text=Olá, vi seus serviços pelo Quack Workes e fiquei interessado. Pode me ajudar?...";
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Número de WhatsApp não encontrado", Toast.LENGTH_SHORT).show();
                }

            } else {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }
}
