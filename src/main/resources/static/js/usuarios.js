

// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    cargarUsuarios();
  $('#usuarios').DataTable();//jQuery para paginación de la tabla
  document.getElementById("MailJWTSesionIniciada").outerHTML = localStorage.mail;
});

async function cargarUsuarios(){
//Para hacer un 'llamado al servidor tenemos que usar una funcion fetch

const request = await fetch('/buscarUsuarios', {
    method: 'GET',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json',
      'Authorization':localStorage.token
    }
   // body: JSON.stringify({a: 1, b: 'Textual content'}) no es necesario porque no es POST sino GET
  });
  const usuariosJSONLIST = await request.json();

  let listadoHTML='';


  for(let usuario of usuariosJSONLIST){
    let usuarioTelefono = usuario.telefono == null ? '-': usuario.telefono;
    let usuarioHTML = '<tr><td>'+usuario.id+'</td><td>'+usuario.nombre+' '+ usuario.apellido+'</td><td>'+ usuario.mail +'</td><td>'+ usuarioTelefono +'</td><td><a href="#" onClick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>'
    listadoHTML+=usuarioHTML;
  }
  document.querySelector('#usuarios tbody').outerHTML=listadoHTML;
  console.log(usuariosJSONLIST);

  

}

async function eliminarUsuario(id){

if(!confirm('¿Desea eliminar este usuario?')){
  return;
}

  const request = await fetch('/usuario/'+id, {
    method: 'DELETE',
    headers: {
      'Accept': 'application/json', //indica que va a estar usando json
      'Content-Type': 'application/json',
      'Authorization':localStorage.token
    }
   // body: JSON.stringify({a: 1, b: 'Textual content'}) no es necesario porque no es POST sino GET
  });
  
location.reload();  

}