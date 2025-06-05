package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    //Variaveis navegação
    MaterialToolbar materialToolbar;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.home_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Apresentação XML + Java
        materialToolbar = findViewById(R.id.matTooBarActivtHome);
        bottomNavigationView = findViewById(R.id.botNavgationTelaPrincipal);

        //Informações iniciais --------------------------------
        getSupportFragmentManager().beginTransaction().replace(R.id.frmLayoutConteiner, new FragmentHome()).commit();


        //Botões ---------------------

        //Botão perfil
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.itemMenuTopPerfil) {
                    if (ControllerMaster.getControllerMaster().getLoginOn()) {
                        startActivity(new Intent(getApplicationContext(), PerfilActivity.class));
                        return true;
                    }else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        return true;
                    }
                }
                return true;
            }
        });


        //Controle bot navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Recebe o fragmento correspondete ao item selecionado
                Fragment fragment = null;

                if (item.getItemId() == R.id.itemMenuBotHome) {

                    fragment = new FragmentHome();

                } else if (item.getItemId() == R.id.itemMenuBotFavoritos) {

                    fragment = new FragmentFavoritos();

                } else if (item.getItemId() == R.id.itemMenuBotMenssagens) {

                    fragment = new FragmentChat();

                }


                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frmLayoutConteiner, fragment).commit();
                }

                return true;
            }
        });

    }


}