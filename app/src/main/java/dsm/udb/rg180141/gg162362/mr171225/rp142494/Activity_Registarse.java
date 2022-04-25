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

public class Activity_Registarse extends AppCompatActivity {

    private EditText contrasenaEDT,  correoEDT, nombreEDT;
    private Switch switchTipoCuenta;
    private Button botonRegistrar;

    private FirebaseAuth miAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registarse);

        contrasenaEDT = (EditText) findViewById(R.id.registrarContrasenaEDT);
        correoEDT = (EditText) findViewById(R.id.registrarCorreoEDT);
        nombreEDT = (EditText) findViewById(R.id.registrarNombreEDT);
        switchTipoCuenta = (Switch) findViewById(R.id.switchTipoCuenta);
        botonRegistrar = (Button) findViewById(R.id.botonRegistrarse);

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

                if (switchTipoCuenta.isChecked()){
                    Intent intent = new Intent(Activity_Registarse.this,Registro_Negocio.class);
                    startActivity(intent);
                }else{
                    miAuth.createUserWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Registrado correctamente!",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Activity_Registarse.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Error al registrarse, intentelo de nuevo", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }


            }
        });


    }
}