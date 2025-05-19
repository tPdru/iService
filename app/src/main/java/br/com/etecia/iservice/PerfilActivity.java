package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

public class PerfilActivity extends AppCompatActivity {

    //Variáveis de controle
    TabLayout tabLayout;
    MaterialToolbar materialToolbar;


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
        tabLayout = findViewById(R.id.tabLayoutPerfil);
        materialToolbar = findViewById(R.id.matTooBarActivtPerfil);

        //Controle maste
        ControllerMaster contMaster = ControllerMaster.getControllerMaster();

        //Configurações iniciais
        carregarFragment(contMaster.getLoginOn() ? new FragmentPerfilUsuario(): new FragmentPerfilDesLogado() );


        //Configuração TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment escolhido = null;

                switch (tab.getPosition()){
                    case 0:
                        //Mudando a pagina caso o usuario tenha ou não perfil
                        if ( contMaster.getLoginOn() ) {
                            escolhido = new FragmentPerfilUsuario();
                        }else {
                            escolhido = new FragmentPerfilDesLogado();
                        }
                        break;
                    case 1:
                        //Mudando a pagina caso o usuario tenha ou não loja
                        //verificando se o perfil esta lçogado antees
                        if ( contMaster.getLoginOn() ){
                            if ( contMaster.getInformacoesPerfil().isTemLoja() ) {
                                escolhido = new FragmentPerfilLoja();
                            } else {
                                escolhido = new FragmentPerfilSemLoja();
                            }
                        } else {
                            escolhido = new FragmentPerfilSemLoja();
                        }
                        break;
                }

                if(escolhido != null){
                    carregarFragment(escolhido);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Botões ------------------------------------------------------------------------
        //Botão voltar arow back
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });
        //-------------------------------------------------------------------------------




    }

    private void carregarFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.layContainerFragmentPerfil,
                fragment).commit();
    }

}