package com.tfg.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String avatar;

    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void setAvatarIfNull() {
        if (this.avatar == null || this.avatar.isEmpty()) {
            int randomId = (int) (Math.random() * 70) + 1; // pravatar.io tiene imágenes de 1 a 70
            this.avatar = "https://i.pravatar.cc/150?img=" + randomId;
        }
    }
}
