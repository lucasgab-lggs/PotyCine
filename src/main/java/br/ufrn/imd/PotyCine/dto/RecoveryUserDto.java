package br.ufrn.imd.PotyCine.dto;

import br.ufrn.imd.PotyCine.enums.Role;

public record RecoveryUserDto(
        Long id,
        String email,
        Role role
) {
}
