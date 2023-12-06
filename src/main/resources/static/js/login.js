
// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    
});

async function iniciarSesion(){
/*
Este código en JavaScript es una función asincrónica (async function) llamada iniciarSesion que se utiliza
para manejar el inicio de sesión de un usuario. Aquí hay una explicación detallada de cada parte del código:

1-Creación del objeto datos:
*/

let datos = {};

/*
 crea un objeto vacío llamado datos que se utilizará para almacenar la dirección de
correo electrónico (mail) y la contraseña (password) introducidas por el usuario en un formulario de inicio de sesión.

2-Se obtienen los valores de los campos de entrada del formulario con los ID txtMail y txtPassword y se asignan a
las propiedades mail y password del objeto datos, respectivamente.
*/

datos.mail = document.getElementById("txtMail").value;
datos.password =document.getElementById("txtPassword").value;



/*3- Uso de fetch para hacer una solicitud al servidor:*/

const request = await fetch('/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos) //agarra cualquier objeto JS y lo combierte en un String de JSON

  });


/*
Se utiliza la función fetch para realizar una solicitud al servidor. La URL de la solicitud es /login, y se está
realizando una solicitud de tipo POST. Se incluyen encabezados para indicar que se acepta y se envía información
 en formato JSON. El cuerpo de la solicitud se compone de los datos del usuario convertidos a una cadena JSON
 mediante JSON.stringify(datos).

**/

//4-Espera de la respuesta del servidor:

  let respuesta = await request.text();
 /*
 Se utiliza await para esperar la respuesta de la solicitud. La respuesta se trata como texto y se almacena en la
 variable respuesta.
 */

//5-Manejo de la respuesta del servidor:


  if (respuesta != 'FAIL') { //GURARDAR EN LOCALSTORAGE
    localStorage.token = respuesta;
    localStorage.mail = datos.mail;
    window.location.href = 'usuarios.html';
}else{
        alert('Las credenciales son incorrectas, por favor intente nuevamente')
    }
/*
Se verifica si la respuesta del servidor no es igual a 'FAIL'. Si es así, se asume que el inicio de sesión fue exitoso.
Se almacena el token de sesión en el almacenamiento local (localStorage), junto con la dirección de correo electrónico
del usuario. Luego, se redirige a la página 'usuarios.html'. Si la respuesta es 'FAIL', se muestra una alerta indicando
que las credenciales son incorrectas.
*/
  

}
