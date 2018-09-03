package pe.edu.upeu.upeu;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnEnviar;
    EditText txtNumero, txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bu=getIntent().getExtras();
        String usuario=bu.getString( "valorUser");
        btnEnviar=(Button)findViewById(R.id.button2);
        txtNumero=(EditText)findViewById(R.id.editText3);
        txtMensaje=(EditText)findViewById(R.id.editText4);

        Log.i("Informacion" , usuario);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });


    }

    public void sendSMS(){
        Intent smsInte=new Intent(Intent.ACTION_VIEW);
        smsInte.setData(Uri.parse("smsto:"));
        smsInte.setType("vnd.android-dir/mms-sms");
        smsInte.putExtra("adress",new String(String.valueOf(txtNumero)));
        smsInte.putExtra("sms_body", String.valueOf(txtMensaje.getText()));
        try {
            startActivity(smsInte);
            finish();
            Log.i("Informacion", "Mensaje Enviado");
        }catch (ActivityNotFoundException ex){
            Toast.makeText(MainActivity.this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show();

        }
    }
}
