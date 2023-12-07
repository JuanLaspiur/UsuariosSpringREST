
// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    
});

async function registrarUsuarios(){
let datos = {};



datos.nombre =document.getElementById("txtNombre").value;
datos.apellido =document.getElementById("txtApellido").value;
datos.mail =document.getElementById("txtMail").value;
datos.password =document.getElementById("txtPassword").value;

if (!datos.nombre || !datos.apellido || !datos.mail || !datos.password) {
    alert('Todos los campos son obligatorios');
    return;
}


let repetirPassword = document.getElementById("txtRepetirPassword").value;

if(repetirPassword != datos.password){
    alert('Las contraseñas no coinciden');
    return
}


//Para hacer un 'llamado al servidor tenemos que usar una funcion fetch
try{
const request = await fetch('/buscarUsuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos) //agarra cualquier objeto JS y lo combierte en un String de JSON

  });
} catch (error) {
    console.error('Error en la solicitud:', error);
    alert('Hubo un problema al comunicarse con el servidor');}

  alert('La cuenta fue creada con exito');
 window.location.href = 'login.html';
}
