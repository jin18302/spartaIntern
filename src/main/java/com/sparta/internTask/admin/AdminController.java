package com.sparta.internTask.admin;

import com.sparta.internTask.annotation.RequireRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RequireRole("ADMIN")
    @GetMapping("/roleCheck")
    public ResponseEntity<String> testMemberRole(){
        String result = adminService.checkUserRole();
        return ResponseEntity.ok(result);
    }
}
