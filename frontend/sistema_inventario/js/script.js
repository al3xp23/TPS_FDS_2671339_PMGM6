
function navegar() {
    var select = document.querySelector("#funcionUsuario");
    
    switch (select.value) {
        case '1':
            window.open("login.html", "_self");
            break;
        case '2':
            window.open("Actualizar_Usuario.html", "_self");
            break;
        case '3':
            window.open("consultar_usuario.html", "_self");
            break;
        case '4':
            window.open("estados_de_usuarios.html", "_self");
            break;
        case '5':
            window.open("eliminar_usuario.html", "_self");
            break;
        case '6':
            window.open("crear_usuario.html", "_self");
            break;
        default:
            console.log('el valor es :' + select.value);
        }
}

const entry_endpoint = "https://sistema-mgm-service-users.azurewebsites.net"
const span_message = document.getElementById("login_message")

// mensaje de prueba
// fetch(entry_endpoint, {
//     method: 'GET',
//     headers: {
//     'Content-Type': 'application/json'
//     }
// })
// .then((response) => response.json())
// .then((response) => span_message.innerText = response.message)
// .catch(function(error) {console.log(error)
// });

// obtener referencia al formulario
const form = document.querySelector('#login-form');

// agregar un gestor de eventos para enviar el formulario
form.addEventListener('submit', async (event) => {
    // detener el comportamiento predeterminado del formulario (que es recargar la página)
    event.preventDefault();
  
    // obtener los datos del formulario como un objeto FormData
    const formData = new FormData(form);

    // enviar los datos al servidor usando fetch()
    const response = await fetch(entry_endpoint + "/api/login", {
      method: 'POST',
      body: formData
    });
  
    // procesar la respuesta del servidor
    const data = await response.json();

    if (data.access_token) {
      // si el inicio de sesión fue exitoso, redirigir al usuario a la página de inicio
      window.location.href = "index.html";
      window.localStorage.setItem("tk", data.access_token);
    } else {
      // si el inicio de sesión falló, mostrar un mensaje de error
      span_message.innerText = data.detail;
    }
    
  });


  const formProducto = document.querySelector('#producto-form');

  formProducto.addEventListener('submit', async (event) => {
    console.log(formProducto);
  });
