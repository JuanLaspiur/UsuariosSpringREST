package com.example.demo.controladores;
//1-Importación de clases:
import com.example.demo.dao.UsuarioDao;
import com.example.demo.modelos.Usuario;
import com.example.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*
* Se importan las clases necesarias, incluida JWTUtil, Usuario, UsuarioDao y las clases de anotación de Spring
* (@Autowired, @RequestBody, @RequestMapping, @RestController).
*
* */

//2- Definición del controlador
@RestController
public class OutControlador {
 //3- Inyección de dependencias
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;
    /*
    * Se realiza la inyección de dependencias para obtener instancias de UsuarioDao y JWTUtil.
   gestionados por el contenedor de Spring.
  */




    //4-Manejo de la solicitud de inicio de sesión:
    /*
     * Se define un método llamado login que manejará las solicitudes POST a la URL /login. El parámetro @RequestBody indica que el
     *  objeto Usuario se extraerá del cuerpo de la solicitud y se convertirá automáticamente desde el formato JSON.
     *
     * */
    @RequestMapping(value= "/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){ //Request Body esta conviertiendo el JSon que recibe a un usuario automaticamente

        //5-Autenticación del usuario:
     Usuario usuarioLogeado = usuarioDao.obtenerUsuarioVerificado(usuario);
    /*Se utiliza el objeto usuarioDao para intentar obtener un usuario verificado a partir de la información proporcionada en la
        solicitud. */
     if(usuarioLogeado != null) {
         //*  Si se encuentra un usuario, se genera un token JWT usando jwtUtil y se devuelve como respuesta.
        String tokenJWT = jwtUtil.create(String.valueOf(usuarioLogeado.getId()),usuarioLogeado.getMail());
         return tokenJWT;
     }


   /*    En caso contrario,
 se devuelve la cadena "FAIL" para indicar que la autenticación ha fallado.//*/
       return "FAIL";
    }
}





/*
En resumen, este controlador de Spring Boot maneja las solicitudes de inicio de sesión, verifica las credenciales proporcionadas
* con la base de datos (a través de UsuarioDao), y devuelve un token JWT en caso de éxito o la cadena "FAIL" en caso de fallo.
* */

