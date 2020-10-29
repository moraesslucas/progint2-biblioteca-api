package br.com.senac.biblioteca.security;

import br.com.senac.biblioteca.dto.LoginRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

    public static LoginRequest parseAuthorizationHeader(String authorization) {
        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            // credentials = username:password
            final String[] values = credentials.split(":", 2);
            return LoginRequest.builder().username(values[0]).password(values[1]).build();
        }

        return LoginRequest.builder().build();
    }

}
