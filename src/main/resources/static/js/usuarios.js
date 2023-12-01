

// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    cargarUsuarios();
  $('#usuarios').DataTable(); //jQuery para paginación de la tabla
});

async function cargarUsuarios(){
//Para hacer un 'llamado al servidor tenemos que usar una funcion fetch

const request = await fetch('/buscarUsuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json'
    }
   // body: JSON.stringify({a: 1, b: 'Textual content'}) no es necesario porque no es POST sino GET
  });
  const usuariosJSONLIST = await request.json();

  let listadoHTML='';

  for(let usuario of usuariosJSONLIST){
    let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+ usuario.apellido +'</td><td>'+ usuario.mail +'</td><td>'+ usuario.telefono +'</td><td><a href="#" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>'
    listadoHTML+=usuarioHTML;
  }
  document.querySelector('#usuarios tbody').outerHTML=listadoHTML;
  console.log(usuariosJSONLIST);

  

/*30/11/2023 quede en minuto 1:46:59 */
}