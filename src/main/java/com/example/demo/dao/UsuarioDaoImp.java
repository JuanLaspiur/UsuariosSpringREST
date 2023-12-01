package com.example.demo.dao;

import com.example.demo.modelos.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository //conexion con la base de datos
@Transactional  //funcionalidad a la clase de armar querys
public class UsuarioDaoImp implements UsuarioDao{

  @PersistenceContext
  private EntityManager entityManager; //sirve para hacer la conexion


    @Override
    public List<Usuario> getUsuarios() {
      String query = "FROM Usuario"; //parecido a sql pero es hibernante no es la tabla es la Clase Usuario
      //objetoconexionconbdatos.ejecutaQuery.lotransforma en lista;
      return  entityManager.createQuery(query).getResultList();
    }
}
