package cl.cokke.CrudPersonas.services;

import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.dto.UsuarioEncontradoDTO;
import cl.cokke.CrudPersonas.dto.UsuarioListDTO;
import cl.cokke.CrudPersonas.exceptions.ApiError;
import cl.cokke.CrudPersonas.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<UsuarioListDTO> buscarTodos() throws ApiError;
    public UsuarioEncontradoDTO buscarPorId(Long id) throws ApiError;
    public UsuarioCreatedDTO crearUsuario(Usuario u) throws ApiError;
    public UsuarioCreatedDTO editarUsuario(Usuario u);
    public void eliminarUsuario(Long id);
}
