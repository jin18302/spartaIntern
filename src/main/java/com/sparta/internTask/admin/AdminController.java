package com.sparta.internTask.admin;

import com.sparta.internTask.annotation.RequireRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "ADMIN_API_GROUP", description = "권한 체크 API를 포함하고 있습니다")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RequireRole("ADMIN")
    @GetMapping("/roleCheck")
    @Operation(summary = "권한 체크", description = "필요한 권한을 체크합니다. Bearer를 제외한 토큰을 사용해주세요", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "권한 체크 성공")
    public ResponseEntity<String> testMemberRole(){
        String result = adminService.checkUserRole();
        return ResponseEntity.ok(result);
    }
}
