package com.ecoresolve.controller;
import com.ecoresolve.dto.ComplaintDtos.*; import com.ecoresolve.service.ComplaintService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.data.domain.*; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/complaints") @RequiredArgsConstructor public class ComplaintController { private final ComplaintService service;
 @GetMapping public Page<ComplaintView> mine(Authentication a,@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="10")int size){return service.mine(a.getName(),PageRequest.of(page,size,Sort.by("createdAt").descending()));}
 @PostMapping public ComplaintView create(Authentication a,@Valid @RequestBody CreateComplaintRequest r){return service.create(a.getName(),r);}
 @GetMapping("/{id}") public ComplaintView get(Authentication a,@PathVariable Long id){return service.getMine(a.getName(),id);}
}
