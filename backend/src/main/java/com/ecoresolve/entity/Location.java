package com.ecoresolve.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="locations") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Location {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=150) private String name;
 @Column(length=100) private String building;
 @Column(length=50) private String floor;
}
