package cl.cokke.CrudPersonas.controller;

import cl.cokke.CrudPersonas.dto.ApiErrorDTO;
import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.dto.UsuarioEncontradoDTO;
import cl.cokke.CrudPersonas.dto.UsuarioListDTO;
import cl.cokke.CrudPersonas.exceptions.ApiError;
import cl.cokke.CrudPersonas.exceptions.CustomRestExceptionHandler;
import cl.cokke.CrudPersonas.model.Usuario;
import cl.cokke.CrudPersonas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioListDTO>> listarUsuarios() throws ApiError {
        List<UsuarioListDTO> listaUsuarios = new ArrayList<>();
        try {
            listaUsuarios = usuarioService.buscarTodos();
            return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
        }catch (RuntimeException ex){
            throw new ApiError("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioEncontradoDTO> buscarUsuarioPorId(@PathVariable Long id) throws ApiError {
        UsuarioEncontradoDTO usuarioEncontradoDTO = new UsuarioEncontradoDTO();
        try {
            usuarioEncontradoDTO = usuarioService.buscarPorId(id);
            return new ResponseEntity<>(usuarioEncontradoDTO, HttpStatus.OK);
        }catch (RuntimeException ex){
            throw new ApiError("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioCreatedDTO> crearUsuario(@RequestBody Usuario u) throws ApiError{
        UsuarioCreatedDTO nuevoUsuario = new UsuarioCreatedDTO();
        try{
            nuevoUsuario = usuarioService.crearUsuario(u);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            throw new ApiError("Error interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
