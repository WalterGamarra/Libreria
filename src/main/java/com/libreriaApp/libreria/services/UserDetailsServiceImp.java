package com.libreriaApp.libreria.services;

import com.libreriaApp.libreria.DTOs.AuthLoginRequestDTO;
import com.libreriaApp.libreria.DTOs.AuthResponseDTO;
import com.libreriaApp.libreria.models.usuarios_seguridad.UserSec;
import com.libreriaApp.libreria.repositories.IUsuarioRepository;
import com.libreriaApp.libreria.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserSec usuarioSec = usuarioRepo.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no fue encontrado"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // 1️⃣ Cargar ROLES con prefijo ROLE_
        usuarioSec.getRolesList()
                .forEach(rol ->
                        authorityList.add(new SimpleGrantedAuthority("ROLE_" + rol.getRole()))
                );

        usuarioSec.getRolesList().stream()
                .flatMap(rol -> rol.getPermisos().stream())
                .forEach(permiso ->
                        authorityList.add(new SimpleGrantedAuthority(permiso.getNombre()))
                );

        return new User(
                usuarioSec.getUsername(),
                usuarioSec.getPassword(),
                usuarioSec.isEnabled(),
                usuarioSec.isAccountNotExpired(),
                usuarioSec.isCredentialNotExpired(),
                usuarioSec.isAccountNotLocked(),
                authorityList);
    }

    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequest){


        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesToken = jwtUtils.createToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "Login successfull", accesToken, true);

        return authResponseDTO;
    }

    public Authentication authenticate (String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw  new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());

    }


}