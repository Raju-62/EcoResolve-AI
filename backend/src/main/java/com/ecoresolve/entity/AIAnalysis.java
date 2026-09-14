package com.ecoresolve.entity;
import com.ecoresolve.enums.*; import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="ai_analysis") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AIAnalysis {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="complaint_id",nullable=false) private Complaint complaint;
 @Column(length=100) private String model;
 @Enumerated(EnumType.STRING) @Column(length=50) private ComplaintCategory category;
 @Column(length=100) private String subcategory;
 @Enumerated(EnumType.STRING) @Column(length=20) private Priority priority;
 @Column(columnDefinition="TEXT") private String summary;
 @Column(columnDefinition="TEXT") private String recommendation;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
 @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
}
