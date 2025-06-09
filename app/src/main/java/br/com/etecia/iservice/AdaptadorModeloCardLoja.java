package br.com.etecia.iservice;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AdaptadorModeloCardLoja extends RecyclerView.Adapter<AdaptadorModeloCardLoja.ViewHolder> {


    Context context;
    List<ObjCardLoja> listaCardLoja;
    DAOLocalService daoLocalService;


    public AdaptadorModeloCardLoja(Context context, List<ObjCardLoja> listaCardLoja) {
        this.context = context;
        this.listaCardLoja = listaCardLoja;
        daoLocalService = new DAOLocalService(context);
    }


    @NonNull
    @Override
    public AdaptadorModeloCardLoja.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layInflater = LayoutInflater.from(context);
        view = layInflater.inflate(R.layout.modelo_card_loja_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorModeloCardLoja.ViewHolder holder, int position) {

        holder.nome_loja.setText(listaCardLoja.get(position).getNomeLoja());
        holder.img_loja.setImageResource(listaCardLoja.get(position).getImgLoja());
        holder.nota_loja.setText(String.valueOf(listaCardLoja.get(position).getTxtNota()));



        //Configurar o recycleView Interno-------------------------------------------
        ObjCardLoja loja = listaCardLoja.get(position);


        //Verifica se a loja tem serviços
        if ( loja.isTemServicos() ) {

            List<ObjCardServicoPp> lista_servicos = new ArrayList<>();

            //Lista com todos os serviços do banco
            List<ObjCardServicoPp> lista = new ArrayList<>(daoLocalService.readService());

            // Coletando osserviços referentes a loja selecionada
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCodigoLoja() == listaCardLoja.get(position).getCodigLoja()) {
                    lista_servicos.add(lista.get(i));
                }
            }

            //Liga o adaptador com os serviços
            AdaptadorServicoPp adpServicoPp = new AdaptadorServicoPp(context, lista_servicos);
            holder.rec_servicos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.rec_servicos.setAdapter(adpServicoPp);
        }


        //Configurando o click das lojas
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pegar a posição atual do item escolhido
                int pos = holder.getAdapterPosition();

                //Verifica se a posição do item clicado e valida
                if ( pos == RecyclerView.NO_POSITION )  return;

                //pegando o contexto do item clicado
                Intent intent = new Intent(context, LojaActivity.class);

                //salvando as informaçoes do serviço escolhido
                intent.putExtra("emailEscolhido", String.valueOf(listaCardLoja.get(pos).getCodigLoja()));

                //iniciando a nova activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // operador ternario verificando se alista esta null antes de passar
        return listaCardLoja != null? listaCardLoja.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Declaração das variaveis que vão setar informações
        ImageView img_loja;
        TextView nota_loja, nome_loja;
        //tem que declarar p recycleView interno aqui
        RecyclerView rec_servicos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_loja = itemView.findViewById(R.id.imgModeloCardLoja);
            nota_loja = itemView.findViewById(R.id.txtModeloCardLojaNota);
            nome_loja = itemView.findViewById(R.id.txtModeloCardLojaNomLoja);
            rec_servicos = itemView.findViewById(R.id.recModeloCardsLojas);
        }
    }

    //Atualizar lista
    public void atualizarListaAgendados(List<ObjCardLoja> lista_atualizada){

        if (lista_atualizada != null){
            listaCardLoja.clear();
            listaCardLoja.addAll(lista_atualizada);
        } else {
            listaCardLoja.clear();
        }
        notifyDataSetChanged();

    }
}
