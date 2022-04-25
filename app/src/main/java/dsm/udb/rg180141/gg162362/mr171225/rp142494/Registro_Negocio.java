package dsm.udb.rg180141.gg162362.mr171225.rp142494;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Registro_Negocio extends AppCompatActivity {

    Spinner spinnerDepartamentos,spinnerTipoNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_negocio);

        spinnerDepartamentos = (Spinner) findViewById(R.id.departamentoSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.array_departamentos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartamentos.setAdapter(adapter);

        spinnerTipoNegocio = (Spinner) findViewById(R.id.tipoNegocioSpinner);
        ArrayAdapter<CharSequence> adapterNegocios = ArrayAdapter.createFromResource(this,R.array.lista_negocios, android.R.layout.simple_spinner_item);
        adapterNegocios.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoNegocio.setAdapter(adapterNegocios);
    }
}