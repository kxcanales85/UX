package com.example.bustamante.unifit;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyRoutinesActivity extends AppCompatActivity {

    //ArrayList<String> rutinas;
    List<String> rutinas;
    ListView lista_mis_rutinas;
    //MyAdapter adaptador;
    Intent intent;
    String nombre_rutina;
    MenuItem item;
    ImageView dropdown_routine;
    private static final int MENU_ITEM_ITEM1 = 0;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> ejercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routines);
        setTitle("Mis rutinas");


        setListView();

    }

    public boolean setListView(){
        expListView = (ExpandableListView)findViewById(R.id.listViewRoutines);

        rutinas = new ArrayList<String>();
        ejercicios = new HashMap<String, List<String>>();

        rutinas.add("Espalda y hombros");
        rutinas.add("Rutina martes");
        rutinas.add("Rutina miércoles");
        rutinas.add("Rutina jueves");
        rutinas.add("Rutina piernas y glúteos");

        List<String> espalda_hombros = new ArrayList<String>();
        espalda_hombros.add("Pull-ups");
        espalda_hombros.add("Chin-ups");
        espalda_hombros.add("Remo");
        espalda_hombros.add("Flexiones de brazo");

        List<String> rutina_martes = new ArrayList<String>();
        rutina_martes.add("Remo");

        List<String> rutina_miercoles = new ArrayList<String>();
        rutina_miercoles.add("Pull-ups");

        List<String> rutina_jueves = new ArrayList<String>();
        rutina_jueves.add("Chin-ups");

        List<String> piernas_gluteos = new ArrayList<String>();
        piernas_gluteos.add("Sentadillas");

        ejercicios.put(rutinas.get(0),espalda_hombros);
        ejercicios.put(rutinas.get(1),rutina_martes);
        ejercicios.put(rutinas.get(2),rutina_miercoles);
        ejercicios.put(rutinas.get(3),rutina_jueves);
        ejercicios.put(rutinas.get(4),piernas_gluteos);

        listAdapter = new ExpandableListAdapter(this,rutinas,ejercicios);
        expListView.setAdapter(listAdapter);


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

    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.exercise);

            txtListChild.setText(childText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            final String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.list_header, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.myRoutine);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            dropdown_routine = (ImageView)convertView.findViewById(R.id.editRoutine);
            dropdown_routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(groupPosition){
                        case 0:
                            intent = new Intent(MyRoutinesActivity.this,EditRoutineActivity.class);
                            intent.putExtra("rutina",headerTitle);
                            startActivity(intent);
                    }
                }
            });

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    /*private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
        }

        public View getView(final int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.list_header, null);
            }

            ImageView dropdown_routine = (ImageView)fila.findViewById(R.id.dropdownRoutine);


            TextView mi_rutina_view = (TextView) fila.findViewById(R.id.myRoutine);


            final String rutina = getItem(position);
            mi_rutina_view.setText(rutina);

            dropdown_routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:

                    }
                }
            });

            return fila;
        }
    }*/
}
