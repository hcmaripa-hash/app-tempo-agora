package com.example.meuapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.widget.Toast;
import android.content.SharedPreferences;

public class QRScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Aponte para o QR da cidade (ex: Curitiba,PR)");
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                String conteudo = result.getContents().trim();
                SharedPreferences sp = getSharedPreferences("prefs", MODE_PRIVATE);
                sp.edit().putString("city_query", conteudo).apply();
                Toast.makeText(this, "Cidade definida: " + conteudo, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
