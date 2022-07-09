package com.subway.subwayapp.dao;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import com.subway.subwayapp.R;
import com.subway.subwayapp.constantes.Constantes;
import com.subway.subwayapp.entity.Usuario;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class UsuarioIMP implements UsuarioDAO{
    private static final String URL= Constantes.HOST+"cliente/login.php";
    private RequestQueue requestQueue;


    @Override
    public void verificarCredenciales(String email, String password, View view) {
        requestQueue = Volley.newRequestQueue(view.getContext());

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

                            Intent i = new Intent(view.getContext(), NavegationActivity.class);
                            i.putExtra("UsuLog",usuario.getId_usuario());
                            view.getContext().startActivity(i);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("in","no inicio sesion");
                            Toast.makeText(view.getContext(),"Credenciales invalidas",Toast.LENGTH_LONG).show();
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

    public void obtenerNombre(final int codigo, View view) {
        final String URL1= Constantes.HOST+"cliente/datos.php";
        requestQueue = Volley.newRequestQueue(view.getContext());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Usuario usuario = new Usuario();
                        try {
                            JSONObject json = new JSONObject(response);
                            TextView tv = (TextView) view.findViewById(R.id.textUsername);
                            tv.setText(json.getString("nombre") + "!");
                            //textView.setText(json.getString("nombre"));

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
                params.put("codigo",String.valueOf(codigo));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void registrarCliente(View view,String nombre, String apellido, String dni, String celular, String email, String password, String fecha){
        final String URL1= Constantes.HOST+"cliente/registro.php";
        requestQueue = Volley.newRequestQueue(view.getContext());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("in",response);
                        Toast.makeText(view.getContext(),"Cliente Registrado",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(view.getContext(), MainActivity.class);
                        view.getContext().startActivity(i);

                        /*
                        try {
                            JSONObject json = new JSONObject(response);
                            /*
                            if(json.getString("nombre")=="Registrado"){
                                Log.i("in","Registrado");
                            }
                            //Log.i("in",json.getString());
                            Toast.makeText(view.getContext(),"Registrado correctamente",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(view.getContext(), MainActivity.class);
                            view.getContext().startActivity(i);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("in","no inicio sesion");
                        }*/
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
                params.put("nombre",String.valueOf(nombre));
                params.put("apellido",String.valueOf(apellido));
                params.put("dni",String.valueOf(dni));
                params.put("celular",String.valueOf(celular));
                params.put("email",String.valueOf(email));
                params.put("password",String.valueOf(password));
                params.put("fecha",String.valueOf(fecha));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
