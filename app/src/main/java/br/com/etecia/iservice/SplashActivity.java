package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    ControllerMaster contMaster = ControllerMaster.getControllerMaster();

    //Banco local
    DAOLocalPerfil daoLocalPerfil;
    DAOLocalLoja daoLocalLoja;

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
                contMaster.setLoginOn(false);

                /*addPerfis("pato.workes@gmail.com");
                addPerfis("teste2.barbeiro@exemplo.com");
                addPerfis("teste3.barbeiro@exemplo.com");
                addPerfis("teste4.barbeiro@exemplo.com");*/

                /** Banco de dados online
                // Inicializa o ApiRequest com o contexto da aplicação
                ApiRequest apiRequest = new ApiRequest();
                apiRequest.init(getApplicationContext());

                //adicionando lojas atravez do banco
                DAOLoja daoLoja = new DAOLoja();
                daoLoja.readLojas(getApplicationContext(), new InRespostaPerfil() {
                    @Override
                    public void listaReadPerfil(List<ObjPerfil> listaPerfils) {

                    }
                    @Override
                    public void listaReadLoja(List<ObjCardLoja> listaLojas) {
                        if ( listaLojas != null ) {
                            contMaster.carregarLojasBanco(new ArrayList<>(listaLojas));
                        }

                    }
                });
                */

                //Banco local
                // Instancias
                daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());
                daoLocalLoja = new DAOLocalLoja(getApplicationContext());


                //Adicionando lojas no banco local de forma manual
                List<ObjCardLoja> lista = new ArrayList<>(daoLocalLoja.readLojas());
                List<String> emails = new ArrayList<>();
                emails.add("teste1.barbeiro@exemplo.com");
                emails.add("teste2.barbeiro@exemplo.com");
                emails.add("teste3.barbeiro@exemplo.com");
                emails.add("teste4.barbeiro@exemplo.com");


                for (int i = 3; i > lista.size(); i--) {


                    ObjPerfil perfil = new ObjPerfil();
                    ObjCardLoja loja = new ObjCardLoja();

                    perfil.setNome("Hugo Suterio");
                    perfil.setEmail(emails.get(i));
                    perfil.setUsuario("Hugo");
                    perfil.setSenha("123456");
                    perfil.setTemLoja(true);
                    perfil.setCelular("184758963");

                    perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));


                    loja.setNomeLoja("Oficina");
                    loja.setImgLoja(R.drawable.foto_imagem);
                    loja.setCpfCnpj("55577733");
                    loja.setDescricao("Reparos em carros");
                    loja.setTemEndereco(false);
                    loja.setTemServicos(false);
                    loja.setCodUsuario(perfil.getCodigo());

                    daoLocalLoja.inserirLoja(loja);
                    Toast.makeText(SplashActivity.this, "Insert: ", Toast.LENGTH_SHORT).show();
                }

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

        // avisando que tem servisos e endereço
        perfil.getMinhaLoja().setTemServicos(true);
        perfil.getMinhaLoja().setTemEndereco(true);

        ControllerMaster.getControllerMaster().criarPerfil(perfil, email);
    }
}