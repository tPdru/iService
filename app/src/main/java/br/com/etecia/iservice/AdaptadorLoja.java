package br.com.etecia.iservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
