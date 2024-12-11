package br.ufrn.imd.PotyCine.dto;

import br.ufrn.imd.PotyCine.enums.Role;

public record CreateUserDto (
        String name,
        String email,
        String password,
        Role role
){
}
