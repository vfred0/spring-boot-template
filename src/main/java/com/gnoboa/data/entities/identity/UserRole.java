//
//package com.gnoboa.data.entities.identity;
//
//import com.gnoboa.data.enums.Role;
//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Set;
//import java.util.UUID;
//
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(schema="identity", name = "roles")
//public class UserRole implements GrantedAuthority {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    UUID id;
//
//    @Convert(converter = Role.RoleConverter.class)
//    Role name;
//
//    @Override
//    public String getAuthority() {
//        return this.name.name();
//    }
//
//    @OneToMany(mappedBy = "role")
//    Set<UserAccount> users;
//}