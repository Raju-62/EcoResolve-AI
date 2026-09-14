package com.ecoresolve.repository;
import com.ecoresolve.entity.Complaint; import com.ecoresolve.enums.*; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param;
public interface ComplaintRepository extends JpaRepository<Complaint,Long>{ Page<Complaint> findByUserId(Long userId,Pageable pageable); long countByStatus(ComplaintStatus s); long countByFinalPriority(Priority p); long countByAiPriority(Priority p); }
