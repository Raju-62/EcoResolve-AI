package com.ecoresolve.entity;
import com.ecoresolve.enums.ComplaintStatus; import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="complaint_history") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ComplaintHistory {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="complaint_id",nullable=false) private Complaint complaint;
 @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="changed_by") private User changedBy;
 @Enumerated(EnumType.STRING) @Column(name="old_status",length=30) private ComplaintStatus oldStatus;
 @Enumerated(EnumType.STRING) @Column(name="new_status",nullable=false,length=30) private ComplaintStatus newStatus;
 @Column(columnDefinition="TEXT") private String note;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
 @PrePersist void prePersist(){if(createdAt==null)createdAt=LocalDateTime.now();}
}
