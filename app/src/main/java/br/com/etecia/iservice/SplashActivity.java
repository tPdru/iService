package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    ControllerMaster contMaster = ControllerMaster.getControllerMaster();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Definindo que o app não possui conta logada no inicio
                ControllerMaster.getControllerMaster().setLoginOn(false);

                //Adicionando perfis/loja/serviços de forma manual para testes
                addPerfis("teste1.barbeiro@exemplo.com");
                addPerfis("teste2.barbeiro@exemplo.com");
                addPerfis("teste3.barbeiro@exemplo.com");
                addPerfis("teste4.barbeiro@exemplo.com");
                addPerfis("teste5.barbeiro@exemplo.com");

                // Inicializa o ApiRequest com o contexto da aplicação
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.init(getApplicationContext());

                //adicionando lojas atravez do banco
                DAOLoja daoLoja = new DAOLoja();
                contMaster.carregarLojasBanco(daoLoja.readLojas(getApplicationContext()));


                //contMaster.carregarLojas();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }, 2000);

        //Adicionar elementos para teste-------------------------------------------
        //Adicionando Perfis



    }
    private void addPerfis(String email){
        //Criação de conta teste

        ObjCardLoja novaLoja;
        List<ObjCardServicoPp> listaServicos = new ArrayList<>();

        //Criando serviços
        ObjCardServicoPp servicoPp = new ObjCardServicoPp(
                R.drawable.foto_imagem,
                "Corte",
                "Qualquer corte mesmo valor",
                35.00);
        listaServicos.add(servicoPp);

        servicoPp = new ObjCardServicoPp(
                R.drawable.foto_imagem,
                "Barba",
                "Qualque estilo é o mesmo valor",
                30.00);
        listaServicos.add(servicoPp);

        servicoPp = new ObjCardServicoPp(
                R.drawable.foto_imagem,
                "Sombrancelhas",
                "Escolha como quer sua sombrancelha valores não mudão",
                20.00);
        listaServicos.add(servicoPp);

        servicoPp = new ObjCardServicoPp(
                R.drawable.foto_imagem,
                "Pintura",
                "Com sua tintura é mais barato",
                40.00);
        listaServicos.add(servicoPp);

        //Criando um endereço
        ObjEndereco endere = new ObjEndereco(
                111,
                "São Paulo",
                "BLoco B",
                "São Paulo",
                "Satelite",
                23,
                "Laranjeiras"
        );


        ObjPerfil perfil = new ObjPerfil(
                ControllerMaster.getControllerMaster().getCodigoList() + 1,
                email,
                "Neymar Júnior",
                "Senha",
                "Ney",
                R.drawable.foto_usuario,
                true,
                novaLoja = new ObjCardLoja(
                        email,
                        R.drawable.foto_imagem,
                        listaServicos,
                        "Barbearia",
                        4.5,
                        endere
                )
        );
        perfil.getMinhaLoja().setTemEndereco(true);
        ControllerMaster.getControllerMaster().criarPerfil(perfil, email);
    }
}