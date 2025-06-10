package br.com.etecia.iservice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorLoja extends RecyclerView.Adapter <AdaptadorLoja.ViewHolder>{
    Context contexto;
    List <ObjCardServicoPp> listaServicos;

    //Interface para passar o serviõ escolhido ao detalhes
    InComunicarServPp inComunicar;

    public AdaptadorLoja(Context contexto, List<ObjCardServicoPp> listaServicos, InComunicarServPp inComunicar) {
        this.contexto = contexto;
        this.listaServicos = listaServicos;
        this.inComunicar = inComunicar;
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

        //Ajustando a formatação do valor
        String valorFormatado = "R$: " + listaServicos.get(position).getTxtValorServicoPp();

        holder.valorServ.setText(valorFormatado);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pegado posição do serviço
                int numServ = holder.getAdapterPosition();

                //verificar se a posição é válida
                if (numServ == RecyclerView.NO_POSITION) return;


                //ObjetoServiço que vai receber as informações do selecionado
                ObjCardServicoPp objServ = new ObjCardServicoPp(
                        listaServicos.get(numServ).getImgServicoPp(),
                        listaServicos.get(numServ).getTxtNomeServicoPp(),
                        listaServicos.get(numServ).getTxtDetalhesServicoPp(),
                        listaServicos.get(numServ).getTxtValorServicoPp());
                // Envia o objeto atravez da interface
                inComunicar.enviarServico(objServ);
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
