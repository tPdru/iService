package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.drawable.BitmapDrawable;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CadastrarActivity extends AppCompatActivity implements DialogOpcaoCadastrarLoja.DialogListerCadastro {

    //VAriáveis de controle
    Button btnCriarConta, btnAdicionarFoto;

    MaterialToolbar materialButton;

    ImageView imgCadUsuario;

    //Variáveis de informação
    TextInputEditText txtNome, txtemail, txtSenha, txtReSenha, txtUsuario, txtCelular;

    ActivityResultLauncher <Intent> imagePickerLauncher;

    byte[]imageBytes;

    int codigoImagem;
    //Bancos
    DAOLocalPerfil daoLocalPerfil;

    private byte[] imageViewToByte (ImageView imageView){
        // Pega o drawable (imagem) do ImageView e o converte para Bitmap
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

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
        setContentView(R.layout.cadastrar_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Apresentação Java + XML----------------------------------------------------------
        btnCriarConta = findViewById(R.id.btnCriarConta);
        txtemail = findViewById(R.id.txtEmailUsuario);
        txtNome = findViewById(R.id.txtNomeCompleto);
        txtSenha = findViewById(R.id.txtSenhaUsuario);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtReSenha = findViewById(R.id.txtConfirmarSenhaUsuario);
        txtCelular = findViewById(R.id.txtCelular);
        imgCadUsuario = findViewById(R.id.imgCadUsuario);
        btnAdicionarFoto = findViewById(R.id.btnAdicionarFoto);
        materialButton = findViewById(R.id.appBarCadastrar);

        // bancos
        DAOUsuario daoUsuario = new DAOUsuario();//Banco online (desativado)
        daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());

        codigoImagem=getIntent().getIntExtra("getCodigo", 0);

        // Inicializa o launcher para selecionar imagem
        imagePickerLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        imgCadUsuario.setImageURI(selectedImageUri);

                        // Verifica se há uma imagem no ImageView antes de converter

                        if (imgCadUsuario.getDrawable()!=null){
                            imageBytes = imageViewToByte(imgCadUsuario);
                            Toast.makeText(this, "Imagen selecionada!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "Erro: imagem inválida!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(this, "Erro ao selecionar a imagem", Toast.LENGTH_SHORT).show();
                    }
                });



        //Botões----------------------------------------------------------------------------

        //Btn Voltar
        materialButton.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        // Botão para escolher imagem
        btnAdicionarFoto.setOnClickListener(v->{
            // Cria uma intent para abrir o seletor de imagens do dispositivo
            Intent intent = new Intent(Intent.ACTION_PICK);

            // Define que o tipo de conteúdo a ser selecionado é imagem
            intent.setType("image/*");

            // Lança a intent usando o launcher previamente registrado (imagePickerLauncher)
            imagePickerLauncher.launch(intent);

        });




        //Botão criar conta chama o dialog para o usuário decidir se quer ir para a criação
        //da loja.
        btnCriarConta.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Pegar os textos
                String email = txtemail.getText().toString().trim();
                String senha = txtSenha.getText().toString().trim();
                String nome = txtNome.getText().toString().trim();
                String usuario = txtUsuario.getText().toString().trim();
                String confirmarSenha = txtReSenha.getText().toString().trim();

                imageBytes=imageViewToByte(imgCadUsuario);


                //Testar os textos
                if (checkCampo(usuario, txtUsuario) && checkCampo(nome, txtNome) &&
                    checkCampo(email, txtemail) && checkCampo(senha, txtSenha) &&
                    checkCampo(confirmarSenha, txtReSenha)){

                    //Testando se a senha e confirmação são iguais
                    if ( senha.equals(confirmarSenha) ) {
                        ObjPerfil perfil = new ObjPerfil(
                                ControllerMaster.getControllerMaster().getCodigoList() + 1,
                                email,
                                nome,
                                senha,
                                usuario,
                                imageBytes,
                                false
                        );

                        //Adicionando o celular ao objeto
                        if (!TextUtils.isEmpty(txtCelular.getText().toString().trim())){
                            perfil.setCelular(txtCelular.getText().toString().trim());
                        }

                        //cria a conta
                        ControllerMaster.getControllerMaster().criarPerfil(perfil, email);
                        //efetua o login apos a criação
                        ControllerMaster.getControllerMaster().autenticarConta(email, senha);

                        /**Adiciona ao banco Local*/
                        //adicionando o codigo do usuario no controlemaster too
                        ControllerMaster cm = ControllerMaster.getControllerMaster();
                        cm.getInformacoesPerfil().setCodigo(daoLocalPerfil.inserirPerfil(perfil));


                        /** Adiciona oa banco online
                        DAOUsuario daoUsu = new DAOUsuario();
                        daoUsu.creatPerfil(perfil, getApplicationContext(),imageBytes);
                        */

                        //chama o dialog fragment para decidir sobre a criação da loja
                        DialogOpcaoCadastrarLoja dialog = new DialogOpcaoCadastrarLoja();
                        dialog.show(getSupportFragmentManager(),"Cadastrar loja?");
                    }
                    else{
                        msgConfirmacaoSenha(txtReSenha);
                    }
                }
            }
        });

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
    // Metodo teste senha e confirmação
    private void msgConfirmacaoSenha(TextInputEditText inputEditText) {
        inputEditText.setError("A senha e a confirmação são diferentes!");
        inputEditText.requestFocus();
    }

    @Override
    public void dialogPassarDados() {
    }
}