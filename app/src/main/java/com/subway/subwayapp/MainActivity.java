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
    private static final String URL= Constantes.HOST+"cliente/login.php";
    private RequestQueue requestQueue;
    private EditText etEmail, etPass;
    //UsuarioIMP usuarioIMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        initUI();
    }

    private void initUI() {
        //usuarioIMP = new UsuarioIMP();
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPasword);
    }

    public void iniciarSesion(View view){
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();
        verificarCredenciales(email,password);
        //int id_usuario = usuarioIMP.verificarCredenciales(email,password,view);

        //Intent i = new Intent(this, NavegationActivity.class);
        //i.putExtra("UsuLog",id_usuario);
        //startActivity(i);
    }

    private void verificarCredenciales(final String email,final String password) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Usuario usuario = new Usuario();
                        try {
                            JSONObject json = new JSONObject(response);

                            Intent i = new Intent(MainActivity.this, NavegationActivity.class);
                            i.putExtra("UsuLog",json.getInt("id_usuario"));
                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("in","no inicio sesion");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("in","no inicio sesion");
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("correo",email);
                params.put("password",password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void crearCuenta(View view) {
        Intent i = new Intent(this, MiCuentaActivity.class);
        startActivity(i);
    }
}