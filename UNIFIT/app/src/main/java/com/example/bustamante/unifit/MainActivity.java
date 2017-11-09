package com.example.bustamante.unifit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private PieChartView pieChart;
    private PieChartData data;
    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasLabelForSelected = false;
    private double carbo = 25.9, proteinas = 20.1, grasas = 30.8, total = 0.0;

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(MainActivity.this, value.getValue() + " %", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
        }
    }

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

    private void toggleLabels() {
        hasLabels = !hasLabels;
        if (hasLabels) {
            hasLabelForSelected = false;
            pieChart.setValueSelectionEnabled(hasLabelForSelected);
            if (hasLabelsOutside) {
                pieChart.setCircleFillRatio(0.7f);
            } else {
                pieChart.setCircleFillRatio(1.0f);
            }
        }
        generateData();
    }

    /*Reinicia los valores de las variables*/
    public void resetCounts() {
        carbo = 0;
        proteinas = 0;
        grasas = 0;
    }

    private void generateData() {
        int numValues = 3;	// Numero de particiones y/o variables
        List<SliceValue> values = new ArrayList<SliceValue>();
	/*Definimos el tamano (mediante un valor porcentual referente a cierta variable) y el color que tendra*/
        if (carbo > 0) {
            SliceValue sliceValueBuenas = new SliceValue((float) ((float) carbo * 100 / total), getResources().getColor(R.color.colorCarbo));
            values.add(sliceValueBuenas);
        }
        if (proteinas > 0) {
            SliceValue sliceValueMalas = new SliceValue((float) ((float) proteinas * 100 / total), getResources().getColor(R.color.colorProteina));
            values.add(sliceValueMalas);
        }
        if (grasas > 0) {
            SliceValue sliceValueNulas = new SliceValue((float) ((float) grasas * 100 / total), getResources().getColor(R.color.colorGrasa));
            values.add(sliceValueNulas);
        }
        data = new PieChartData(values);
        data.setHasLabels(hasLabels); // Muesta el valor de la particion
        pieChart.setPieChartData(data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button boton;
        boton = (Button)findViewById(R.id.btn1);

        pieChart = (PieChartView) findViewById(R.id.chart);

        carbo = 25.9;
        proteinas = 20.1;
        grasas = 30.8;
        total=carbo+proteinas+grasas;

        pieChart.setOnValueTouchListener(new ValueTouchListener());
        toggleLabels();
        generateData();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MyRoutinesActivity.class);
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
