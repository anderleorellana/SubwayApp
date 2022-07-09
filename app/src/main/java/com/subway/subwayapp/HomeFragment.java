package com.subway.subwayapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.subway.subwayapp.constantes.Constantes;
import com.subway.subwayapp.dao.UsuarioIMP;
import com.subway.subwayapp.entity.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private View homeView;
    private int ID_U;
    private TextView tUsername;
    UsuarioIMP usuarioIMP;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ID_U = getArguments().getInt("ID_USU",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        usuarioIMP = new UsuarioIMP();
        homeView = inflater.inflate(R.layout.fragment_home, container, false);
        tUsername = (TextView) homeView.findViewById(R.id.textUsername);
        usuarioIMP.obtenerNombre(ID_U, homeView);

        // Inflate the layout for this fragment
        return homeView;

    }

}