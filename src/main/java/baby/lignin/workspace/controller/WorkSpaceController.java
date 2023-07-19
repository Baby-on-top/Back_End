package baby.lignin.workspace.controller;

import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceRequest;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import baby.lignin.workspace.service.WorkspaceService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import baby.lignin.support.ApiResponse;
import baby.lignin.support.ApiResponseGenerator;
import baby.lignin.support.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Example API", description = "Swagger Test  API")
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/workspace")
public class WorkSpaceController {

    private final WorkspaceService workspaceService;
    // 워크 스페이스 목록 조회
    @Operation(summary = "WorkSpace 조회", description = "내가 속한 Workspace를 조회함")
    @GetMapping()
    public ApiResponse<ApiResponse.SuccessBody<List<WorkSpaceResponse>>> findList(WorkSpaceRequest request) {
        return ApiResponseGenerator.success(workspaceService.findList(request.getMemberId()),HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 워크 스페이스 생성
    @Operation(summary = "WorkSpace 생성", description = "WorkSpace를 생성함")
    @PostMapping()
    public ApiResponse<ApiResponse.SuccessBody<Void>> create(@RequestBody WorkSpaceCreateRequest request) {
        System.out.println(request.getMemberId());
        workspaceService.create(request);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 워크 스페이스 목록 조회
    @Hidden
    @GetMapping("/hidden")
    public String hidden() {
        return "무시되는 API";
    }
}
