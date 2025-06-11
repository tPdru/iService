package br.com.etecia.iservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorServicoPp extends RecyclerView.Adapter<AdaptadorServicoPp.ViewHolder> {

    //Variáveis
    Context context;
    List<ObjCardServicoPp> listaServicoPp;
    ControllerMaster contMaster;


    public AdaptadorServicoPp(Context context, List<ObjCardServicoPp> listaServicoPp) {
        this.context = context;
        this.listaServicoPp =listaServicoPp;
        contMaster = ControllerMaster.getControllerMaster();
    }

    @NonNull
    @Override
    public AdaptadorServicoPp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater layInflater = LayoutInflater.from(context);
        view = layInflater.inflate(R.layout.modelo_servico_pequeno_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorServicoPp.ViewHolder holder, int position) {

        if (listaServicoPp.get(position).getImgServicoPp() != null) {

            Bitmap bitmap = BitmapFactory.decodeByteArray(listaServicoPp.get(position).getImgServicoPp(), 0, listaServicoPp.get(position).getImgServicoPp().length);

            Bitmap bit = contMaster.getResizedBitmap(bitmap,90);

            holder.img_servico_pp.setImageBitmap(bit);

        }

        holder.txt_nome_servico_pp.setText(listaServicoPp.get(position).getTxtNomeServicoPp());
        holder.txt_valor_servico_pp.setText(String.valueOf(listaServicoPp.get(position).getTxtValorServicoPp()));

    }

    @Override
    public int getItemCount() {
        return listaServicoPp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_servico_pp;
        TextView txt_nome_servico_pp, txt_valor_servico_pp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_servico_pp = itemView.findViewById(R.id.imgModeloCardServicoPp);
            txt_nome_servico_pp = itemView.findViewById(R.id.txtModeloCardServicoPpNome);
            txt_valor_servico_pp = itemView.findViewById(R.id.txtModeloCardServicoPpValor);

        }
    }
}
