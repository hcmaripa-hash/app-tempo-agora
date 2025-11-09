package com.example.meuapp.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.meuapp.R;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MapFragment extends Fragment {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        webView = view.findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        Context ctx = requireContext();
        SharedPreferences sp = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        String city = sp.getString("city_query", "Curitiba,PR");
        String encoded = URLEncoder.encode(city + " street view", StandardCharsets.UTF_8);

        String url = "https://www.google.com/maps/search/" + encoded;
        webView.loadUrl(url);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null) webView.onResume();
    }

    @Override
    public void onDestroyView() {
        if (webView != null) {
            webView.destroy();
        }
        super.onDestroyView();
    }
}
