package cl.cokke.CrudPersonas.controller;

import cl.cokke.CrudPersonas.dto.UsuarioCreatedDTO;
import cl.cokke.CrudPersonas.model.Usuario;
import cl.cokke.CrudPersonas.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("usuarios")
    public ResponseEntity<List<Usuario>> listarusuarios(){
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios = usuarioService.buscarTodos();

        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);

    }

    @PostMapping("crear")
    public ResponseEntity<UsuarioCreatedDTO> crearUsuario(@RequestBody Usuario u){

        UsuarioCreatedDTO nuevoUsuario = usuarioService.crearUsuario(u);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }
}
