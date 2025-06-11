package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    ControllerMaster contMaster = ControllerMaster.getControllerMaster();
    ImageView imgLojaInicial;
    byte[] imageBytes;

    //Banco local
    DAOLocalPerfil daoLocalPerfil;
    DAOLocalLoja daoLocalLoja;

    private byte[] imageViewToByte(ImageView imgLojaInicial) {
        // Pega o drawable (imagem) do ImageView e o converte para Bitmap
        Bitmap bitmap = ((BitmapDrawable) imgLojaInicial.getDrawable()).getBitmap();

        // Cria um fluxo de bytes na memória para armazenar a imagem comprimida
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Comprime o bitmap em formato PNG com qualidade 100% e escreve no stream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        // Retorna o array de bytes com os dados da imagem comprimida
        return stream.toByteArray();
    }



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

        imgLojaInicial = findViewById(R.id.imgLojaInicial);

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
                /*daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());
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



                    imgLojaInicial.setImageResource(R.drawable.foto_imagem);
                    imageBytes = imageViewToByte(imgLojaInicial);




                    loja.setNomeLoja("Oficina");
                    loja.setImgLoja(imageBytes);
                    loja.setCpfCnpj("55577733");
                    loja.setDescricao("Reparos em carros");
                    loja.setTemEndereco(false);
                    loja.setTemServicos(false);
                    loja.setCodUsuario(perfil.getCodigo());

                    daoLocalLoja.inserirLoja(loja);
                }

                // Coloca os perfis na lista do controleMaster
                contMaster.setCarregarListaPerfil(daoLocalPerfil.readPerfil());*/

                //contMaster.carregarLojas();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }, 2000);

    }

}