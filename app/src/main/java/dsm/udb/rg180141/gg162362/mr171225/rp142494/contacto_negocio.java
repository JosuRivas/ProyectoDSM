package dsm.udb.rg180141.gg162362.mr171225.rp142494;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class contacto_negocio extends AppCompatActivity {

    DatabaseReference miDatabase;
    TextView txtNombre,txtDepartamento,txtMunicipio,txtTelefono,txtRangoPrecio,txtDireccion,txtHorario;
    Button btnRegresarInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_negocio);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtDepartamento = (TextView) findViewById(R.id.txtDepartamento);
        txtMunicipio = (TextView) findViewById(R.id.txtMunicipio);
        txtTelefono = (TextView) findViewById(R.id.txtTelefono);
        txtRangoPrecio = (TextView) findViewById(R.id.txtRangoPrecio);
        txtDireccion = (TextView) findViewById(R.id.txtDireccion);
        txtHorario = (TextView) findViewById(R.id.txtHorario);
        btnRegresarInicio = (Button) findViewById(R.id.btnVolverInicio);

        Bundle datos = getIntent().getExtras();

        miDatabase = FirebaseDatabase.getInstance().getReference("negocios").child(datos.getString("id"));
        rellenarCampos();

        btnRegresarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contacto_negocio.this,activity_pantalla_principal.class);
                startActivity(intent);
            }
        });
    }

    public void rellenarCampos(){
        miDatabase.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    txtNombre.setText(task.getResult().child("nombre").getValue().toString());
                    txtDepartamento.setText(task.getResult().child("departamento").getValue().toString());
                    txtMunicipio.setText(task.getResult().child("municipio").getValue().toString());
                    txtTelefono.setText(task.getResult().child("telefono").getValue().toString());
                    txtRangoPrecio.setText(task.getResult().child("rangoPrecios").getValue().toString());
                    txtDireccion.setText(task.getResult().child("direccion").getValue().toString());
                    txtHorario.setText(task.getResult().child("horario").getValue().toString());
                }
            }
        });
    }
}