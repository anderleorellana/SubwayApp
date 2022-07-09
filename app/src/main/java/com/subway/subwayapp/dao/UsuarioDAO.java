package com.subway.subwayapp.dao;

import android.view.View;

public interface UsuarioDAO {

    int verificarCredenciales(final String email,final String password, View view);

}
