package com.example.clienteparcial1ecosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variable comunicacion
    private ComunicacionTCP comm;

    //Variable para componenetes de la interfaz
    private EditText posxT;
    private EditText posyT;
    private EditText recorT;
    private RadioButton leveBtn;
    private RadioButton mediaBtn;
    private RadioButton altaBtn;
    private Button vistaBtn;
    private Button confirmarBtn;

    //Variables para guardar mensajes
    private String posX;
    private String posY;
    private String recordatorio;
    private String importancia;
    private String mensajeRecordatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        comm = new ComunicacionTCP(this);
        comm.solicitarConexion();

        posxT = findViewById(R.id.posxT);
        posyT = findViewById(R.id.posyT);
        recorT = findViewById(R.id.recorT);
        leveBtn = findViewById(R.id.leveBtn);
        mediaBtn = findViewById(R.id.mediaBtn);
        altaBtn = findViewById(R.id.altaBtn);
        vistaBtn = findViewById(R.id.vistaBtn);
        confirmarBtn = findViewById(R.id.confirmarBtn);

        confirmarBtn.setOnClickListener(
                (v) -> {
                    posX = posxT.getText().toString();
                    posY = posyT.getText().toString();
                    recordatorio = recorT.getText().toString();

                    mensajeRecordatorio = posX +"," + posY +"," + recordatorio;

                    if(!posX.equals("") && !posY.equals("") && !recordatorio.equals("")) {
                        comm.mandarMensaje(mensajeRecordatorio);

                    } else {
                        runOnUiThread(
                                () -> {
                                    Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                                }
                        );
                    }
                }
        );
    }
}
