function buscarProducto() {
  var nombre = document.forms["producto-form"]["busquedaProducto"].value;
  var id = document.forms["producto-form"]["busquedaID"].value;

  var url = "http://localhost:8080/api/productos/buscar";
  if(nombre !== '' && id !== ''){
    url = url + "?idProducto="+id+"&nombreProducto="+nombre;
  }
  if(nombre !== '' && id === ''){
    url = url + "?nombreProducto="+nombre;
  }
  if(nombre === '' && id !== ''){
    url = url + "?idProducto="+id;
  }

  // Consumir el endpoint o la url de java
  fetch(url)
    .then(response => response.json())
    .then(datos => mostrarData(datos))
    .catch( error => console.log(error) )


  // Metodo que pinta los datos
  const mostrarData = (data) => {
    console.log(data)
    let body = ""
    for (var i = 0; i < data.length; i++) {      
        body+=`<tr><td>${data[i].idProducto}</td><td>${data[i].nombreProducto}</td><td>${data[i].precioProducto}</td><td>${data[i].marca}</td></tr>`
    }
    document.getElementById('data').innerHTML = body
    //console.log(body)
  }
}


function buscarProveedor() {

  var url = "http://localhost:8080/api/proveedor/buscar";


  // Consumir el endpoint o la url de java
  fetch(url)
    .then(response => response.json())
    .then(datos => mostrarData(datos))
    .catch( error => console.log(error) )


  // Metodo que pinta los datos
  const mostrarData = (data) => {
    console.log(data)
    let body = ""
    for (var i = 0; i < data.length; i++) {      
        body+=`<tr><td>${data[i].NombreDeEmpresa}</td><td>${data[i].direccion}</td><td>${data[i].telefono}</td><td>${data[i].descripcion}</td></tr>`
    }
    document.getElementById('data').innerHTML = body
    //console.log(body)
  }
}

function buscarusuario() {

  var url = "http://localhost:8080/api/usuario/buscar";


  // Consumir el endpoint o la url de java
  fetch(url)
    .then(response => response.json())
    .then(datos => mostrarData(datos))
    .catch( error => console.log(error) )


  // Metodo que pinta los datos
  const mostrarData = (data) => {
    console.log(data)
    let body = ""
    for (var i = 0; i < data.length; i++) {      
        body+=`<tr><td>${data[i].NombreUsuario}</td><td>${data[i].telefono}</td><td>${data[i].correo}</td></tr>`
    }
    document.getElementById('data').innerHTML = body
    //console.log(body)
  }
}