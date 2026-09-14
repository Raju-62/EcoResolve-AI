package com.ecoresolve.controller;
import com.ecoresolve.dto.ComplaintDtos.*; import com.ecoresolve.service.ComplaintService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.data.domain.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/admin") @RequiredArgsConstructor public class AdminController { private final ComplaintService service;
 @GetMapping("/dashboard") public DashboardStats dashboard(){return service.dashboard();}
 @GetMapping("/complaints") public Page<ComplaintView> all(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="20")int size){return service.all(PageRequest.of(page,size,Sort.by("createdAt").descending()));}
 @GetMapping("/complaints/{id}") public ComplaintView get(@PathVariable Long id){return service.adminGet(id);}
 @PutMapping("/complaints/{id}") public ComplaintView update(Authentication a,@PathVariable Long id,@Valid @RequestBody AdminUpdateRequest r){return service.update(id,r,a.getName());}
 @GetMapping("/analytics") public Analytics analytics(){return service.analytics();}
 @PostMapping("/insights") public InsightResponse insights(){return service.insights();}
}
