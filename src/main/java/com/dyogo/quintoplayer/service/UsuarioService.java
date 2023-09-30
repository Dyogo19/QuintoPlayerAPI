package com.dyogo.quintoplayer.service;

import com.dyogo.quintoplayer.dto.UsuarioCreateDTO;
import com.dyogo.quintoplayer.dto.UsuarioDTO;
import com.dyogo.quintoplayer.dto.UsuarioUpdateDTO;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final LoginService loginService;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper, LoginService loginService) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
        this.loginService = loginService;
    }

    /**
     * Cria um novo usuário.
     *
     * @param usuarioCreateDTO Dados do usuário a ser criado.
     * @return Uma instância de UsuarioDTO contendo os detalhes do usuário recém-criado.
     */
    public UsuarioDTO criarUsuario(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = objectMapper.convertValue(usuarioCreateDTO, Usuario.class);
        UsuarioDTO usuarioDTO = objectMapper.convertValue(usuarioRepository.save(usuario), UsuarioDTO.class);

        loginService.criarLogin(usuario.getEmail(), usuario.getSenha(), usuario.getNome(), usuario.getNumeroDeCelular());

        return usuarioDTO;
    }

    /**
     * Busca todos os usuários.
     *
     * @return Uma lista de UsuarioDTO contendo os detalhes de todos os usuários.
     */
    public Iterable<UsuarioDTO> buscarTodosOsUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> objectMapper.convertValue(usuario, UsuarioDTO.class))
                .toList();
    }

    /**
     * Deleta um usuário pelo ‘ID’.
     *
     * @param id O ID do usuário a ser deletado.
     */
    public void deletarPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }

    /**
     * Edita um usuário pelo ID.
     *
     * @param id               O ID do usuário a ser editado.
     * @param usuarioUpdateDTO Dados atualizados do usuário.
     * @throws Exception Se o usuário não for encontrado.
     */
    public void editaUsuarioPorId(Integer id, UsuarioUpdateDTO usuarioUpdateDTO) throws Exception {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado!"));
        usuario.setId(id);
        usuario.setNome(usuarioUpdateDTO.nome());
        usuario.setEmail(usuarioUpdateDTO.email());
        usuario.setSenha(usuarioUpdateDTO.senha());
        usuario.setNumeroDeCelular(usuarioUpdateDTO.numeroDeCelular());
        Usuario usuarioRetorno = usuarioRepository.save(usuario);
    }


    /**
     * Busca um usuário pelo ID.
     *
     * @param idUsuario O ID do usuário a ser buscado.
     * @return Uma instância de Usuario representando o usuário encontrado.
     * @throws Exception Se o usuário não for encontrado.
     */
    public Usuario buscarPorId(Integer idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new Exception("Usuario não encontrado!"));
        return usuario;
    }


    /**
     * Busca um usuário pelo email.
     *
     * @param email O email do usuário a ser buscado.
     * @return Uma instância de Usuario representando o usuário encontrado.
     * @throws Exception Se o usuário não for encontrado.
     */
    public Usuario buscarPorEmail(String email) throws Exception {

        return usuarioRepository.findByEmail(email).orElseThrow(() -> new Exception("Email não encontrado!"));

    }

}
