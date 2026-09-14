package com.ecoresolve.dto;

import com.ecoresolve.enums.*; import jakarta.validation.constraints.*; import java.time.LocalDateTime; import java.util.List;

public final class ComplaintDtos {
 private ComplaintDtos(){}
 public record CreateComplaintRequest(@NotBlank @Size(max=5000) String description,@NotNull Long locationId,String imageUrl){}
 public record AdminUpdateRequest(ComplaintCategory category,Priority priority,@NotNull ComplaintStatus status,String resolutionNote){}
 public record AIResult(ComplaintCategory category,String subcategory,Priority priority,String summary,String recommendation,String model){}
 public record ComplaintView(Long id,String description,String location,ComplaintCategory aiCategory,String aiSubcategory,Priority aiPriority,ComplaintCategory finalCategory,Priority finalPriority,String aiSummary,String aiRecommendation,ComplaintStatus status,String resolutionNote,LocalDateTime createdAt,LocalDateTime updatedAt,LocalDateTime resolvedAt, List<HistoryView> history){}
 public record HistoryView(ComplaintStatus oldStatus,ComplaintStatus newStatus,String changedBy,String note,LocalDateTime createdAt){}
 public record DashboardStats(long total,long pending,long inProgress,long resolved,long highPriority){}
 public record Analytics(java.util.Map<String,Long> categoryDistribution,java.util.Map<String,Long> priorityDistribution,java.util.Map<String,Long> statusDistribution){}
 public record Insight(String title,String description,String recommendation){}
 public record InsightResponse(List<Insight> insights,LocalDateTime generatedAt){}
}
