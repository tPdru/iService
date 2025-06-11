package br.com.etecia.iservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.ByteArrayOutputStream;

public class DialogCriarServico extends DialogFragment {

    // Variáveis de informação
    EditText txtNome, txtValor, txtDescriacao;
    private long idLoja;
    ImageView imgCriarServico;
    ActivityResultLauncher<Intent> imagePickerLauncher;
    byte [] imageBytes;

    // Variaveis de controle
    Button btnCancelar, btnCriar, btnAdcFotoServ;

    //Interface
    InCriarServ inCriarServ;

    //Construtor
    public DialogCriarServico(long idLoja, InCriarServ inCriarServ) {
        this.idLoja = idLoja;
        this.inCriarServ = inCriarServ;
    }

    private byte[] imageViewToByte(ImageView imgCad) {
        Bitmap bitmap = ((BitmapDrawable) imgCad.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Usando JPEG (mais leve que PNG) com qualidade de 70%
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);

        return stream.toByteArray();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Criando o dialog----------------------------------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_criar_servico_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //----------------------------------------------------------------------------------

        imagePickerLauncher = registerForActivityResult(
                // Define o tipo de contrato para iniciar uma Activity esperando um resultado
                new ActivityResultContracts.StartActivityForResult(),

                // Define o que fazer quando a Activity retornar um resultado
                result -> {
                    // Verifica se o resultado foi OK e se os dados retornados não são nulos
                    if (result.getResultCode() == -1 && result.getData() != null){

                        // Obtém a URI da imagem selecionada
                        Uri selectedImageUri = result.getData().getData();

                        // Define essa URI como a imagem exibida no ImageView
                        imgCriarServico.setImageURI(selectedImageUri);

                        // Verifica se há uma imagem no ImageView antes de converter
                        if (imgCriarServico.getDrawable() != null) {

                            // Caso a seleção falhe ou seja cancelada, salva a imagem padrão
                            imageBytes = imageViewToByte(imgCriarServico);
                        } else {
                            //Define a imagem padrão
                            imgCriarServico.setImageResource(R.drawable.foto_imagem);

                            //converter a imagem para byte[]
                            imageBytes=imageViewToByte(imgCriarServico);
                        }

                    } else {
                        //Define a imagem padrão
                        imgCriarServico.setImageResource(R.drawable.foto_imagem);

                        //converter a imagem para byte[]
                        imageBytes=imageViewToByte(imgCriarServico);
                    }
                });

        // apresentação Java + XML
        btnCriar = view.findViewById(R.id.btnCriarServicoCriar);
        btnCancelar = view.findViewById(R.id.btnCriarServicoCancelar);
        txtNome = view.findViewById(R.id.txtCriarServicoNome);
        txtValor = view.findViewById(R.id.txtCriarServicoValor);
        txtDescriacao = view.findViewById(R.id.txtCriarServicoDescricao);
        btnAdcFotoServ = view.findViewById(R.id.btnAdcFotoServ);
        imgCriarServico = view.findViewById(R.id.imgCriarServico);


        /** ------------------------------- Botões ------------------------------- **/

        // Botão para escolher imagem
        btnAdcFotoServ.setOnClickListener(v -> {
            // Cria uma intent para abrir o seletor de imagens do dispositivo
            Intent intent = new Intent(Intent.ACTION_PICK);

            // Define que o tipo de conteúdo a ser selecionado é imagem
            intent.setType("image/*");

            // Lança a intent usando o launcher previamente registrado (imagePickerLauncher)
            imagePickerLauncher.launch(intent);
        });

        //Botão Criar
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtNome.getText())) {
                    txtNome.setError("Preencha todos os campos!");
                    txtNome.requestFocus();
                }
                else{

                    ObjCardServicoPp serv = new ObjCardServicoPp();

                    serv.setCodigoLoja(idLoja);
                    serv.setTxtNomeServicoPp(String.valueOf(txtNome.getText()));
                    serv.setTxtValorServicoPp(String.valueOf(txtValor.getText()));
                    serv.setTxtDetalhesServicoPp(String.valueOf(txtDescriacao.getText()));
                    serv.setImgServicoPp(imageBytes);


                    inCriarServ.salvar(serv);
                    dialog.dismiss();
                }
            }
        });

        //Botão Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(requireContext(), "Cancelar", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        return dialog;
    }
}
