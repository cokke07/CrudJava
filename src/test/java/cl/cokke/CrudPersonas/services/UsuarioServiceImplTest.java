package cl.cokke.CrudPersonas.services;
import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.dto.UsuarioEncontradoDTO;
import cl.cokke.CrudPersonas.dto.UsuarioListDTO;
import cl.cokke.CrudPersonas.exceptions.ApiError;
import cl.cokke.CrudPersonas.model.Telefono;
import cl.cokke.CrudPersonas.model.Usuario;
import cl.cokke.CrudPersonas.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodos() throws ApiError {
        //Mock de telefonos
        Telefono telefono1 = new Telefono();
        telefono1.setCountrycode("9");
        telefono1.setCitycode("56");
        telefono1.setNumber("987654321");
        List<Telefono> listaTelefonoDto = new ArrayList<>();
        listaTelefonoDto.add(telefono1);
        String token = UUID.randomUUID().toString();

        // Mock de la lista de usuarios
        Usuario usuario1 = new Usuario(1L,"nombre completo","correo@mail.com","Clave1234",listaTelefonoDto, LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),token, true);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);

        //Creando un UsuarioListDto
        /*UsuarioListDTO usuarioListDTO = new UsuarioListDTO();
        usuarioListDTO.setId(usuario1.getId().toString());
        usuarioListDTO.setNombre(usuario1.getName());
        usuarioListDTO.setEmail(usuario1.getEmail());
        usuarioListDTO.setPhones(Utilidades.concatenaTelefono(listaTelefonoDto));*/

        // Mock del repositorio para devolver la lista de usuarios
        Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Ejecución del método a probar
        List<UsuarioListDTO> usuariosListDTO = usuarioService.buscarTodos();

        // Verificación de resultados
        Assertions.assertEquals(1, usuarios.size());
        Assertions.assertEquals(1, usuarios.get(0).getId());
        Assertions.assertEquals("nombre completo", usuarios.get(0).getName());
        Assertions.assertEquals("correo@mail.com", usuarios.get(0).getEmail());

    }

    @Test
    public void TestbuscarPorId() throws ApiError {
        //Mock de telefonos
        Telefono telefono1 = new Telefono();
        telefono1.setCountrycode("9");
        telefono1.setCitycode("56");
        telefono1.setNumber("987654321");
        List<Telefono> listaTelefono = new ArrayList<>();
        listaTelefono.add(telefono1);
        String token = UUID.randomUUID().toString();

        // Mock de la lista de usuarios
        Usuario usuario1 = new Usuario(1L,"nombre completo","correo@mail.com","Clave1234",listaTelefono, LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),token, true);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);

        // ID del usuario a buscar
        Long idUsuario = 1L;

        // Mock del repositorio para devolver un Optional vacío
        Mockito.when(usuarioRepository.findById(idUsuario)).thenReturn(Optional.of(usuario1));

        // Ejecución del método a probar y verificación de excepción
        UsuarioEncontradoDTO usuarioEncontradoDTO = usuarioService.buscarPorId(idUsuario);

        // Verificación de resultados
        Assertions.assertEquals(idUsuario.toString(), usuarioEncontradoDTO.getId());
        Assertions.assertEquals("nombre completo", usuarioEncontradoDTO.getNombre());
        Assertions.assertEquals("correo@mail.com", usuarioEncontradoDTO.getEmail());

    }

}