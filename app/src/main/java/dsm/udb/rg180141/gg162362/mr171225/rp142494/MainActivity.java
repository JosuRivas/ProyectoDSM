package dsm.udb.rg180141.gg162362.mr171225.rp142494;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dsm.udb.rg180141.gg162362.mr171225.rp142494.modelos.Negocio;

public class MainActivity extends AppCompatActivity {

    private EditText correoEDT,contrasenaEDT;
    private Button botonIngresar;
    private TextView txtRegistrarse;
    private FirebaseAuth miAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.TemaSplash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correoEDT = (EditText) findViewById(R.id.loginCorreoEDT);
        contrasenaEDT = (EditText) findViewById(R.id.loginContrasenaEDT);
        botonIngresar = (Button) findViewById(R.id.botonIniciarSesion);
        txtRegistrarse = (TextView) findViewById(R.id.txtIrRegistrar);
        miAuth = FirebaseAuth.getInstance();

        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo,contrasena;
                correo = correoEDT.getText().toString();
                contrasena = contrasenaEDT.getText().toString();

                if (TextUtils.isEmpty(correo)) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar un correo", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(contrasena)) {
                    Toast.makeText(getApplicationContext(), "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
                    return;
                }

                miAuth.signInWithEmailAndPassword(correo,contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this,activity_pantalla_principal.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"Credenciales incorrectas",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        txtRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity_Registarse.class);
                startActivity(intent);
            }
        });
    }
}