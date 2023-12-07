// Call the dataTables jQuery plugin
$(document).ready(function() { //funcion que se ejecuta una vez que se haya cargado la pagina
    cargarUsuarios();
    $('#usuarios').DataTable(); // jQuery para paginación de la tabla
    document.getElementById("MailJWTSesionIniciada").outerHTML = localStorage.mail;
});

async function cargarUsuarios() {
    try {
        const response = await fetch('/buscarUsuarios123', {
            method: 'GET',
            headers: {
                'Accept': 'application/json', //indica que va a estar usando json
                'Content-Type': 'application/json',
                //'Authorization': localStorage.token
            }
        });

        if (!response.ok) {
            throw new Error(`Error en la solicitud if: ${response.statusText}`, "mensaje ", response.statusText);
        }

        const usuariosJSONLIST = await response.json();

        let listadoHTML = '';

        for (let usuario of usuariosJSONLIST) {
            let usuarioTelefono = usuario.telefono == null ? '-' : usuario.telefono;
            let usuarioHTML = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>' + usuario.mail + '</td><td>' + usuarioTelefono + '</td><td><a href="#" onClick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a></td></tr>';
            listadoHTML += usuarioHTML;
        }

        document.querySelector('#usuarios tbody').outerHTML = listadoHTML;
        console.log(usuariosJSONLIST);
    } catch (error) {
        console.error('Error en la solicitud: catch', error );
        // Manejo de errores, puede ser útil mostrar un mensaje al usuario
    }
}

async function eliminarUsuario(id) {
    if (!window.confirm('¿Desea eliminar este usuario?')) {
        return;
    }

    try {
        const response = await fetch('/usuario/' + id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json', //indica que va a estar usando json
                'Content-Type': 'application/json',
                'Authorization': localStorage.token
            }
        });

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.statusText}`);
        }

        // Actualizar la tabla sin recargar toda la página
        cargarUsuarios();
    } catch (error) {
        console.error('Error en la solicitud:', error);
        // Manejo de errores, puede ser útil mostrar un mensaje al usuario
    }
}
