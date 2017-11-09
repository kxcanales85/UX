package com.example.bustamante.unifit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddRoutinesActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Button musculo;
    TabHost tab;
    TabHost.TabSpec spec;
    ListView lista_pormusculo;
    ArrayList<String> rutinas;
    MyAdapter adaptador;
    Intent intent;
    String nombre_rutina;

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
        setContentView(R.layout.activity_add_routines);
        setTitle("Agregar ejercicio");

        setSelectedMuscle();

        setTabs();

        setListView();
    }

    public boolean setSelectedMuscle(){
        musculo = (Button) findViewById(R.id.selectedMuscle);
        musculo.setText("Músculo seleccionado: Biceps");

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

    public boolean setListView(){
        rutinas = new ArrayList<>();
        //ArrayAdapter<String> adaptador;
        lista_pormusculo = (ListView)findViewById(R.id.listViewMusculo);
        //adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        rutinas.add("Flexiones de brazo");
        rutinas.add("Fondos");
        rutinas.add("Jalones");
        rutinas.add("Lagartijas");
        rutinas.add("Press en banca");
        adaptador = new MyAdapter(this,R.layout.custom_row,rutinas);
        lista_pormusculo.setAdapter(adaptador);
        lista_pormusculo.setClickable(true);
        lista_pormusculo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 0:
                        intent = new Intent(AddRoutinesActivity.this,AddRoutineActivity.class);
                        nombre_rutina = adapterView.getItemAtPosition(i).toString();
                        intent.putExtra("nombre",nombre_rutina);
                        startActivity(intent);
                }

            }
        });

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation,menu);

        return true;
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public View getView(int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.custom_row, null);
            }

            TextView rutina_view = (TextView) fila.findViewById(R.id.routine);

            String rutina = getItem(position);
            rutina_view.setText(rutina);

            return fila;
        }
    }
}
