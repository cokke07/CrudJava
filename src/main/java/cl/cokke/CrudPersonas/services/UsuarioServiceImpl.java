package cl.cokke.CrudPersonas.services;

import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.model.Usuario;
import cl.cokke.CrudPersonas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioCreatedDTO> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public UsuarioCreatedDTO crearUsuario(Usuario u) {
        LocalDateTime now = LocalDateTime.now();
        UsuarioCreatedDTO usuarioCreatedDTO= new UsuarioCreatedDTO();

        u.setCreated(now);
        u.setModified(now);
        u.setLast_login(now);
        u.setToken(UUID.randomUUID().toString());
        u.setActive(true);

        usuarioRepository.save(u);

        usuarioCreatedDTO.setId(u.getId());
        usuarioCreatedDTO.setCreated(u.getCreated());
        usuarioCreatedDTO.setModified(u.getModified());
        usuarioCreatedDTO.setLast_login(u.getLast_login());
        usuarioCreatedDTO.setToken(u.getToken());
        usuarioCreatedDTO.setActive(u.isActive());


        return usuarioCreatedDTO;
    }

    @Override
    public UsuarioCreatedDTO editarUsuario(Usuario u) {
        return null;
    }

    @Override
    public void eliminarUsuario(Long id) {

    }
}
