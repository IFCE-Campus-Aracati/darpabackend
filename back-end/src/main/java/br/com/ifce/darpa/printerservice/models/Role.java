package br.com.ifce.darpa.printerservice.models;


import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_role")
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    public enum Authority {

        ROLE_ADMIN("ROLE_ADMIN"),
        ROLE_OPERATOR("ROLE_OPERATOR"),
        ROLE_USER("ROLE_USER");

        private final String authority;

        Authority(String authority) {
            this.authority = authority;
        }


        public String getAuthority() {
            return authority;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    public Role() {
    }

    public Role(Long id, Authority authority) {
        this.id = id;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonValue
    public String getAuthorityAsString() {
        return authority.getAuthority();
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority=" + authority +
                '}';
    }
}
