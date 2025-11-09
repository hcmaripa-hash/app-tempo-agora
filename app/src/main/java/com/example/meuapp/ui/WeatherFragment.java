package com.example.meuapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.meuapp.R;
import com.example.meuapp.adapter.WeatherAdapter;
import com.example.meuapp.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherFragment extends Fragment {

    private RecyclerView recyclerView;
    private WeatherAdapter adapter;
    private final List<WeatherData> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_weather, container, false);
        recyclerView = root.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WeatherAdapter(list);
        recyclerView.setAdapter(adapter);
        new Thread(this::loadData).start();
        return root;
    }
    private void loadData() {
        try {
            Context ctx = requireContext();
            SharedPreferences sp = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE);

            // Começa sempre com Curitiba, se o usuário nunca escaneou nada
            String city = sp.getString("city_query", "Curitiba,PR");

            // Monta a URL principal com user_ip=remote (para evitar cair em São Paulo)
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
            String apiUrl = "https://api.hgbrasil.com/weather?format=json&city_name=Curitiba,PR&user_ip=remote";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(10000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) json.append(line);
            reader.close();

            JSONObject obj = new JSONObject(json.toString());
            JSONObject results = obj.getJSONObject("results");

            final String cityName = results.optString("city_name", "Cidade");
            JSONArray forecast = results.getJSONArray("forecast");

            list.clear();
            for (int i = 0; i < forecast.length(); i++) {
                JSONObject item = forecast.getJSONObject(i);
                String date = item.optString("date");
                String desc = item.optString("description");
                String max = item.optString("max");
                String min = item.optString("min");
                list.add(new WeatherData(cityName + " - " + date, desc + "  Máx:" + max + "°  Mín:" + min + "°"));
            }

            requireActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        
}
