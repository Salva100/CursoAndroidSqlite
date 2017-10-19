package devworms.mibasededatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    Button btnGuardar, btnActualizar, btnBuscar;
    EditText edtDato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista= (ListView)findViewById(R.id.listDato);
        btnActualizar=(Button)findViewById(R.id.btnActualizar);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        edtDato = (EditText) findViewById(R.id.edtDato);

        AdminSQLite dbHandler;
        dbHandler = new AdminSQLite(this, null, null, 1);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        Cursor resultados = dbHandler.listarTodos();
        List<BdModel> modelo = new ArrayList<BdModel>();

        int cuantos = resultados.getCount();
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        for (int c = 0; c < cuantos ; c++){




            adaptador.add(resultados.getString(1));

            resultados.moveToNext();

        }





        lista.setAdapter(adaptador);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AdminSQLite dbHandler;
                dbHandler = new AdminSQLite(getBaseContext(), null, null, 1);
                SQLiteDatabase db = dbHandler.getWritableDatabase();
                dbHandler.addDato(edtDato.getText().toString());

                Toast.makeText(MainActivity.this, "Se guardo en Mi Dato",
                        Toast.LENGTH_SHORT).show();

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite dbHandler;
                dbHandler = new AdminSQLite(getBaseContext(), null, null, 1);
                SQLiteDatabase db = dbHandler.getWritableDatabase();

                Cursor resultados = dbHandler.listarTodos();
                List<BdModel> modelo = new ArrayList<BdModel>();
                BdModel elemento;
                String tempHorario;
                int cuantos = resultados.getCount();
                ArrayAdapter<String> adaptador;
                adaptador = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1);
                adaptador.clear();
                for (int c = 0; c < cuantos ; c++){

                    elemento = new BdModel();
                    elemento.setDato(resultados.getString(1));


                    adaptador.add(resultados.getString(1));
                    resultados.moveToNext();

                }
                lista.setAdapter(adaptador);
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLite dbHandler;
                dbHandler = new AdminSQLite(getBaseContext(), null, null, 1);
                SQLiteDatabase db = dbHandler.getWritableDatabase();

                Cursor resultados = dbHandler.BuscarporDato(edtDato.getText().toString());
                List<BdModel> modelo = new ArrayList<BdModel>();
                BdModel elemento;

                int cuantos = resultados.getCount();
                ArrayAdapter<String> adaptador;
                adaptador = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1);

                for (int c = 0; c <= cuantos ; c++){




                    adaptador.add(resultados.getString(1));
                    resultados.moveToNext();

                }
                lista.setAdapter(adaptador);
            }
        });

    }
}
