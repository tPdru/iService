package br.com.etecia.iservice;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorPerfilLojaServicos extends RecyclerView.Adapter<AdaptadorPerfilLojaServicos.ViewHolder> {

    Context context;
    List<ObjCardServicoPp> listaServicos;


    public AdaptadorPerfilLojaServicos(Context context, List<ObjCardServicoPp> listaServicos) {
        this.context = context;
        this.listaServicos = listaServicos;
    }

    @NonNull
    @Override
    public AdaptadorPerfilLojaServicos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPerfilLojaServicos.ViewHolder holder, int position) {

        //Colocando as informaçoes nos itens
        holder.imgfoto.setImageResource(listaServicos.get(position).getImgServicoPp());
        holder.txtNome.setText(listaServicos.get(position).getTxtNomeServicoPp());
        holder.txtValor.setText( String.valueOf(listaServicos.get(position).getTxtValorServicoPp()) );

        //Captando o item que foi clicado
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar a posição atual do item escolhido
                int pos = holder.getAdapterPosition();

                //Verifica se a posição do item clicado e valida
                if ( pos == RecyclerView.NO_POSITION )  return;

                Toast.makeText(context, "Teste", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtNome, txtValor;
        ImageView imgfoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Apresentação Java + XML, variaveis do modelo.
            txtNome = itemView.findViewById(R.id.txtModeloCardServicoPpNome);
            txtValor = itemView.findViewById(R.id.txtModeloCardServicoPpValor);
            imgfoto = itemView.findViewById(R.id.imgModeloCardServicoPp);

        }
    }
}
