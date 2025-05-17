package br.com.etecia.iservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class CadastrarLojaActivity extends AppCompatActivity {

    //Variáveis de controle
    CheckBox cbxAreaEndereco;
    LinearLayout lnlAreaEndereco;
    Button btnFinalizarCadLoja;
    List<TextInputLayout> listElementos;
    boolean lojaFisica;

    //Variáveis de Informação
    TextInputEditText txtCep, txtEstado, txtCidade, txtLogradouro;
    TextInputEditText txtRua, txtNumero, txtComplemento;
    TextInputEditText txtNome, txtCpfCnpj, txtDescricao;


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

        //Apresentação Java + XML----------------------------------------------
        cbxAreaEndereco = findViewById(R.id.cbxCadastroLojaEndereco);
        lnlAreaEndereco = findViewById(R.id.lnlCadastroLojaAreaEndereco);
        btnFinalizarCadLoja = findViewById(R.id.btnFinalizarCadLoja);

        //Loja
        txtNome = findViewById(R.id.txtCadLojaNome);
        txtCpfCnpj = findViewById(R.id.txtCadLojaCnpj);
        txtDescricao = findViewById(R.id.txtCadLojaDescricao);

        //Endereço
        txtCep = findViewById(R.id.txtCadLojaCep);
        txtEstado = findViewById(R.id.txtCadLojaEstado);
        txtCidade = findViewById(R.id.txtCadLojaCidade);
        txtLogradouro = findViewById(R.id.txtCadLojaLogradouro);
        txtRua = findViewById(R.id.txtCadLojaRua);
        txtNumero = findViewById(R.id.txtCadLojaNumero);
        txtComplemento = findViewById(R.id.txtCadLojaComplemento);


        //Variáveis TextInputLayout
        TextInputLayout lnlCep = findViewById(R.id.txtLnlCep);
        TextInputLayout lnlEstado = findViewById(R.id.txtLnlEstado);
        TextInputLayout lnlCidade = findViewById(R.id.txtLnlCidade);
        TextInputLayout lnlLogradouro = findViewById(R.id.txtLnlLogradouro);
        TextInputLayout lnlRua = findViewById(R.id.txtLnlRua);
        TextInputLayout lnlNumero = findViewById(R.id.txtLnlNumero);
        TextInputLayout lnlComplemento = findViewById(R.id.txtLnlComplemento);

        //------------------------------------------------------------------------

        //Instacias
        listElementos = new ArrayList<>();

        //Configurações iniciais
        listElementos.add(lnlCep);
        listElementos.add(lnlEstado);
        listElementos.add(lnlCidade);
        listElementos.add(lnlLogradouro);
        listElementos.add(lnlRua);
        listElementos.add(lnlNumero);
        listElementos.add(lnlComplemento);
        ativacaoElementos(listElementos, true);
        lojaFisica = false;



        //CheckBox, ativar e desativar área de endereço
        cbxAreaEndereco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cbxAreaEndereco.isChecked()){
                    lnlAreaEndereco.setEnabled(false);
                    ativacaoElementos(listElementos, false);
                    lojaFisica = false;
                }else{
                    lnlAreaEndereco.setEnabled(true);
                    ativacaoElementos(listElementos, true);
                    lojaFisica = true;
                }
            }
        });

        //Botões ----------------------------------------------------------------------
        //Botão de finalizar cadastro
        btnFinalizarCadLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Strings com as informações Loja
                String nome = txtNome.getText().toString().trim();
                String cpfCnpj = txtCpfCnpj.getText().toString().trim();
                String descricao = txtDescricao.getText().toString().trim();

                //Strings com as informações endereço
                String cep = txtCep.getText().toString().trim();
                String estado = txtEstado.getText().toString().trim();
                String cidade = txtCidade.getText().toString().trim();
                String logra = txtLogradouro.getText().toString().trim();
                String rua = txtRua.getText().toString().trim();
                String numero = txtNumero.getText().toString().trim();
                String comp = txtComplemento.getText().toString().trim();

                //Objetos
                ObjCardLoja loja;
                ObjEndereco end;
                ObjCardServicoPp serv;

                //Lista serviço
                List<ObjCardServicoPp> listServicos = new ArrayList<>();

                //Imagem e nota
                int imgFoto = R.drawable.foto_imagem;
                double nota = 5;

                if (lojaFisica) {
                    if ( checkCampo(cep, txtCep) && checkCampo(estado, txtCep) && checkCampo(cidade, txtCep) &&
                    checkCampo(logra, txtCep) && checkCampo(rua, txtCep) && checkCampo(numero, txtCep) && checkCampo(comp, txtCep) ) {
                        end = new ObjEndereco(
                                Integer.parseInt(cep),
                                cidade,
                                comp,
                                estado,
                                logra,
                                Integer.parseInt(numero),
                                rua
                        );

                        loja = new ObjCardLoja(
                                imgFoto,
                                listServicos,
                                nome,
                                nota,
                                end
                        );
                        ControllerMaster.getControllerMaster().addLojaPerfil(loja);
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                }else {
                    if ( checkCampo(nome, txtNome) && checkCampo(cpfCnpj, txtCpfCnpj)) {
                        loja = new ObjCardLoja(
                                imgFoto,
                                listServicos,
                                nome,
                                nota
                        );
                        ControllerMaster.getControllerMaster().addLojaPerfil(loja);
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }

                }
            }
        });

    }

    //Função para desativar ou ativar os elemento de endereço
    private void ativacaoElementos(List<TextInputLayout> listElemento, boolean enable){
        for (int i = 0; i < listElemento.size(); i++) {
            listElemento.get(i).setVisibility( enable? View.GONE:View.VISIBLE );
        }
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
}