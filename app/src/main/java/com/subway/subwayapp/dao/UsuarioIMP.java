package com.subway.subwayapp.dao;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.subway.subwayapp.MainActivity;
import com.subway.subwayapp.NavegationActivity;
import com.subway.subwayapp.constantes.Constantes;
import com.subway.subwayapp.entity.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UsuarioIMP implements UsuarioDAO{
    private static final String URL= Constantes.HOST+"cliente/login.php";
    private RequestQueue requestQueue;


    @Override
    public int verificarCredenciales(String email, String password, View view) {
        requestQueue = Volley.newRequestQueue(view.getContext());
        Usuario usuario = new Usuario();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Usuario usuario = new Usuario();
                        try {
                            JSONObject json = new JSONObject(response);
                            usuario.setId_usuario(json.getInt("id_usuario"));

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
        return usuario.getId_usuario();
    }
}
