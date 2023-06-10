package cl.cokke.CrudPersonas.services;

import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.dto.UsuarioEncontradoDTO;
import cl.cokke.CrudPersonas.dto.UsuarioListDTO;
import cl.cokke.CrudPersonas.exceptions.ApiError;
import cl.cokke.CrudPersonas.model.Usuario;
import cl.cokke.CrudPersonas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Value("${parametros.regexEmail}")
    private String regexEmail;

    @Value("${parametros.regexPassword}")
    private String regexPassword;

    @Override
    public List<UsuarioListDTO> buscarTodos() throws ApiError {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            if (!usuarioRepository.findAll().isEmpty()) {
                usuarios = usuarioRepository.findAll();
            } else {
                throw new ApiError("La lista de usuarios esta vacia", HttpStatus.NOT_FOUND);
            }

            List<UsuarioListDTO> usuariosListDTO = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                UsuarioListDTO usuarioListDTO = new UsuarioListDTO();
                usuarioListDTO.setId(usuario.getId().toString());
                usuarioListDTO.setNombre(usuario.getName());
                usuarioListDTO.setEmail(usuario.getEmail());
                usuariosListDTO.add(usuarioListDTO);
            }
            return usuariosListDTO;
        }catch (Exception ex){
            throw new ApiError("No se logro traer la lista de usuarios", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UsuarioEncontradoDTO buscarPorId(Long id) throws ApiError {
        UsuarioEncontradoDTO usuarioEncontradoDTO = new UsuarioEncontradoDTO();
        Usuario usuario = new Usuario();
        try {
            if (!usuarioRepository.findById(id).isPresent()) {
                throw new ApiError("El usuario no fue encontrado", HttpStatus.NOT_FOUND);
            } else {
                usuario = usuarioRepository.findById(id).orElseThrow();
                usuarioEncontradoDTO.setId(usuario.getId().toString());
                usuarioEncontradoDTO.setNombre(usuario.getName());
                usuarioEncontradoDTO.setEmail(usuario.getEmail());
                usuarioEncontradoDTO.setCreated(usuario.getCreated().toString());
                if (usuario.isActive()) {
                    usuarioEncontradoDTO.setEstado("activo");
                } else {
                    usuarioEncontradoDTO.setEstado("desactivado");
                }
            }
            return usuarioEncontradoDTO;
        }catch(Exception ex){
            throw new ApiError("No se encontro el usuario buscado", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UsuarioCreatedDTO crearUsuario(Usuario u) throws ApiError {
        UsuarioCreatedDTO usuarioCreatedDTO = new UsuarioCreatedDTO();
        LocalDateTime now = LocalDateTime.now();
        try {
            if (!u.getEmail().matches(regexEmail)) {
                throw new ApiError("El correo no tiene el formato correcto", HttpStatus.BAD_REQUEST);
            }
            if (!u.getPassword().matches(regexPassword)) {
                throw new ApiError("El password debe tener al menos 1 Mayuscula, 1 numero y 8 digitos minimo", HttpStatus.BAD_REQUEST);
            }
            //Seteamos los parametros que se pasan en el servicio
            u.setCreated(now);
            u.setModified(now);
            u.setLast_login(now);
            u.setToken(UUID.randomUUID().toString());
            u.setActive(true);
            //Guardamos el usuario
            usuarioRepository.save(u);
            //Llenamos el DTO que mostramos en la salida de este metodo
            usuarioCreatedDTO.setId(u.getId());
            usuarioCreatedDTO.setName(u.getName());
            usuarioCreatedDTO.setEmail(u.getEmail());
            usuarioCreatedDTO.setCreated(u.getCreated());
            usuarioCreatedDTO.setModified(u.getModified());
            usuarioCreatedDTO.setLast_login(u.getLast_login());
            usuarioCreatedDTO.setToken(u.getToken());
            usuarioCreatedDTO.setActive(u.isActive());

            return usuarioCreatedDTO;
        }catch (Exception ex){
            throw new ApiError("No se pudo crear el usuario", HttpStatus.CONFLICT);
        }

    }

    @Override
    public UsuarioCreatedDTO editarUsuario(Usuario u , Long id) throws ApiError {
        UsuarioCreatedDTO usuarioCreatedDTO = new UsuarioCreatedDTO();
        LocalDateTime now = LocalDateTime.now();
        try {
                Usuario usuarioEncontrado = usuarioRepository.findById(id).orElseThrow();
                if (!u.getEmail().matches(regexEmail)) {
                    throw new ApiError("El correo no tiene el formato correcto", HttpStatus.BAD_REQUEST);
                }
                if (!u.getPassword().matches(regexPassword)) {
                    throw new ApiError("El password debe tener al menos 1 Mayuscula, 1 numero y 8 digitos minimo", HttpStatus.BAD_REQUEST);
                }
                //Actualizamos los parametros que se pasan en el servicio
                usuarioEncontrado.setId(id);
                usuarioEncontrado.setName(u.getName());
                usuarioEncontrado.setEmail(u.getEmail());
                usuarioEncontrado.setPassword(u.getPassword());
                usuarioEncontrado.setPhones(u.getPhones());
                usuarioEncontrado.setModified(now);
                usuarioEncontrado.isActive();
                //Guardamos el usuario
                usuarioRepository.save(usuarioEncontrado);
                //Llenamos el DTO que mostramos en la salida de este metodo
                usuarioCreatedDTO.setId(id);
                usuarioCreatedDTO.setName(usuarioEncontrado.getName());
                usuarioCreatedDTO.setEmail(usuarioEncontrado.getEmail());
                usuarioCreatedDTO.setCreated(usuarioEncontrado.getCreated());
                usuarioCreatedDTO.setModified(usuarioEncontrado.getModified());
                usuarioCreatedDTO.setLast_login(usuarioEncontrado.getLast_login());
                usuarioCreatedDTO.setToken(usuarioEncontrado.getToken());
                usuarioCreatedDTO.setActive(usuarioEncontrado.isActive());

            return usuarioCreatedDTO;
        }catch (Exception ex){
            throw new ApiError("No se pudo actualizar el usuario", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws ApiError {
        try {
            if (usuarioRepository.findById(id).isPresent()) {
                usuarioRepository.deleteById(id);
            } else {
                throw new ApiError("No se encontro el Id para eliminar el Usuario", HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            throw new ApiError("No se pudo eliminar el usuario", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
