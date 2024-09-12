package com.umg.clarorecargasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder> {

    private List<Tienda> tiendaList;

    public TiendaAdapter(List<Tienda> tiendaList) {
        this.tiendaList = tiendaList;
    }

    @NonNull
    @Override
    public TiendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tienda, parent, false);
        return new TiendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiendaViewHolder holder, int position) {
        Tienda tienda = tiendaList.get(position);
        holder.codigoTextView.setText(String.valueOf(tienda.getId())); // Mostrar el ID
        holder.nombreTextView.setText(tienda.getNombre());
        holder.pinTextView.setText(String.valueOf(tienda.getPin()));
        holder.estadoTextView.setText(tienda.getEstado());
    }

    @Override
    public int getItemCount() {
        return tiendaList.size();
    }

    public static class TiendaViewHolder extends RecyclerView.ViewHolder {
        TextView codigoTextView;
        TextView nombreTextView;
        TextView pinTextView;
        TextView estadoTextView;

        public TiendaViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.codigoTextView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            pinTextView = itemView.findViewById(R.id.pinTextView);
            estadoTextView = itemView.findViewById(R.id.estadoTextView);
        }
    }
}