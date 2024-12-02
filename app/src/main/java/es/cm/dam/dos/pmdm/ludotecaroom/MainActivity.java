package es.cm.dam.dos.pmdm.ludotecaroom;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private List<Disco> listaDeDiscos;
    private ListView listaDiscos;
    private Button boton;
    private EditText edAlbum;
    private EditText edArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        iniciarUI();

        DiscoDao discoDao = DatabaseClient.getInstance(this).discoDao();
        iniciarComportamiento(discoDao);

        //Reiniciando la bbdd.
        discoDao.eliminarTodosLosDisco();

        //Insertar manualmente
        /*discoDao.insertarDisco(new Disco("Thriller", "Michael Jackson", 1982));
        discoDao.insertarDisco(new Disco("Mayeútica", "Robe", 2021));
        discoDao.insertarDisco(new Disco("Wembley", "Queen", 191));
        discoDao.insertarDisco(new Disco("Opus 33", "Mozart", 1735));*/

        listaDeDiscos = discoDao.obtenerTodosLosDiscos();
        listar();
    }

    private void iniciarComportamiento(DiscoDao discoDao) {
        boton.setOnClickListener(view -> {
            //recoger los valores de los edittext
            discoDao.insertarDisco(new Disco(edAlbum.getText().toString(), edArtista.getText().toString(), Year.now().getValue()));
            listaDeDiscos = discoDao.obtenerTodosLosDiscos();
            listar();
        });
    }

    private void iniciarUI() {
        listaDiscos = findViewById(R.id.lstResultados);
        boton = findViewById(R.id.btAnadir);
        edAlbum = findViewById(R.id.edAlbum);
        edArtista = findViewById(R.id.edArtista);
    }

    private void listar() {
        ArrayAdapter<String> adaptador;
        List<String> lista = new ArrayList<String>();
        if(listaDeDiscos == null || listaDeDiscos.isEmpty()){
            lista.add("No hay registros");
        }else{
            for (Disco miniDisco : listaDeDiscos) {
                lista.add(miniDisco.nombre + " " + miniDisco.artista + " " + miniDisco.anio);
                adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
                listaDiscos.setAdapter(adaptador);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DatabaseClient.getInstance(this).close();
    }
}