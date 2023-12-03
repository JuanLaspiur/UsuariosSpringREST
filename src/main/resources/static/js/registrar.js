
// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    
});

async function registrarUsuarios(){
let datos = {};

datos.nombre = ;


//Para hacer un 'llamado al servidor tenemos que usar una funcion fetch

const request = await fetch('/buscarUsuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos) //agarra cualquier objeto JS y lo combierte en un String de JSON

  });
  const usuariosJSONLIST = await request.json();


  

}
