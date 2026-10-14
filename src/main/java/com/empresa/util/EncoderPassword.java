package com.empresa.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/** Utilitario para generar el hash BCrypt de una contraseña (ejecutar como Java Application). */
public class EncoderPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "1234";
        System.out.println("Password is         : " + password);
        System.out.println("Encoded Password is : " + encoder.encode(password));
    }
}
