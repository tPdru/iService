package br.com.etecia.iservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorTeste extends RecyclerView.Adapter<AdaptadorTeste.ViewHolder> {

    List<ObjTeste> objTesteList;
    Context context;

    public AdaptadorTeste(Context context, List<ObjTeste> objTesteList) {
        this.context = context;
        this.objTesteList = objTesteList;
    }

    @NonNull
    @Override
    public AdaptadorTeste.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.testar_banco_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTeste.ViewHolder holder, int position) {

        holder.txtNomeTxt.setText(objTesteList.get(position).getNome());

    }

    @Override
    public int getItemCount() {
        return objTesteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomeTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeTxt = itemView.findViewById(R.id.txtNomeTeste);
        }
    }
}
