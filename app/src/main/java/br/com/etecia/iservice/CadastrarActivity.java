package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class CadastrarActivity extends AppCompatActivity implements DialogOpcaoCadastrarLoja.DialogListerCadastro {

    //VAriáveis de controle
    Button btnCriarConta;

    //Variáveis de informação
    TextInputEditText txtNome, txtemail, txtSenha, txtReSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastrar_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Apresentação Java + XML----------------------------------------------------------
        btnCriarConta = findViewById(R.id.btnCriarConta);
        txtemail = findViewById(R.id.txtEmailUsuario);
        txtNome = findViewById(R.id.txtNomeCompleto);
        txtSenha = findViewById(R.id.txtSenhaUsuario);


        //Botões----------------------------------------------------------------------------
        //Botão criar conta chama o dialog para o usuário decidir se quer ir para a criação
        //da loja.
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtemail.getText().toString().trim();
                String senha = txtSenha.getText().toString().trim();

                ObjPerfil perfil = new ObjPerfil(
                        ControllerMaster.getControllerMaster().getCodigoList() + 1,
                        email,
                        txtNome.getText().toString(),
                        senha,
                        false
                );

                ControllerMaster.getControllerMaster().criarPerfil(perfil, email);
                ControllerMaster.getControllerMaster().autenticarConta(email, senha);

                DialogOpcaoCadastrarLoja dialog = new DialogOpcaoCadastrarLoja();
                dialog.show(getSupportFragmentManager(),"Cadastrar loja?");
            }
        });

    }

    @Override
    public void dialogPassarDados() {
    }
}