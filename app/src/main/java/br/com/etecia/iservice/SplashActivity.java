package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

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

                //Criação de conta teste
                String email = "teste.barbeiro@exemplo.com";
                ObjPerfil perfil = new ObjPerfil(
                        ControllerMaster.getControllerMaster().getCodigoList() +1,
                        email,
                        "Neymar Júnior",
                        "Senha",
                        false
                );
                ControllerMaster.getControllerMaster().criarPerfil(perfil, email);


                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }, 2000);
    }
}