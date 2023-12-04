package com.example.demo.controladores;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutControlador {

    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping(value= "/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){ //Request Body esta conviertiendo el JSon que recibe a un usuario automaticamente
       if(usuarioDao.verificarMailPassword(usuario)){
           return "OK";  }

       return "No";
    }

}
