package com.example.sistemainternoficherosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private final String  BITACORA = "bitacora.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.txt_bitacora);
        String[] archivos  = fileList();
        if(archivoExiste(archivos)){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput(BITACORA));
                BufferedReader leerArchivo = new BufferedReader(archivo);
                String linea = leerArchivo.readLine();
                String bitacoraCompleta = "";
                while(linea != null){
                    bitacoraCompleta +=  linea + "\n";
                    linea = leerArchivo.readLine();
                }
                leerArchivo.close();
                archivo.close();
                editText.setText(bitacoraCompleta);
            }catch (IOException e){
                System.out.println("archivo no existe");
            }

        }
    }

    private boolean archivoExiste(String[] archivo){
        for (String arch:archivo
             ) {
            if (BITACORA.equals(arch)){
                return true;
            }
        }
        return false;
    }

    //btn guardar
    public void guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(BITACORA, Activity.MODE_PRIVATE));
            archivo.write(editText.getText().toString());
            //limpiar datos del buffer
            archivo.flush();
            archivo.close();
        }catch (IOException e){
            System.out.println("archivo no guardado");
        }
        Toast.makeText(this,"Bitacora guardada correctamente",Toast.LENGTH_SHORT).show();
        finish();
    }
}