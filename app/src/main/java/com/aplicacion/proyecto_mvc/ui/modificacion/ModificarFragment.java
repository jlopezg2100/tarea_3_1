package com.aplicacion.proyecto_mvc.ui.modificacion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aplicacion.proyecto_mvc.BD.BDHelper;
import com.aplicacion.proyecto_mvc.BD.Transaccion;
import com.aplicacion.proyecto_mvc.R;

public class ModificarFragment extends Fragment {

    Button btnactualizar,btnbuscar, btneliminar;
    EditText id, txt_nombre, txt_apellido, txt_edad, txt_direccion;
    Spinner spi_puesto;
    BDHelper conexion;
    String Puesto;
    private static final String DEFAULT_POST = "Seleccione";
    String[] ssppuestos = {"Seleccione","Gerente de departamento","Subgerente de departamento","Atencion al cliente","Cajero","Conserje","Auxiliar de bodega","Agente de limpieza","Soporte Tecnico"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar,container, false);

        spi_puesto = (Spinner)view.findViewById(R.id.spi_puesto);
        btnactualizar = (Button)view.findViewById(R.id.btnactualizar);
        btnbuscar = (Button)view.findViewById(R.id.btnbuscar);
        btneliminar = (Button)view.findViewById(R.id.btneliminar);
        id = (EditText)view.findViewById(R.id.txtid);
        txt_nombre= (EditText)view.findViewById(R.id.txt_nombre);
        txt_apellido = (EditText)view.findViewById(R.id.txt_apellido);
        txt_edad = (EditText)view.findViewById(R.id.txt_edad);
        txt_direccion = (EditText)view.findViewById(R.id.txt_direccion);

        spi_puesto.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, ssppuestos));

        spi_puesto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Puesto = (String) spi_puesto.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        conexion= new BDHelper(getActivity(), Transaccion.NameDataBase, null, 1);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();


            }
        });

        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();

            }
        });

        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();


            }
        });

        return view;
    }


    private void Buscar() {

        SQLiteDatabase db = conexion.getWritableDatabase();


        String[] params = {id.getText().toString()};
        String[] fields= {Transaccion.nombre,
                Transaccion.apellido,
                Transaccion.edad,
                Transaccion.direccion,
                Transaccion.puesto};

        String wherecond = Transaccion.id + "=?";

        try {
            Cursor cdata = db.query(Transaccion.tabla_empleados, fields, wherecond, params, null, null, null);
            cdata.moveToFirst();
            txt_nombre.setText(cdata.getString(0));
            txt_apellido.setText(cdata.getString(1));
            txt_edad.setText(cdata.getString(2));
            txt_direccion.setText(cdata.getString(3));
            //sppuesto.setText(cdata.getString(4));

            Toast.makeText(getActivity(), "Consultado con exito", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){

            ClearScreen();
            Toast.makeText(getActivity(),"Elemento no encontrado",Toast.LENGTH_LONG).show();
        }
    }



    private void eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String []  params = {id.getText().toString()};
        String wherecond = Transaccion.id + "=?";
        db.delete(Transaccion.tabla_empleados, wherecond, params);
        Toast.makeText(getActivity(),"Empleado eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();

    }

    private void actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String []  params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transaccion.nombre, txt_nombre.getText().toString());
        valores.put(Transaccion.apellido, txt_apellido.getText().toString());
        valores.put(Transaccion.edad, txt_edad.getText().toString());
        valores.put(Transaccion.direccion, txt_direccion.getText().toString());
        valores.put(Transaccion.puesto, Puesto.toString());

        db.update(Transaccion.tabla_empleados, valores, Transaccion.id +"=?", params);
        Toast.makeText(getActivity(),"Datos actualizados", Toast.LENGTH_LONG).show();
        ClearScreen();

    }


    private void ClearScreen()
    {
        id.setText("");
        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_edad.setText("");
        txt_direccion.setText("");


    }
}