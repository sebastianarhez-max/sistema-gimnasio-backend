package com.empresa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.empresa.entity.Usuario;
import com.empresa.repository.UsuarioRepository;
import com.empresa.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String encripta(String password) {
        if (password.startsWith("$2")) {
            return password; // ya viene encriptada con BCrypt
        }
        return passwordEncoder.encode(password);
    }

    @Override
    public List<Usuario> listaTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Usuario> buscaPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Usuario inserta(Usuario obj) {
        if (obj.getPassword() == null || obj.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El password es obligatorio");
        }
        obj.setIdUsuario(null);
        obj.setPassword(encripta(obj.getPassword()));
        return repository.save(obj);
    }

    @Override
    public Usuario actualiza(Usuario obj) {
        if (obj.getPassword() == null || obj.getPassword().isBlank()) {
            // si no envía password, se conserva el actual
            repository.findById(obj.getIdUsuario()).ifPresent(actual -> obj.setPassword(actual.getPassword()));
        } else {
            obj.setPassword(encripta(obj.getPassword()));
        }
        return repository.save(obj);
    }

    @Override
    public void elimina(Integer id) {
        repository.findById(id).ifPresent(e -> {
            e.setEstado(false);
            repository.save(e);
        });
    }
}
