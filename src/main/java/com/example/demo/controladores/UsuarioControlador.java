package com.example.demo.controladores;


import com.example.demo.dao.UsuarioDao;
import com.example.demo.modelos.Usuario;
import com.example.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/*minuto 1:19:41 */

@RestController
public class UsuarioControlador {
  @Autowired
  private JWTUtil jwtUtil;
  @Autowired // creación de instancia de UsuarioDaoImpl, patrón singleton
  private UsuarioDao usuarioDao;




    //listado comopleto de usuarios
  @RequestMapping(value= "/buscarUsuarios", method = RequestMethod.GET)
  public List buscarUsuarios(@RequestHeader(value = "Authorization") String tokenLocalStorage ){
    String idToken =jwtUtil.getKey(tokenLocalStorage);
    if(idToken == null){
       return new ArrayList<>();
    }

    return usuarioDao.getUsuarios();
    }



  @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
  public void eliminarUsuario(@PathVariable int id, @RequestHeader(value = "Authorization") String tokenLocalStorage ){
   String idToken =jwtUtil.getKey(tokenLocalStorage);
     if(idToken == null){
        return;
     }
     usuarioDao.eliminar(id);
    }



  @RequestMapping(value= "/buscarUsuarios", method = RequestMethod.POST)
  public void registrarUsuario(@RequestBody Usuario usuario, @RequestHeader(value = "Authorization") String tokenLocalStorage){ //Request Body esta conviertiendo el JSon que recibe a un
    String idToken =jwtUtil.getKey(tokenLocalStorage);
    if(idToken == null){
      return;
    }
                                                             // usuario automaticamente
    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
    String hash = argon2.hash(1,1024,1, usuario.getPassword());
    usuario.setPassword(hash);
    usuarioDao.registrar(usuario);

  }



}
/*
*Mejora proxima, Spring Security y mapeo de ERRORES (401,500, ect,) FALTA
*
* */