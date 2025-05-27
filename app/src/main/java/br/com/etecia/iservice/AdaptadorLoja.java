package br.com.etecia.iservice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorLoja extends RecyclerView.Adapter <AdaptadorLoja.ViewHolder>{
    Context contexto;
    List <ObjCardServicoPp> listaServicos;

    public AdaptadorLoja(Context contexto, List<ObjCardServicoPp> listaServicos) {
        this.contexto = contexto;
        this.listaServicos = listaServicos;
    }

    @NonNull
    @Override
    public AdaptadorLoja.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(contexto);
        view = layoutInflater.inflate(R.layout.modelo_servico_pequeno_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorLoja.ViewHolder holder, int position) {
        holder.imgServ.setImageResource(listaServicos.get(position).getImgServicoPp());
        holder.nomeServ.setText(listaServicos.get(position).getTxtNomeServicoPp());
        holder.valorServ.setText(listaServicos.get(position).getTxtNomeServicoPp());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pegado posição do serviço
                int numServ = holder.getAdapterPosition();

                //verificar se a posição é válida
                if (numServ == RecyclerView.NO_POSITION) return;

                //pegando infofrmações do item clicado
                Intent intent = new Intent(contexto, DetalhesServicosActivity.class);

                //salvando as informaçoes do serviço escolhido
                intent.putExtra("imgServ", listaServicos.get(numServ).getImgServicoPp());
                intent.putExtra("nomeServ", listaServicos.get(numServ).getTxtNomeServicoPp());
                intent.putExtra("valorServ", listaServicos.get(numServ).getTxtValorServicoPp());
                intent.putExtra("detalhesServ", listaServicos.get(numServ).getTxtDetalhesServicoPp());

                //iniciando a nova activity
                contexto.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgServ;
        TextView nomeServ, valorServ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgServ = itemView.findViewById(R.id.imgModeloCardServicoPp);
            nomeServ = itemView.findViewById(R.id.txtModeloCardServicoPpNome);
            valorServ = itemView.findViewById(R.id.txtModeloCardServicoPpValor);

        }
    }
}
