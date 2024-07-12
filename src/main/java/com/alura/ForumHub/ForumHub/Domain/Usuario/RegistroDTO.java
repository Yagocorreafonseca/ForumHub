package com.alura.ForumHub.ForumHub.Domain.Usuario;

public record RegistroDTO(String email, String nome) {
    public RegistroDTO(String email) {
        this(email, null);
    }

    public RegistroDTO(Usuario newUser){
        this(newUser.getEmail());
    }
}
