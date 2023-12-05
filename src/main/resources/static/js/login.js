
// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    
});

async function iniciarSesion(){
let datos = {};

datos.mail = document.getElementById("txtMail").value;
datos.password =document.getElementById("txtPassword").value;

//Para hacer un 'llamado al servidor tenemos que usar una funcion fetch

const request = await fetch('/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos) //agarra cualquier objeto JS y lo combierte en un String de JSON

  });
  const respuesta = await request.text();

  if (respuesta != 'FAIL') {
    localStorage.token = respuesta;
    localStorage.mail = datos.mail;
    window.location.href = 'usuarios.html';
}else{
        alert('Las credenciales son incorrectas, por favor intente nuevamente')
    }

  

}
