package br.com.etecia.iservice;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PerfilActivity extends AppCompatActivity {

    TextView txtNome, txtEmail, txtNomeFt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Apresentação Java + XML
        txtNome = findViewById(R.id.txtPerfilNome);
        txtEmail = findViewById(R.id.txtPerfilEmail);
        txtNomeFt = findViewById(R.id.txtPerfilNomeFt);

        //Pegando as informações do perfil
        ObjPerfil meuPerfil = ControllerMaster.getControllerMaster().getinformacoesPerfil();

        //Preenchendo as informações do perfil
        informacoesPerfil(meuPerfil);
    }

    //Função para preencher informações do perfil
    private void informacoesPerfil(ObjPerfil meuPerfil){
        txtNome.setText(meuPerfil.getNome());
        txtEmail.setText(meuPerfil.getEmail());
        txtNomeFt.setText(meuPerfil.getNome());
    }

}