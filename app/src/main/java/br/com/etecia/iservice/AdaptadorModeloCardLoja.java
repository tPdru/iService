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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorModeloCardLoja extends RecyclerView.Adapter<AdaptadorModeloCardLoja.ViewHolder> {

    Context context;
    List<ObjCardLoja> listaCardLoja;
    DAOLocalService daoLocalService;
    ControllerMaster contMaster;

    public AdaptadorModeloCardLoja(Context context, List<ObjCardLoja> listaCardLoja) {
        this.context = context;
        this.listaCardLoja = listaCardLoja;
        daoLocalService = new DAOLocalService(context);
        contMaster = ControllerMaster.getControllerMaster();
    }

    @NonNull
    @Override
    public AdaptadorModeloCardLoja.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layInflater = LayoutInflater.from(context);
        View view = layInflater.inflate(R.layout.modelo_card_loja_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorModeloCardLoja.ViewHolder holder, int position) {

        ObjCardLoja loja = listaCardLoja.get(position);

        // Redimensionar o bitmap antes de setar
        if (loja.getImgLoja() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(listaCardLoja.get(position).getImgLoja(),
                    0, listaCardLoja.get(position).getImgLoja().length);

            //Bitmap bit = contMaster.getResizedBitmap(bitmap, 200);

            holder.img_loja.setImageBitmap(bitmap);
        }
        holder.nome_loja.setText(loja.getNomeLoja());
        holder.nota_loja.setText(loja.getDescricao());

        // Configurar RecyclerView interno (serviços da loja)
        List<ObjCardServicoPp> lista_servicos = new ArrayList<>();
        for (ObjCardServicoPp servico : daoLocalService.readService()) {
            if (servico.getCodigoLoja() == loja.getCodigLoja()) {
                lista_servicos.add(servico);
            }
        }

        AdaptadorServicoPp adpServicoPp = new AdaptadorServicoPp(context, lista_servicos);
        holder.rec_servicos.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.rec_servicos.setAdapter(adpServicoPp);
        holder.rec_servicos.setNestedScrollingEnabled(false);

        // Clique no card da loja
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos == RecyclerView.NO_POSITION) return;

            Intent intent = new Intent(context, LojaActivity.class);
            intent.putExtra("emailEscolhido", String.valueOf(listaCardLoja.get(pos).getCodigLoja()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaCardLoja != null ? listaCardLoja.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_loja;
        TextView nota_loja, nome_loja;
        RecyclerView rec_servicos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_loja = itemView.findViewById(R.id.imgModeloCardLoja);
            nota_loja = itemView.findViewById(R.id.txtModeloCardLojaDesc);
            nome_loja = itemView.findViewById(R.id.txtModeloCardLojaNomLoja);
            rec_servicos = itemView.findViewById(R.id.recModeloCardsLojas);
        }
    }

    public void atualizarListaAgendados(List<ObjCardLoja> lista_atualizada) {
        if (lista_atualizada != null) {
            listaCardLoja.clear();
            listaCardLoja.addAll(lista_atualizada);
        } else {
            listaCardLoja.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * Função utilitária para redimensionar imagens da memória de forma segura.
     */
    private Bitmap decodeSampledBitmapFromByteArray(byte[] data, int reqWidth, int reqHeight) {
        // Primeira decodificação apenas para obter as dimensões
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // Calcular o fator de escala
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Agora decodificar a imagem com o fator de escala
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calcula o maior inSampleSize possível que ainda seja maior que os tamanhos requisitados
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
