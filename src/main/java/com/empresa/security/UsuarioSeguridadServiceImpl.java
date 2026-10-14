package com.empresa.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresa.entity.Usuario;
import com.empresa.repository.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioSeguridadServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.info(">>> loadUserByUsername >>> {}", login);
        Optional<Usuario> optUsuario = usuarioRepository.findByLogin(login);
        if (optUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + login);
        }
        return UsuarioPrincipal.build(optUsuario.get());
    }
}
