package com.empresa.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.empresa.entity.Usuario;

/** Todas las cuentas de `usuario` son Administrador: un único rol ROLE_ADMIN. */
public class UsuarioPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final Integer idUsuario;
    private final String login;
    private final String password;
    private final String nombreCompleto;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(Integer idUsuario, String login, String password, String nombreCompleto,
            boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario u) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new UsuarioPrincipal(u.getIdUsuario(), u.getLogin(), u.getPassword(),
                u.getNombres() + " " + u.getApellidos(), Boolean.TRUE.equals(u.getEstado()), authorities);
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
