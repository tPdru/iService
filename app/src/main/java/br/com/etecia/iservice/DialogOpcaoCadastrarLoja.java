package br.com.etecia.iservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class DialogOpcaoCadastrarLoja extends DialogFragment {

    //Variáveis de controle
    DialogListerCadastro escutar;
    boolean cadastrarLoja;

    //Variáveis de informação
    Button btnSair, btnCadastrar;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Fragment parentFragment = getParentFragment();

        //Conversão de Fragment para DialogListerCadastro.
        escutar = (DialogListerCadastro) parentFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Criando o dialog----------------------------------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_opcao_cadastrar_loja_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //--------------------------------------------------------------------------------

        //Apresentação Java + XML
        btnCadastrar = view.findViewById(R.id.btnDialogSimCadastrar);
        btnSair = view.findViewById(R.id.btnDialogNaoCadastrar);

        //Instancias

        //Botões---------------------------------------------------------------------------
        //Botão cadastrar loja
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CadastrarLojaActivity.class));
                requireActivity().finish();
                dialog.dismiss();
            }
        });

        //Botão Não cadastrar a loja agora
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HomeActivity.class));
                requireActivity().finish();
                dialog.dismiss();
            }

        });



        return dialog;
    }

    // Interface -----------------------------------------------------------------------------
    //Interface para receber informaçoes
    public interface DialogListerCadastro{
        void dialogPassarDados();
    }
}
