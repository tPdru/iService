package br.com.etecia.iservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogCriarServico extends DialogFragment {

    // Variáveis de informação
    EditText txtNome, txtValor, txtDescriacao;
    private long idLoja;

    // Variaveis de controle
    Button btnCancelar, btnCriar;

    //Interface
    InCriarServ inCriarServ;

    //Construtor
    public DialogCriarServico(long idLoja, InCriarServ inCriarServ) {
        this.idLoja = idLoja;
        this.inCriarServ = inCriarServ;
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

        // apresentação Java + XML
        btnCriar = view.findViewById(R.id.btnCriarServicoCriar);
        btnCancelar = view.findViewById(R.id.btnCriarServicoCancelar);
        txtNome = view.findViewById(R.id.txtCriarServicoNome);
        txtValor = view.findViewById(R.id.txtCriarServicoValor);
        txtDescriacao = view.findViewById(R.id.txtCriarServicoDescricao);


        /** ------------------------------- Botões ------------------------------- **/
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
                    serv.setTxtValorServicoPp(Long.parseLong(String.valueOf(txtValor.getText())));
                    serv.setTxtDetalhesServicoPp(String.valueOf(txtDescriacao.getText()));

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
