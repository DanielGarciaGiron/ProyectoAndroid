package com.example.polygons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    private Button BeginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        BeginButton=(Button) findViewById(R.id.BeginButton);
        BeginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSelectorMenu();
            }
        });
    }

    public void openSelectorMenu(){
        Intent intent = new Intent(this, Selector.class);
        startActivity(intent);
    }
}
