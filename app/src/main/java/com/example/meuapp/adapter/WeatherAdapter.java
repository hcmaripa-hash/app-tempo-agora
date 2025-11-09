package com.example.meuapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meuapp.R;
import com.example.meuapp.model.WeatherData;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.VH> {

    private final List<WeatherData> data;

    public WeatherAdapter(List<WeatherData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        WeatherData d = data.get(position);
        holder.txtDate.setText(d.getTitle());
        holder.txtDesc.setText(d.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtDate, txtDesc;
        VH(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }
    }
}
