package br.com.etecia.iservice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
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
        View view;
        LayoutInflater layInflater = LayoutInflater.from(context);
        view = layInflater.inflate(R.layout.modelo_servico_pequeno_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPerfilLojaServicos.ViewHolder holder, int position) {

        //Colocando as informaçoes nos itens

        if (listaServicos.get(position).getImgServicoPp() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(listaServicos.get(position).getImgServicoPp(), 0, listaServicos.get(position).getImgServicoPp().length);
            holder.imgfoto.setImageBitmap(bitmap);
        } else {
            holder.imgfoto.setImageResource(R.drawable.foto_imagem);
        }
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
