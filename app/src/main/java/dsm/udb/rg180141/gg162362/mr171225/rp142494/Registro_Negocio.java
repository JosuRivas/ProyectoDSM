package dsm.udb.rg180141.gg162362.mr171225.rp142494;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dsm.udb.rg180141.gg162362.mr171225.rp142494.modelos.Negocio;

public class Registro_Negocio extends AppCompatActivity {

    private Spinner spinnerDepartamentos,spinnerTipoNegocio;
    private EditText nombreNegocioEDT,municipioEDT,direccionEDT,horarioEDT,telefonoEDT,rangoPreciosEDT;
    private Button btnConfirmar;
    private String idNegocio;
    private DatabaseReference miDatabaseNegocios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_negocio);

        Bundle datos = getIntent().getExtras();
        idNegocio = datos.getString("idNegocio");

        miDatabaseNegocios = FirebaseDatabase.getInstance().getReference("negocios");

        spinnerDepartamentos = (Spinner) findViewById(R.id.departamentoSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.array_departamentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartamentos.setAdapter(adapter);

        spinnerTipoNegocio = (Spinner) findViewById(R.id.tipoNegocioSpinner);
        ArrayAdapter<CharSequence> adapterNegocios = ArrayAdapter.createFromResource(this,R.array.lista_negocios, android.R.layout.simple_spinner_item);
        adapterNegocios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoNegocio.setAdapter(adapterNegocios);

        inicializarVistas();

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int camposFaltantes = validarCampos();

                if (camposFaltantes == 0){

                    Negocio negocio = new Negocio(nombreNegocioEDT.getText().toString(),spinnerDepartamentos.getSelectedItem().toString(),
                            municipioEDT.getText().toString(),telefonoEDT.getText().toString(),direccionEDT.getText().toString(),
                            rangoPreciosEDT.getText().toString(),horarioEDT.getText().toString(),spinnerTipoNegocio.getSelectedItem().toString());

                    miDatabaseNegocios.child(idNegocio).setValue(negocio).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Negocio creado correctamente!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Registro_Negocio.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    private void inicializarVistas() {
        nombreNegocioEDT = findViewById(R.id.nombreNegocioEDT);
        municipioEDT = findViewById(R.id.municipioEDT);
        direccionEDT = findViewById(R.id.direccionEDT);
        horarioEDT = findViewById(R.id.horarioEDT);
        telefonoEDT = findViewById(R.id.telefonoEDT);
        rangoPreciosEDT = findViewById(R.id.rangoPreciosEDT);
        btnConfirmar = findViewById(R.id.btnConfirmarInfoNegocio);
    }

    private int validarCampos() {
        int contador=0;
        contador = nombreNegocioEDT.getText().toString().trim().isEmpty() ? contador+1: contador;
        contador = municipioEDT.getText().toString().trim().isEmpty() ? contador+1: contador;
        contador = telefonoEDT.getText().toString().trim().isEmpty() ? contador+1: contador;

        if (contador !=0){
            Toast.makeText(getApplicationContext(),"Faltan " + contador + " campos obligatorios por completar",Toast.LENGTH_LONG).show();
        }

        return contador;
    }
}