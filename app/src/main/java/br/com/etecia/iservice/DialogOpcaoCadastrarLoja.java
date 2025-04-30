package br.com.etecia.iservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class DialogOpcaoCadastrarLoja extends DialogFragment {

    DialogListerCadastro escutar;


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

        return dialog;
    }

    // Interface -----------------------------------------------------------------------------
    //Interface para receber informaçoes
    public interface DialogListerCadastro{
        void dialogPassarDados();
    }
}
