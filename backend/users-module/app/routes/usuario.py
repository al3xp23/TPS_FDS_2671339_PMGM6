from fastapi import APIRouter, Body, Query
from typing import Annotated
from fastapi.encoders import jsonable_encoder
from models.usuario_finded_by import Usuario_Finded_by
from werkzeug.security import generate_password_hash

from controllers.usuario import (
    add_user,
    delete_user,
    retrieve_user_by_id,
    retrieve_user_by_name,
    retrieve_user_by_username,
    retrieve_all_users,
    update_user,
)

from models.usuario import (
    ErrorResponseModel,
    ResponseModel,
    Usuario,
    UpdateUsuario,
)

usuario = APIRouter()

# Add a new user in endpoint /api/usuario
@usuario.post(
    "/api/usuario", response_description="Usuario agregado a la base de datos")
def add_usuario_data(usuario: Usuario = Body(...)):
    usuario = jsonable_encoder(usuario)
    usuario["credenciales"]["contrasena"] = generate_password_hash(
                                                            password=usuario["credenciales"]["contrasena"], 
                                                            method="scrypt")
    new_usuario = add_user(usuario)
    return ResponseModel(new_usuario, "Usuario agregado exitosamente.")

# Get all users in endpoint /usuarios
@usuario.get(
    "/api/usuarios", response_description="Usuarios obtenidos de la base de datos")
def get_all_usuarios():
    usuarios = retrieve_all_users()
    if usuarios:
        return ResponseModel(usuarios, "Usuarios obtenidos exitosamente.")
    return ErrorResponseModel(
        "No se encontraron usuarios.", 404, "No se encontraron usuarios.")

#Get a user by id or name in endpoint /usuario/{find_by}
@usuario.get(
    "/api/usuario/{find_by}", 
    response_description="Usuario obtenido de la base de datos")
def get_usuario(
    find_by: Usuario_Finded_by, value: Annotated[str, Query(max_length=25)]):
    usuario = {}
    if find_by == Usuario_Finded_by.id:
        usuario = retrieve_user_by_id(value)
    elif find_by == Usuario_Finded_by.name:
        usuario = retrieve_user_by_name(value)
    elif find_by == Usuario_Finded_by.username:
        usuario = retrieve_user_by_username(value)

    if usuario:
        return ResponseModel(usuario, "Usuario obtenido exitosamente.")
    return ErrorResponseModel(
        "No se encontró el usuario.", 404, "No se encontró el usuario.")

# Update a user by id in endpoint /api/usuario/{id}
@usuario.put("/api/usuario/{id}")
def update_usuario_data(id: str, data: UpdateUsuario = Body(...)):
    user_dict = delete_none_in_dict(data.dict())
    if user_dict["credenciales"]["contrasena"]:
        user_dict["credenciales"]["contrasena"] = generate_password_hash(
                                                            password=user_dict["credenciales"]["contrasena"],
                                                            method="scrypt")
    updated_usuario = update_user(id, user_dict)
    if updated_usuario:
        return ResponseModel(
            "Usuario con ID: {} actualizado exitosamente.".format(id),
            "Usuario actualizado exitosamente.",
        )
    return ErrorResponseModel(
        "Error al actualizar el usuario.", 404, "No se encontró el usuario."
    )

def delete_none_in_dict(_dict):
    """Delete None values recursively from all of the 
    dictionaries, tuples, lists, sets"""
    if isinstance(_dict, dict):
        for key, value in list(_dict.items()):
            if isinstance(value, (list, dict, tuple, set)):
                _dict[key] = delete_none_in_dict(value)
            elif value is None or key is None:
                del _dict[key]
    elif isinstance(_dict, (list, set, tuple)):
        _dict = type(_dict)(
            delete_none_in_dict(item) for item in _dict if item is not None)
    return _dict

# Delete a user by id in endpoint /api/usuario/{id}
@usuario.delete(
    "/api/usuario/{id}", response_description="Usuario eliminado de la base de datos")
def delete_usuario_data(id: str):
    deleted_usuario = delete_user(id)
    if deleted_usuario:
        return ResponseModel(
            "Usuario con ID: {} eliminado exitosamente.".format(id),
            "Usuario eliminado exitosamente.",
        )
    return ErrorResponseModel(
        "Error al eliminar el usuario.", 404, "No se encontró el usuario."
    )