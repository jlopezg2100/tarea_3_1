package com.aplicacion.proyecto_mvc.ui.lista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.aplicacion.proyecto_mvc.BD.BDHelper;
import com.aplicacion.proyecto_mvc.BD.Transaccion;
import com.aplicacion.proyecto_mvc.R;
import com.aplicacion.proyecto_mvc.models.Empleados;

import java.util.ArrayList;


public class ListaFragment extends Fragment {



    BDHelper conexion;
    ListView lista;

    private String ID;
    private Boolean SelectedRow = false;

    ArrayList<Empleados> empleados;
    ArrayList<String> ArrayEmpleados;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        lista = (ListView) view.findViewById(R.id.listView);



        conexion= new BDHelper(getActivity(), Transaccion.NameDataBase, null, 1);

        GetEmpleado();

        ArrayAdapter adp = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_single_choice, ArrayEmpleados);
        lista.setAdapter(adp);

        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

                ID = empleados.get(position).getId().toString();
                SelectedRow = true;
            }
        });

        return view;
    }

    private void GetEmpleado() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados e = null;
        empleados = new ArrayList<Empleados>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transaccion.tabla_empleados, null);

        while (cursor.moveToNext()){
            e = new Empleados();
            e.setId(cursor.getInt(0));
            e.setNombre(cursor.getString(1));
            e.setApellido(cursor.getString(2));
            e.setEdad(cursor.getInt(3));
            e.setDireccion(cursor.getString(4));
            e.setPuesto(cursor.getString(5));

            empleados.add(e);
        }

        cursor.close();

        ArrayEmpleados = new ArrayList<String>();

        for (int i = 0;  i < empleados.size(); i++){

            ArrayEmpleados.add(
                    empleados.get(i).getId() + " - " +
                    empleados.get(i).getNombre() + " " +
                    empleados.get(i).getApellido() + " - Edad " +
                    empleados.get(i).getEdad() + " - Direccion " +
                    empleados.get(i).getDireccion() + " - Puesto " +
                    empleados.get(i).getPuesto());
        }
    }
}