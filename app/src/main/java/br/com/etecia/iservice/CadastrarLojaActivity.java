package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    ImageView imgCadLoja;
    Button btnFinalizarCadLoja, btnAdicionarFotoLoja;
    List<TextInputLayout> listElementos;
    boolean lojaFisica;
    byte[] imageBytes;
    AdaptadorModeloCardLoja adaptadorModeloCardLoja;
    ActivityResultLauncher<Intent> imagePickerLauncher;

    //Variáveis de Informação
    TextInputEditText txtCep, txtEstado, txtCidade, txtLogradouro;
    TextInputEditText txtRua, txtNumero, txtComplemento;
    TextInputEditText txtNome, txtCpfCnpj, txtDescricao;

    //Banco
    DAOLocalLoja daoLocalLoja;
    DAOLocalEndereco daoLocalEndereco;

    //converter imageView pra byte
    private byte[] imageViewToByte(ImageView imgCad) {
        Bitmap bitmap = ((BitmapDrawable) imgCad.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Usando JPEG (mais leve que PNG) com qualidade de 70%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);

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
        btnAdicionarFotoLoja = findViewById(R.id.btnAdicionarFotoLoja);

        //Loja
        txtNome = findViewById(R.id.txtCadLojaNome);
        txtCpfCnpj = findViewById(R.id.txtCadLojaCnpj);
        txtDescricao = findViewById(R.id.txtCadLojaDescricao);
        imgCadLoja = findViewById(R.id.imgCadLoja);


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
        daoLocalEndereco = new DAOLocalEndereco(getApplicationContext());

        //Configurações iniciais
        listElementos.add(lnlCep);
        listElementos.add(lnlEstado);
        listElementos.add(lnlCidade);
        listElementos.add(lnlLogradouro);
        listElementos.add(lnlRua);
        listElementos.add(lnlNumero);
        listElementos.add(lnlComplemento);
        ativacaoElementos(listElementos, true);
        lojaFisica = true;
        //*lojaFisica = false;


        // Inicializa o launcher para selecionar imagem da galeria
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

                result -> {
                    // Checa se a seleção foi bem-sucedida e os dados não são nulos
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        // Pega a URI da imagem selecionada
                        Uri selectedImageUri = result.getData().getData();

                        // Define essa URI no ImageView para mostrar a imagem escolhida
                        imgCadLoja.setImageURI(selectedImageUri);

                        // Verifica se o ImageView tem uma imagem carregada
                        if (imgCadLoja.getDrawable() != null) {
                            // Converte a imagem exibida no ImageView para byte[]
                            imageBytes = imageViewToByte(imgCadLoja);

                            Toast.makeText(this, "Imagem selecionada!", Toast.LENGTH_SHORT).show();

                        } else {
                            // Caso não tenha imagem (falha), seta uma imagem padrão
                            imgCadLoja.setImageResource(R.drawable.foto_imagem);

                            // Converte imagem padrão para byte[]
                            imageBytes = imageViewToByte(imgCadLoja);

                            Toast.makeText(this, "Imagem padrão selecionada", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        // Se o usuário cancelou ou não selecionou, seta imagem padrão
                        imgCadLoja.setImageResource(R.drawable.foto_imagem);

                        // Converte imagem padrão para byte[]
                        imageBytes = imageViewToByte(imgCadLoja);

                        Toast.makeText(this, "Imagem padrão selecionada", Toast.LENGTH_SHORT).show();
                    }
                }
        );


        // Listener para o CheckBox que ativa/desativa a área de endereço
        cbxAreaEndereco.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Quando marcado, desativa a área de endereço e seus elementos
                    lnlAreaEndereco.setEnabled(false);          // Desativa o layout que contém o endereço
                    ativacaoElementos(listElementos, false);    // Desativa todos os elementos do endereço
                    lojaFisica = false;                          // Marca que não é loja física
                } else {
                    // Quando desmarcado, ativa a área de endereço e seus elementos
                    lnlAreaEndereco.setEnabled(true);           // Ativa o layout que contém o endereço
                    ativacaoElementos(listElementos, true);     // Ativa todos os elementos do endereço
                    lojaFisica = true;                           // Marca que é loja física
                }
            }
        });

        //Botões ----------------------------------------------------------------------

        // Botão para escolher imagem
        btnAdicionarFotoLoja.setOnClickListener(v -> {
            // Cria uma intent para abrir o seletor de imagens do dispositivo
            Intent intent = new Intent(Intent.ACTION_PICK);

            // Define que o tipo de conteúdo a ser selecionado é imagem
            intent.setType("image/*");

            // Lança a intent usando o launcher previamente registrado (imagePickerLauncher)
            imagePickerLauncher.launch(intent);
        });

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
                //Define a imagem padrão


                byte[] imgFoto = imageBytes ;
                double nota = 5;

                if (lojaFisica) {
                    if (checkCampo(cep, txtCep) && checkCampo(estado, txtCep) && checkCampo(cidade, txtCep) &&
                            checkCampo(logra, txtCep) && checkCampo(rua, txtCep) && checkCampo(numero, txtCep) && checkCampo(comp, txtCep)) {
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
                        /** Salvando o endereço no banco local **/
                        end.setCodigoLoja(loja.getCodigLoja());
                        end.setCodigoLoja(daoLocalEndereco.inserirEndereco(end));

                        //ControllerMaster.getControllerMaster().carregarLojas();

                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                    }
                } else {
                    if (checkCampo(nome, txtNome) && checkCampo(cpfCnpj, txtCpfCnpj)) {
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

    // Função para ativar ou desativar os elementos da lista de endereço
    private void ativacaoElementos(List<TextInputLayout> listElemento, boolean enable) {
        // Percorre todos os elementos da lista
        for (int i = 0; i < listElemento.size(); i++) {
            // Ativa ou desativa o elemento conforme o parâmetro 'enable'
            // Use setEnabled para ativar/desativar entrada do usuário
            listElemento.get(i).setEnabled(enable);

            // Se quiser esconder o campo quando desativar, pode usar setVisibility, mas aqui ativamos/desativamos
            // Exemplo para esconder em vez de desativar:
            // listElemento.get(i).setVisibility(enable ? View.VISIBLE : View.GONE);
        }
    }

    // Método para verificar se o campo está preenchido (não vazio)
// Retorna true se preenchido, false se vazio e já mostra erro no campo
    private boolean checkCampo(String texto, TextInputEditText inputEditText) {
        if (TextUtils.isEmpty(texto)) {
            // Caso campo vazio, mostra mensagem de erro no campo e foca nele
            inputEditText.setError("Preencha todos os campos!");
            inputEditText.requestFocus();
            return false;
        } else {
            return true;
        }
    }





}