package com.example.demo.controladores;


import com.example.demo.modelos.Usuario;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*minuto 1:19:41 */

@RestController
public class UsuarioControlador {

    @RequestMapping("/usuario/{id}")
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
    @RequestMapping("/buscarUsuarios")
    public List<Usuario> buscarUsuarios(){
        Usuario u1= new Usuario();
        u1.setId(5);
        u1.setNombre("Lucas Moy");
        u1.setMail("lucas@gmail.com");
        u1.setTelefono("123456789");
        u1.setPassword("987654321");

        Usuario u2 = new Usuario(1,"Pepe Moy", "pepe@gmail.com","12315455","555566");
        Usuario u3 = new Usuario(2,"Otro Usuario", "otro@gmail.com", "987654321", "123456789");
        Usuario u4 = new Usuario(3,"Usuario Cuatro", "cuatro@gmail.com", "11112222", "33334444");
        Usuario u5 = new Usuario(4,"Último Usuario", "ultimo@gmail.com", "55556666", "77778888");

        return List.of(u1,u2,u3,u4,u5);
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
    @RequestMapping("/eliminarUsuario")
    public Usuario eliminarUsuario(){
        Usuario u1= new Usuario();
        u1.setNombre("Lucas Moy");
        u1.setMail("lucas@gmail.com");
        u1.setTelefono("123456789");
        u1.setPassword("987654321");
        return u1;
    }




}
