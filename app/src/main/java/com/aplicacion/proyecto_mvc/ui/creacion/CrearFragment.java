package com.aplicacion.proyecto_mvc.ui.creacion;

import android.content.ContentValues;
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
import com.basgeekball.awesomevalidation.AwesomeValidation;

public class CrearFragment extends Fragment {

    Button btncrear;
    EditText txt_nombre, txt_apellido, txt_edad, txt_direccion;
    Spinner spi_puesto;
    ArrayAdapter adp;
    AwesomeValidation awesomenValitation;
    String Puesto;
    private static final String DEFAULT_POST = "Seleccione el puesto";
    String[] sspuestos = {"Seleccione","Gerente de departamento","Subgerente de departamento","Atencion al cliente","Cajero","Conserje","Auxiliar de bodega","Agente de limpieza","Soporte Tecnico"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crear,container, false);

        btncrear = (Button)view.findViewById(R.id.btncrear);
        txt_nombre = (EditText)view.findViewById(R.id.txt_nombre);
        txt_apellido = (EditText)view.findViewById(R.id.txt_apellido);
        txt_edad = (EditText)view.findViewById(R.id.txt_edad);
        txt_direccion = (EditText)view.findViewById(R.id.txt_direccion);
        spi_puesto = (Spinner)view.findViewById(R.id.spi_puesto);

        spi_puesto.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, sspuestos));

        spi_puesto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Puesto = (String) spi_puesto.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aggempleados();
            }
        });

        return view;
    }


    private void aggempleados() {
        BDHelper conexion = new BDHelper(getActivity(), Transaccion.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transaccion.nombre, txt_nombre.getText().toString());
        valores.put(Transaccion.apellido, txt_apellido.getText().toString());
        valores.put(Transaccion.edad, txt_edad.getText().toString());
        valores.put(Transaccion.direccion, txt_direccion.getText().toString());
        valores.put(Transaccion.puesto, Puesto);

        Long resultado = db.insert(Transaccion.tabla_empleados, Transaccion.id, valores);

        //Toast
        Toast.makeText(getActivity().getApplicationContext(), "EMPLEADO INGRESADO: " + resultado.toString(), Toast.LENGTH_LONG).show();


        db.close();

    }



}