package com.example.bustamante.unifit;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import java.nio.channels.Channels;

public class SelectAreaActivity extends AppCompatActivity {

    TabHost tab;
    TabHost.TabSpec spec;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        setTitle("Agregar ejercicio");

        setTabs();
        setImage();
        imageClickListener();
    }

    public boolean imageClickListener(){
        image = (ImageView)findViewById(R.id.bodyImage);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectAreaActivity.this,AddRoutinesActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }

    public boolean setTabs(){
        tab = (TabHost)findViewById(R.id.tabhost);
        tab.setup();

        spec = tab.newTabSpec("Por músculo");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Por músculo");
        tab.addTab(spec);

        spec = tab.newTabSpec("Por nombre");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Por nombre");
        tab.addTab(spec);

        return true;
    }

    public boolean setImage(){
        image = (ImageView)findViewById(R.id.bodyImage);
        image.setImageResource(R.mipmap.body);
        return true;
    }
}
