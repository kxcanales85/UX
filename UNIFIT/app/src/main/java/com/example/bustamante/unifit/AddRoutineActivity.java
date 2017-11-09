package com.example.bustamante.unifit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AddRoutineActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static final int MENU_ITEM_ITEM1 = 0;
    Button rutina;
    ImageView image;
    Spinner series;
    Spinner repeticiones;
    Spinner peso;
    MenuItem item;
    Intent intent;
    String nombre_rutina;
    Integer[] n_series;
    Integer[] n_repeticiones;
    Integer[] pesos;

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
        setContentView(R.layout.activity_add_routine);
        setTitle("Agregar ejercicio");

        setRoutineName();

        setRoutineImage();

        setSpinners();
    }

    public boolean setRoutineName(){
        intent = getIntent();
        nombre_rutina = intent.getStringExtra("nombre");
        rutina = (Button)findViewById(R.id.routineName);
        rutina.setText(nombre_rutina);
        return true;
    }

    public boolean setRoutineImage(){
        image = (ImageView)findViewById(R.id.routineImage);
        image.setImageResource(R.mipmap.flexiones);
        return true;
    }

    public boolean setSpinners(){
        series = (Spinner)findViewById(R.id.seriesSpinner);
        n_series = new Integer[]{5,10,15,20};
        series.setAdapter(new ArrayAdapter<Integer>(this,R.layout.spinner_item,n_series));

        repeticiones = (Spinner)findViewById(R.id.repeticionesSpinner);
        n_repeticiones = new Integer[]{10,15,20,25,30};
        repeticiones.setAdapter(new ArrayAdapter<Integer>(this,R.layout.spinner_item,n_repeticiones));

        peso = (Spinner)findViewById(R.id.pesoSpinner);
        pesos = new Integer[]{20,25,30,35,40};
        peso.setAdapter(new ArrayAdapter<Integer>(this,R.layout.spinner_item,pesos));

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation,menu);
        item = menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Agregar");
        item.setIcon(android.R.drawable.ic_input_add);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_ITEM1:
                return true;

            default:
                return false;
        }
    }

}
