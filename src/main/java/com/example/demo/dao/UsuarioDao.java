package com.example.demo.dao;

import com.example.demo.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioDao {
    /*Cuando tenemos una clase tenemos funciones, una interface es como un archivo en el que
     se indica que funciones debe tener una clase
      como debe ser la funcion y nada mas.
     */


    public List<Usuario> getUsuarios();


    public void eliminar(int id);

    public void registrar(Usuario usuario);


    public Usuario obtenerUsuarioVerificado(Usuario usuario);
}
