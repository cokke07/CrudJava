package cl.cokke.CrudPersonas.services;

import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.exceptions.ApiError;
import cl.cokke.CrudPersonas.model.Usuario;
import cl.cokke.CrudPersonas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioCreatedDTO> buscarPorId(Long id) {
        return Optional.empty();
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
            u.setCreated(now);
            u.setModified(now);
            u.setLast_login(now);
            u.setToken(UUID.randomUUID().toString());
            u.setActive(true);

            usuarioRepository.save(u);

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
    public UsuarioCreatedDTO editarUsuario(Usuario u) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {

    }
}
