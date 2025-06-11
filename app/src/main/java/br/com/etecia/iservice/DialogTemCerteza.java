package br.com.etecia.iservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogTemCerteza extends DialogFragment {

    IntEnviaEmail intEnviaEmail;

    Button btnConfirma, btnRecusa;

    //Construtor
    public DialogTemCerteza(IntEnviaEmail intEnviaEmail) {

        this.intEnviaEmail = intEnviaEmail;

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //Criando o dialog----------------------------------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_tem_certeza_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //----------------------------------------------------------------------------------

        //Apresentação java + XML
        btnConfirma = view.findViewById(R.id.btnTemCertConfirmar);
        btnRecusa = view.findViewById(R.id.btnTemCertCancelar);


        btnConfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intEnviaEmail.enviaEmail(true);
                dismiss();
            }
        });

        btnRecusa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intEnviaEmail.enviaEmail(false);
                dismiss();
            }
        });




        return dialog;
    }
}
