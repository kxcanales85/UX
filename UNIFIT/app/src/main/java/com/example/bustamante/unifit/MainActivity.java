package com.example.bustamante.unifit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_rutinas:
                    mTextMessage.setText(R.string.title_rutinas);
                    return true;
                case R.id.navigation_inicio:
                    mTextMessage.setText(R.string.title_inicio);
                    return true;
                case R.id.navigation_alimentacion:
                    mTextMessage.setText(R.string.title_alimentacion);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button boton;
        boton = (Button)findViewById(R.id.btn1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddRoutinesActivity.class);
                startActivity(i);
                /*AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Hola mundo")
                        .setTitle("Saludo");

                AlertDialog alerta = builder.create();

                alerta.show();*/
            }
        });
    }

}
