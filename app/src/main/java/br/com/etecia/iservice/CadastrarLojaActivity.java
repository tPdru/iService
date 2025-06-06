package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class CadastrarLojaActivity extends AppCompatActivity {

    //Variáveis de controle
    CheckBox cbxAreaEndereco;
    LinearLayout lnlAreaEndereco;
    ImageView imgCadastrarLoja;
    Button btnFinalizarCadLoja, btnAdicionarFotoLoja;
    List<TextInputLayout> listElementos;
    boolean lojaFisica;
    AdaptadorModeloCardLoja adaptadorModeloCardLoja;

    //Variáveis de Informação
    TextInputEditText txtCep, txtEstado, txtCidade, txtLogradouro;
    TextInputEditText txtRua, txtNumero, txtComplemento;
    TextInputEditText txtNome, txtCpfCnpj, txtDescricao;

    //Banco
    DAOLocalLoja daoLocalLoja;

    //converter imageView pra byte
    private byte[] imageViewToByte (ImageView imgCad){
        // Pega o drawable (imagem) do ImageView e o converte para Bitmap
        Bitmap bitmap = ((BitmapDrawable) imgCad.getDrawable()).getBitmap();

        // Cria um fluxo de bytes na memória para armazenar a imagem comprimida
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Comprime o bitmap em formato PNG com qualidade 100% e escreve no stream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        // Retorna o array de bytes com os dados da imagem comprimida
        return stream.toByteArray();
    }


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
        daoLocalLoja = new DAOLocalLoja(getApplicationContext());

        //Configurações iniciais
        listElementos.add(lnlCep);
        listElementos.add(lnlEstado);
        listElementos.add(lnlCidade);
        listElementos.add(lnlLogradouro);
        listElementos.add(lnlRua);
        listElementos.add(lnlNumero);
        listElementos.add(lnlComplemento);
        ativacaoElementos(listElementos, true);
        //*lojaFisica = false;





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
                String email = ControllerMaster.getControllerMaster().getInformacoesPerfil().getEmail();

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
                                email,
                                imgFoto,
                                listServicos,
                                nome,
                                nota,
                                end
                        );
                        //Adicionando descrição a loja, end = true
                        loja.setDescricao(descricao);
                        ControllerMaster.getControllerMaster().getInformacoesPerfil().setTemLoja(true);
                        ControllerMaster.getControllerMaster().addLojaPerfil(loja);
                        ControllerMaster.getControllerMaster().getInformacoesPerfil().getMinhaLoja().setTemEndereco(true);

                        /** Salvando no Banco local */
                        //adicionando a chave estrangeira a loja too
                        ControllerMaster cm = ControllerMaster.getControllerMaster();
                        loja.setCodUsuario(cm.getInformacoesPerfil().getCodigo());
                        // criando a loja e adicionando ocodigo da loja oa controlemaster
                        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

                        //ControllerMaster.getControllerMaster().carregarLojas();

                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                }else {
                    if ( checkCampo(nome, txtNome) && checkCampo(cpfCnpj, txtCpfCnpj)) {
                        loja = new ObjCardLoja(
                                email,
                                imgFoto,
                                listServicos,
                                nome,
                                nota
                        );

                        //Adicionando descrição a loja
                        loja.setDescricao(descricao);
                        loja.setTemEndereco(false);
                        ControllerMaster.getControllerMaster().addLojaPerfil(loja);
                        ControllerMaster.getControllerMaster().getInformacoesPerfil().setTemLoja(true);

                        /** Salvando no Banco local */
                        //adicionando a chave estrangeira a loja too
                        ControllerMaster cm = ControllerMaster.getControllerMaster();
                        loja.setCodUsuario(cm.getInformacoesPerfil().getCodigo());
                        // criando a loja e adicionando ocodigo da loja oa controlemaster
                        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

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