package br.com.etecia.iservice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import java.net.URI;

public class DialogDetalhesServico extends DialogFragment {

    //Variáveis construtor
    ObjCardServicoPp objCardServicoPp;

    //Variáveis de informação
    TextView txtNome, txtValor, txtDescricao;
    ImageView imgServ;

    //Variáveis de controle
    Button btnEntrarEmContato;

    //Construtor
    public DialogDetalhesServico(ObjCardServicoPp objCardServicoPp){
        this.objCardServicoPp = objCardServicoPp;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Criando o dialog----------------------------------------------------------------
        AlertDialog.Builder bilder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_detalhes_servico_layout, null);
        AlertDialog dialog = bilder.setView(view).create();
        //----------------------------------------------------------------------------------

        //Apresentação Java + XML
        txtDescricao = view.findViewById(R.id.txtDialogDescricao);
        txtNome = view.findViewById(R.id.txtDialogNomeServico);
        txtValor = view.findViewById(R.id.txtDialogPreco);
        imgServ = view.findViewById(R.id.imgDialogServico);
        btnEntrarEmContato = view.findViewById(R.id.btnDialogEntrarEmContato);


        //Setando informações no dialog
        imgServ.setImageResource(objCardServicoPp.getImgServicoPp());
        txtNome.setText(objCardServicoPp.getTxtNomeServicoPp());
        txtDescricao.setText(objCardServicoPp.getTxtDetalhesServicoPp());
        //Formatando o valor
        String valorFormatado = "R$: " + objCardServicoPp.getTxtValorServicoPp();
        txtValor.setText(valorFormatado);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        btnEntrarEmContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setData(Uri.parse("mailto:"));
                startActivity(intent);
            }
        });





        return dialog;
    }






}
