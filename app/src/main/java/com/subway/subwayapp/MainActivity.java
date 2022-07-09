package com.subway.subwayapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.subway.subwayapp.constantes.Constantes;
import com.subway.subwayapp.dao.UsuarioIMP;
import com.subway.subwayapp.entity.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPass;
    UsuarioIMP usuarioIMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        usuarioIMP = new UsuarioIMP();
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPasword);
    }

    public void iniciarSesion(View view){
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        usuarioIMP.verificarCredenciales(email,password,view);
    }


    public void crearCuenta(View view) {
        Intent i = new Intent(this, CreateAccountActivity.class);
        startActivity(i);
    }
}