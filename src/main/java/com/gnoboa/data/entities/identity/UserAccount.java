package com.gnoboa.data.entities.identity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Size(max = 11)
    @Column(nullable = false, length = 11)
    String dni;

    String otp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    UserRole role;
}
