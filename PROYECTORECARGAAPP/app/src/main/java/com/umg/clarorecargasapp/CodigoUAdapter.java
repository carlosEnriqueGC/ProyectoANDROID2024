package com.umg.clarorecargasapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CodigoUAdapter extends RecyclerView.Adapter<CodigoUAdapter.CodigoUViewHolder> {

    private List<CodigoU> codigoUList;

    public CodigoUAdapter(List<CodigoU> codigoUList) {this.codigoUList = codigoUList;}

    @NonNull
    @Override
    public CodigoUViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_codigo, parent, false); // Cambia este layout si es necesario
        return new CodigoUViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CodigoUViewHolder holder, int position) {
        CodigoU codigoU = codigoUList.get(position);
        holder.tipoTextView.setText(codigoU.getTipo_codigo()); // Método que debes implementar
        holder.precioTextView.setText(String.valueOf(codigoU.getPrecio_codigo())); // Método que debes implementar
        holder.secuenciaTextView.setText(codigoU.getSecuencia_codigo()); // Método que debes implementar
        holder.estadoTextView.setText(codigoU.getEstado_codigo()); // Método existente
    }

    @Override
    public int getItemCount() {
        return codigoUList.size();
    }

    public static class CodigoUViewHolder extends RecyclerView.ViewHolder {
        TextView tipoTextView; // Cambiado
        TextView precioTextView; // Cambiado
        TextView secuenciaTextView; // Cambiado
        TextView estadoTextView;

        public CodigoUViewHolder(@NonNull View itemView) {
            super(itemView);
            tipoTextView = itemView.findViewById(R.id.tipoCUTextView); // Cambiado
            precioTextView = itemView.findViewById(R.id.precioCUTextView); // Cambiado
            secuenciaTextView = itemView.findViewById(R.id.secuenciaCUTextView); // Cambiado
            estadoTextView = itemView.findViewById(R.id.estadoCUTextView); // Cambiado
        }
    }
}