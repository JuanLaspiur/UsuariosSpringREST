package com.example.demo.controladores;


import com.example.demo.dao.UsuarioDao;
import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*minuto 1:19:41 */

@RestController
public class UsuarioControlador {


    @Autowired // creación de instancia de UsuarioDaoImpl, patrón singleton
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable int id){ //@PathVariable la variable estara en las llaves de RequestMapping
        Usuario u1= new Usuario();
        u1.setId(id);
        u1.setNombre("Lucas Moy");
        u1.setMail("lucas@gmail.com");
        u1.setTelefono("123456789");
        u1.setPassword("987654321");
        return u1;
    }


    //listado comopleto de usuarios
    @RequestMapping(value= "/buscarUsuarios", method = RequestMethod.GET)
    public List buscarUsuarios(){
         return usuarioDao.getUsuarios();
    }

    //Metodo que permita editar el usuario

    @RequestMapping("/editarUsuario")
    public Usuario editaUsuario(){
        Usuario u1= new Usuario();
        u1.setNombre("Lucas Moy");
        u1.setMail("lucas@gmail.com");
        u1.setTelefono("123456789");
        u1.setPassword("987654321");
        return u1;
    }


    //metodo que permita elimianr el usuario
    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@PathVariable int id){
       usuarioDao.eliminar(id);
    }


    //listado comopleto de usuarios
    @RequestMapping(value= "/buscarUsuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){ //Request Body esta conviertiendo el JSon que recibe a un usuario automaticamente
        usuarioDao.registrar(usuario);

    }



}
