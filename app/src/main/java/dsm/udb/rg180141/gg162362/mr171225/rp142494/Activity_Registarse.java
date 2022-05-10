package dsm.udb.rg180141.gg162362.mr171225.rp142494;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dsm.udb.rg180141.gg162362.mr171225.rp142494.modelos.Usuario;

public class Activity_Registarse extends AppCompatActivity {

    private EditText contrasenaEDT,  correoEDT, nombreEDT;
    private Switch switchTipoCuenta;
    private Button botonRegistrar;

    private FirebaseAuth miAuth;
    private DatabaseReference miDatabaseUsuarios,miDatabaseNegocios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registarse);

        contrasenaEDT = (EditText) findViewById(R.id.registrarContrasenaEDT);
        correoEDT = (EditText) findViewById(R.id.registrarCorreoEDT);
        nombreEDT = (EditText) findViewById(R.id.registrarNombreEDT);
        switchTipoCuenta = (Switch) findViewById(R.id.switchTipoCuenta);
        botonRegistrar = (Button) findViewById(R.id.botonRegistrarse);

        miDatabaseUsuarios = FirebaseDatabase.getInstance().getReference("usuarios");
        miDatabaseNegocios = FirebaseDatabase.getInstance().getReference("negocios");
        miAuth = FirebaseAuth.getInstance();

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo,contrasena,nombre;
                correo = correoEDT.getText().toString();
                contrasena = contrasenaEDT.getText().toString();
                nombre = nombreEDT.getText().toString();

                if (TextUtils.isEmpty(correo)) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar un correo", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(contrasena)) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(nombre)) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar una nombre", Toast.LENGTH_LONG).show();
                    return;
                }

                miAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            String uid = task.getResult().getUser().getUid();
                            String id = miDatabaseUsuarios.push().getKey();
                            Toast.makeText(getApplicationContext(),"Registrado correctamente!",Toast.LENGTH_LONG).show();

                            if (switchTipoCuenta.isChecked()){
                                String idNegocio = miDatabaseNegocios.push().getKey();
                                Usuario usuario = new Usuario(correo,"negocio",uid);
                                usuario.setIdNegocio(idNegocio);
                                miDatabaseUsuarios.child(id).setValue(usuario);
                                Intent intent = new Intent(Activity_Registarse.this,Registro_Negocio.class);
                                intent.putExtra("idNegocio",idNegocio);
                                startActivity(intent);
                            }else{
                                Usuario usuario = new Usuario(correo,"cliente",uid);
                                miDatabaseUsuarios.child(id).setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(Activity_Registarse.this,MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                                });

                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Error al registrarse, intentelo de nuevo", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }
}