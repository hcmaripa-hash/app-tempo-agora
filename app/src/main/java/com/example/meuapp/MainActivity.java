package com.example.meuapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.example.meuapp.ui.MapFragment;
import com.example.meuapp.ui.WeatherFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.viewpager);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(new WeatherFragment(), getString(R.string.tab_weather));
        adapter.addFragment(new MapFragment(), getString(R.string.tab_map));
        viewPager.setAdapter(adapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> startActivity(new Intent(this, QRScanActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, com.example.meuapp.ui.AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
