package com.subway.subwayapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail, txtPass;
    int numero;
    private RequestQueue requestQueue;
    private static final String URL="http://192.168.1.16:8081/api_subway/cliente/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this);
        enlazarcomponentes();

    }

    private void enlazarcomponentes() {
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPass = (EditText) findViewById(R.id.txtPass);
    }
    public void login(View v){

        String Email = txtEmail.getText().toString();
        String Pas = txtPass.getText().toString();
        vertificarCredenciales(Email,Pas);
    }

    private void vertificarCredenciales(final String email,final String pas) {
        StringRequest sr = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "inicio sesion", Toast.LENGTH_LONG ).show();
                        Log.i("in",response);
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
                params.put("password",pas);
                return params;
            }
        };
        requestQueue.add(sr);
    }

    public void onClick(View v) {
        // Do something in response to button click
        Intent i = new Intent(this, CreateAccount.class);
        System.out.println( "gaaa");
        startActivity(i);
    }
}