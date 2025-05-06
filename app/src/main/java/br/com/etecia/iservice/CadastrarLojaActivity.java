package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CadastrarLojaActivity extends AppCompatActivity {

    //Variáveis de controle
    CheckBox cbxAreaEndereco;
    LinearLayout lnlAreaEndereco;
    Button btnFinalizarCadLoja;

    //Variáveis de Informação



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cadastrar_loja_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Apresentação Java + XML
        cbxAreaEndereco = findViewById(R.id.cbxCadastroLojaEndereco);
        lnlAreaEndereco = findViewById(R.id.lnlCadastroLojaAreaEndereco);
        btnFinalizarCadLoja = findViewById(R.id.btnFinalizarCadLoja);

        //Configurações iniciais
        lnlAreaEndereco.setClickable(false);


        //CheckBox, ativar e desativar área de endereço
        cbxAreaEndereco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cbxAreaEndereco.isChecked()){
                    lnlAreaEndereco.setClickable(true);
                }else{
                    lnlAreaEndereco.setClickable(false);
                }
            }
        });

        //Botões ----------------------------------------------------------------------
        //Botão de finalizar cadastro
        btnFinalizarCadLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

    }
}