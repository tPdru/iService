package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {

    //Variáveis de controle
    Button btnLogin, btnCadastrar;
    MaterialToolbar materialToolbar;
    int tentativas = 0;
    ControllerMaster controllerMaster;


    //Variáveis de informação
    EditText txtEmail, txtSenha;
    DAOLocalPerfil daoLocalPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Apresentação Java + XML
        materialToolbar = findViewById(R.id.topAppBarPerfil);
        btnLogin = findViewById(R.id.btnLogar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        txtEmail = findViewById(R.id.txtEmailUsuario);
        txtSenha = findViewById(R.id.txtSenhaUsuario);

        controllerMaster = ControllerMaster.getControllerMaster();

        daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());

        controllerMaster.carregarperfis(daoLocalPerfil.readPerfil());



        //Botões ---------------------------------------------

        //Botão Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String senha = txtSenha.getText().toString().trim();

                ControllerMaster.getControllerMaster().autenticarConta(email, senha);
                if (ControllerMaster.getControllerMaster().getLoginOn()) {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                }else {
                    tentativas++;
                    Toast.makeText(LoginActivity.this, "Senha ou e-mail invalidos!", Toast.LENGTH_SHORT).show();
                    if (tentativas < 3) {
                        txtSenha.clearComposingText();
                    }else {
                        Toast.makeText(LoginActivity.this, "Conta bloqueada!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Botão Cadastrar
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CadastrarActivity.class));
            }
        });

        //Btn Voltar
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

    }
}