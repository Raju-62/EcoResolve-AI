package com.ecoresolve.entity;

import com.ecoresolve.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Table(name="complaints",indexes={@Index(name="idx_complaints_user",columnList="user_id"),@Index(name="idx_complaints_location",columnList="location_id"),@Index(name="idx_complaints_status",columnList="status"),@Index(name="idx_complaints_created_at",columnList="created_at")})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Complaint {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="user_id",nullable=false) private User user;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="location_id",nullable=false) private Location location;
 @Column(nullable=false,columnDefinition="TEXT") private String description;
 @Column(name="image_url",length=500) private String imageUrl;
 @Enumerated(EnumType.STRING) @Column(name="ai_category",length=50) private ComplaintCategory aiCategory;
 @Column(name="ai_subcategory",length=100) private String aiSubcategory;
 @Enumerated(EnumType.STRING) @Column(name="ai_priority",length=20) private Priority aiPriority;
 @Enumerated(EnumType.STRING) @Column(name="final_category",length=50) private ComplaintCategory finalCategory;
 @Enumerated(EnumType.STRING) @Column(name="final_priority",length=20) private Priority finalPriority;
 @Column(name="ai_summary",columnDefinition="TEXT") private String aiSummary;
 @Column(name="ai_recommendation",columnDefinition="TEXT") private String aiRecommendation;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private ComplaintStatus status=ComplaintStatus.PENDING;
 @Column(name="resolution_note",columnDefinition="TEXT") private String resolutionNote;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
 @Column(name="updated_at",nullable=false) private LocalDateTime updatedAt;
 @Column(name="resolved_at") private LocalDateTime resolvedAt;
 @PrePersist void prePersist(){var n=LocalDateTime.now(); if(createdAt==null)createdAt=n; if(updatedAt==null)updatedAt=n;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
