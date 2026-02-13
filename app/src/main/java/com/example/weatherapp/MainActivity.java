package com.example.weatherapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String[] cities = {"Moscow", "Saint Petersburg", "Nizhniy Novgorod", "Sochi", "Chaykovskiy", "Nizhniy Tagil"};
    private String[] periods = {"Today", "Tomorrow", "3 Days", "10 Days", "Month"};


    private String selectedCity;
    private String selectedPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Spinner citySpinner = findViewById(R.id.citySpinner);
        Spinner periodSpinner = findViewById(R.id.periodSpinner);
        Button toPage = findViewById(R.id.toPage);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, periods);

        adapter.setDropDownViewResource(R.layout.spinner_list);
        adapter2.setDropDownViewResource(R.layout.spinner_list);

        citySpinner.setAdapter(adapter);
        periodSpinner.setAdapter(adapter2);


        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                selectedCity = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, "Selected city: " + selectedCity, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        periodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id){
                selectedPeriod = parent.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this, "Selected period: " + selectedPeriod, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        String baseURL = "https://gismeteo.ru/weather-";

        toPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlBuilder url = new UrlBuilder(baseURL, selectedCity, selectedPeriod);
                String extendURL = url.getURL();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(extendURL));
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Browser is not available", Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}