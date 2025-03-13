package com.gnoboa.data.entities.identity;

import com.gnoboa.data.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(schema = "identity", name = "user_accounts")
public class UserAccount implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Size(max = 25)
    @Column(nullable = false, length = 25)
    String username;

    String password;

    @Convert(converter = Role.RoleConverter.class)
    Role role;

    @Override
    public String getAuthority() {
        return this.role.name();
    }

}
