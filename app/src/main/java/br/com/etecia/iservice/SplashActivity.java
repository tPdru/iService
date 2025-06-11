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
    DAOLocalEndereco daoLocalEndereco;
    DAOLocalService daoLocalService;

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

                //Banco local
                // Instancias
                daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());
                daoLocalLoja = new DAOLocalLoja(getApplicationContext());
                daoLocalEndereco = new DAOLocalEndereco(getApplicationContext());
                daoLocalService = new DAOLocalService(getApplicationContext());


                // Coloca os perfis na lista do controleMaster
                /*contMaster.setCarregarListaPerfil(daoLocalPerfil.readPerfil());*/

                //contMaster.carregarLojas();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }, 2000);

    }

    private void adiconar() {

        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Hugo Suterio");
        perfil.setEmail("teste4.barbeiro@exemplo.com");
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
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());

        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(45644);
        endereco.setEstado("");
        endereco.setCidade("");
        endereco.setBairro("");
        endereco.setRua("");
        endereco.setNumero(1);
        endereco.setComplemento("");
        endereco.setCodigoLoja(loja.getCodigLoja());

        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        ObjCardServicoPp ser = new ObjCardServicoPp();

        ser.setTxtNomeServicoPp("");
        ser.setTxtDetalhesServicoPp("");
        ser.setTxtValorServicoPp("");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());

        ser.setCodigo(daoLocalService.inserirService(ser));

        ser = new ObjCardServicoPp();

        ser.setTxtNomeServicoPp("");
        ser.setTxtDetalhesServicoPp("");
        ser.setTxtValorServicoPp("");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());

        ser.setCodigo(daoLocalService.inserirService(ser));

    }


}