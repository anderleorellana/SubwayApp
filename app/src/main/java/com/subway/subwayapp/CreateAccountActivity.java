package com.subway.subwayapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.subway.subwayapp.dao.UsuarioIMP;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellido, txtDni, txtCelular, txtEmail, txtPassword,
        txtCondirmPassword, txtFecha;
    UsuarioIMP usuarioIMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        enlazarControles();
    }

    private void enlazarControles() {
        usuarioIMP = new UsuarioIMP();
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtDni = (EditText) findViewById(R.id.txtDni);
        txtCelular = (EditText) findViewById(R.id.txtCelular);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
    }
    public void registrarCliente(View view){
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String dni = txtDni.getText().toString();
        String celular = txtCelular.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String fecha = txtFecha.getText().toString();

        usuarioIMP.registrarCliente(view, nombre,apellido,dni,celular,email,password,fecha);
    }
}