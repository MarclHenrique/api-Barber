package com.barber.apiBarber.Dto;

import com.barber.apiBarber.Model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private Role role;

    private Boolean atendeDomicilio;

    private String servicos;

    private String contato;

}
