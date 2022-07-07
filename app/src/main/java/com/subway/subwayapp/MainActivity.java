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
    private RequestQueue requestQueue;
    private static final String URL= Constantes.HOST+"cliente/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);

        initUI();
    }

    private void initUI() {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPasword);
    }

    public void iniciarSesion(View view){
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();
        vertificarCredenciales(email,password);
    }

    private void vertificarCredenciales(final String email,final String password) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Usuario usuario = new Usuario();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            JSONObject json = new JSONObject(response);
                            usuario.setId_usuario(json.getInt("id_usuario"));
                            usuario.setNombre(json.getString("nombre"));
                            usuario.setApellido(json.getString("apellido"));
                            usuario.setDni(json.getString("dni"));
                            usuario.setCelular(json.getString("celular"));
                            usuario.setCorreo(json.getString("correo"));
                            usuario.setPassword(json.getString("password"));
                            usuario.setFecha_nacimiento(format.parse(json.getString("fecha_nacimiento")));
                            usuario.setTipo(json.getString("tipo"));

                            Intent i = new Intent(MainActivity.this, NavegationActivity.class);
                            i.putExtra("UsuLog",usuario.getId_usuario());
                            startActivity(i);
                        } catch (JSONException | ParseException e) {
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