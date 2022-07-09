package com.subway.subwayapp.dao;

import android.view.View;
import android.widget.TextView;

public interface UsuarioDAO {

    void verificarCredenciales(final String email,final String password, View view);

    void obtenerNombre(final int codigo, View view);
}
