package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    TextInputEditText txtNome, txtemail, txtSenha, txtReSenha, txtUsuario, txtCelular;


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
        txtUsuario = findViewById(R.id.txtUsuario);
        txtReSenha = findViewById(R.id.txtConfirmarSenhaUsuario);
        txtCelular = findViewById(R.id.txtCelular);


        //Botões----------------------------------------------------------------------------
        //Botão criar conta chama o dialog para o usuário decidir se quer ir para a criação
        //da loja.
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar os textos
                String email = txtemail.getText().toString().trim();
                String senha = txtSenha.getText().toString().trim();
                String nome = txtNome.getText().toString().trim();
                String usuario = txtUsuario.getText().toString().trim();
                String confirmarSenha = txtReSenha.getText().toString().trim();

                //Testar os textos
                if (checkCampo(usuario, txtUsuario) && checkCampo(nome, txtNome) &&
                    checkCampo(email, txtemail) && checkCampo(senha, txtSenha) &&
                    checkCampo(confirmarSenha, txtReSenha)){

                    //Testando se a senha e confirmação são iguais
                    if ( senha.equals(confirmarSenha) ) {
                        ObjPerfil perfil = new ObjPerfil(
                                ControllerMaster.getControllerMaster().getCodigoList() + 1,
                                email,
                                nome,
                                senha,
                                usuario,
                                R.drawable.foto_usuario,
                                false
                        );

                        //Adicionando o celular ao objeto
                        if (!TextUtils.isEmpty(txtCelular.getText().toString().trim())){
                            perfil.setCelular(txtCelular.getText().toString().trim());
                        }

                        //cria a conta
                        ControllerMaster.getControllerMaster().criarPerfil(perfil, email);
                        //efetua o login apos a criação
                        ControllerMaster.getControllerMaster().autenticarConta(email, senha);

                        //chama o dialog fragment para decidir sobre a criação da loja
                        DialogOpcaoCadastrarLoja dialog = new DialogOpcaoCadastrarLoja();
                        dialog.show(getSupportFragmentManager(),"Cadastrar loja?");
                    }
                    else{
                        msgConfirmacaoSenha(txtReSenha);
                    }
                }
            }
        });

    }

    // Metodo de checar se os campos estão preenchidos
    private boolean checkCampo(String texto, TextInputEditText inputEditText){
        if ( TextUtils.isEmpty(texto) ) {
            inputEditText.setError("Preencha todos os campos!");
            inputEditText.requestFocus();
            return false;
        }else {
            return true;
        }
    }
    // Metodo teste senha e confirmação
    private void msgConfirmacaoSenha(TextInputEditText inputEditText) {
        inputEditText.setError("A senha e a confirmação são diferentes!");
        inputEditText.requestFocus();
    }

    @Override
    public void dialogPassarDados() {
    }
}