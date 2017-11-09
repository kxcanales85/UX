package com.example.bustamante.unifit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditRoutineActivity extends AppCompatActivity {

    ListView lista_ejercicios;
    ArrayList<String> ejercicios;
    MyAdapter adaptador;
    Button agregar_ejercicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_routine);

        Intent intent = getIntent();
        String rutina = intent.getStringExtra("rutina");

        setTitle(rutina);

        setListView();

        listenerButton();
    }

    public boolean listenerButton(){
        agregar_ejercicio = (Button)findViewById(R.id.btnAddExercises);
        agregar_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditRoutineActivity.this,SelectAreaActivity.class);
                startActivity(intent);
            }
        });
        return true;
    }

    public boolean setListView(){

        ejercicios = new ArrayList<>();
        lista_ejercicios = (ListView)findViewById(R.id.listViewExercises);
        ejercicios.add("Pull-ups");
        ejercicios.add("Chin-ups");
        ejercicios.add("Remo");
        ejercicios.add("Flexiones de brazo");
        ejercicios.add("Encogimientos");
        ejercicios.add("Pullover");

        adaptador = new MyAdapter(this,R.layout.list_edit_routine,ejercicios);

        lista_ejercicios.setAdapter(adaptador);

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
                fila = mInflater.inflate(R.layout.list_edit_routine, null);
            }

            TextView ejercicio = (TextView) fila.findViewById(R.id.exerciseEdit);
            TextView descripcion = (TextView) fila.findViewById(R.id.exerciseEditDesc);
            String rutina = getItem(position);
            ejercicio.setText(rutina);
            descripcion.setText(R.string.descripcion_rutina);

            return fila;
        }
    }
}
