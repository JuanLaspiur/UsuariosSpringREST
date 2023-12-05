package com.example.demo.dao;

import com.example.demo.modelos.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public List getUsuarios() {
      String query = "FROM Usuario"; //parecido a sql pero es hibernante no es la tabla es la Clase Usuario
      //objetoconexionconbdatos.ejecutaQuery.lotransforma en lista;
      return  entityManager.createQuery(query).getResultList();
    }

  @Override
  public void eliminar(int id) {
     Usuario usuario = entityManager.find(Usuario.class,id);
     entityManager.remove(usuario);
  }

  @Override
  public void registrar(Usuario usuario) {
    entityManager.merge(usuario);
  }

  @Override
  public Usuario obtenerUsuarioVerificado(Usuario usuario) {
    String query ="FROM Usuario WHERE mail= :mail";

    List <Usuario> lista = entityManager.createQuery(query)
            .setParameter("mail",usuario.getMail())
            .getResultList();
    //si esta vacia la lista, no hay usuario con esas credenciales
    if(lista.isEmpty()){
      return null;
    }

    String passwordHashed = lista.get(0).getPassword();
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    /*si encontro algun usuario me devuelve una lista con los datos, sino no devuelve nada*/
    if(argon2.verify(passwordHashed, usuario.getPassword())){
    return lista.get(0);
    }
    return null;
  }


}
