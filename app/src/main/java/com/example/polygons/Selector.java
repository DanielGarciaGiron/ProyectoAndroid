package com.example.polygons;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Selector extends AppCompatActivity {
    ListView myListView;
    public static final String DestinationID = "com.example.polygons.DestinationID";
    private String itemLocation;
    private Boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        //ListView y su contenido
        String[] AvailableDestinations = {"Edificio A", "Edificio B","Edificio C", "Edificio H","Edificio J", "Cancha Polideportiva","Plaza Paiz Riera", "Auditorio"};
        myListView = (ListView) findViewById(R.id.DirectionsList);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item, android.R.id.text1,AvailableDestinations)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(20);
                return view;
            }
        };

        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag = displayGpsStatus();
                if (flag)
                {
                    itemLocation = adapter.getItem(position);
                    loadMaps();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Por favor, encienda el GPS de su celular." ,Toast.LENGTH_SHORT).show();
                }

            }
        });}

    //Se encarga de cargar el mapa y de enviar el lugar a donde se quiere llegar.
    public void loadMaps(){
        Intent intent = new Intent(this, PolyActivity.class);
        intent.putExtra(DestinationID, itemLocation);
        startActivity(intent);
    }

    //Checkea si el GPS en el celular esta encendido
    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }
}

