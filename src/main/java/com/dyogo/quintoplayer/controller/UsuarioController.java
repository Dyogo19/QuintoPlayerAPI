package com.dyogo.quintoplayer.controller;


import com.dyogo.quintoplayer.dto.UsuarioCreateDTO;
import com.dyogo.quintoplayer.dto.UsuarioDTO;
import com.dyogo.quintoplayer.dto.UsuarioUpdateDTO;
import com.dyogo.quintoplayer.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Busca todos os usuários.
     *
     * @return ResponseEntity contendo uma lista de UsuarioDTO com os detalhes de todos os usuários.
     */
    @GetMapping
    public ResponseEntity<Iterable<UsuarioDTO>> buscarTodosOsUsuarios() {
        return ResponseEntity.ok(this.usuarioService.buscarTodosOsUsuarios());
    }

    /**
     * Cria um novo usuário.
     *
     * @param usuarioCreateDTO Dados do usuário a ser criado.
     * @return ResponseEntity contendo uma instância de UsuarioDTO com os detalhes do usuário recém-criado.
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return ResponseEntity.ok(this.usuarioService.criarUsuario(usuarioCreateDTO));
    }

    /**
     * Deleta um usuário pelo ID.
     *
     * @param id O ID do usuário a ser deletado.
     * @return ResponseEntity indicando que a solicitação foi aceita.
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Integer id) {
        usuarioService.deletarPorId(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Edita um usuário pelo ID.
     *
     * @param id               O ID do usuário a ser editado.
     * @param usuarioUpdateDTO Dados atualizados do usuário.
     * @return ResponseEntity indicando que a solicitação foi processada com sucesso.
     * @throws Exception Se ocorrer um erro durante a edição do usuário.
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> editaUsuario(@PathVariable("id") Integer id,
                                             @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) throws Exception {
        usuarioService.editaUsuarioPorId(id, usuarioUpdateDTO);
        return ResponseEntity.noContent().build();
    }
}
