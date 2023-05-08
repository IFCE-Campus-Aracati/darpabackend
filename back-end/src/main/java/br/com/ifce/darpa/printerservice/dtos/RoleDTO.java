package br.com.ifce.darpa.printerservice.dtos;

import br.com.ifce.darpa.printerservice.models.Role;

public record RoleDTO(
        Long id,
        String authority) {

        public RoleDTO(Role role) {
            this(role.getId(), role.getAuthorityAsString());
        }
}
