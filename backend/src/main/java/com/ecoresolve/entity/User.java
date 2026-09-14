package com.ecoresolve.entity;

import com.ecoresolve.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String name;
 @Column(nullable=false,unique=true,length=255) private String email;
 @Column(name="password_hash",nullable=false) private String passwordHash;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Role role=Role.STUDENT;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
 @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
}
