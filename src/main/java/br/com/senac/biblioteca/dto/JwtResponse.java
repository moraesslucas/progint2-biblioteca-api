package br.com.senac.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class JwtResponse {
    private String username;
    private String nome;
    @JsonProperty("access_token")
    private String accessToken;
    private final static String type = "Bearer";
}
