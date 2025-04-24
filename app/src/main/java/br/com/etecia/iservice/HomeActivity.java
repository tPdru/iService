package br.com.etecia.iservice;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

        getSupportFragmentManager().beginTransaction().replace(R.id.frmLayoutConteiner,new FragmentHome()).commit();

        //Controle bot navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Recebe o fragmento correspondete ao item selecionado
                Fragment fragment = null;

                if(item.getItemId() == R.id.itemMenuBotHome){

                    fragment = new FragmentHome();

                } else if (item.getItemId() == R.id.itemMenuBotFavoritos) {

                    fragment = new FragmentFavoritos();

                }else if (item.getItemId() == R.id.itemMenuBotMenssagens){

                    fragment = new FragmentChat();

                }


                if(fragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frmLayoutConteiner,fragment).commit();
                }

                return true;
            }
        });

    }


}