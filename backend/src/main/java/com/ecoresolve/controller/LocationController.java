package com.ecoresolve.controller;
import com.ecoresolve.entity.Location; import com.ecoresolve.repository.LocationRepository; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/locations") @RequiredArgsConstructor public class LocationController { private final LocationRepository repo; @GetMapping public List<Location> all(){return repo.findAll();} }
