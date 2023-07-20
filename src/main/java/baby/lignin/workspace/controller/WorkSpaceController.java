package baby.lignin.workspace.controller;

import baby.lignin.auth.config.TokenResolver;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkSpaceRequest;
import baby.lignin.workspace.model.request.WorkSpaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import baby.lignin.workspace.service.WorkspaceService;
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
import java.util.Optional;

@Tag(name = "Workspace", description = "WorkSpace API")
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/workspace")
public class WorkSpaceController {

    private final WorkspaceService workspaceService;
    // 워크 스페이스 목록 조회
    @Operation(summary = "WorkSpace 전체 조회", description = "전체 Workspace를 조회함")
    @GetMapping("/all")
    public ApiResponse<ApiResponse.SuccessBody<List<WorkSpaceResponse>>> findList() {
        return ApiResponseGenerator.success(workspaceService.findAllList(),HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace 조회", description = "내가 속한 Workspace를 조회함")
    @GetMapping()
    public ApiResponse<ApiResponse.SuccessBody<List<WorkSpaceResponse>>> findList(@RequestHeader("Token") String token) throws Exception {
        return ApiResponseGenerator.success(workspaceService.findMyList(token),HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 워크 스페이스 생성
    @Operation(summary = "WorkSpace 생성", description = "WorkSpace를 생성함")
    @PostMapping()
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceResponse>> create(@RequestHeader("Token") String token, @RequestBody WorkSpaceCreateRequest request) {
        return ApiResponseGenerator.success(workspaceService.create(token,request),HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 워크 스페이스 목록 조회

    @Operation(summary = "WorkSpace 삭제", description = "WorkSpace를 삭제함")
    @DeleteMapping()
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceResponse>> delete(WorkSpaceDeleteRequest request) throws Exception {
        return ApiResponseGenerator.success(workspaceService.delete(request.getWorkspaceId()),HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace 수정", description = "WorkSpace를 수정함")
    @PutMapping()
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceResponse>> update(@RequestBody WorkSpaceUpdateRequest request) throws Exception {
        return ApiResponseGenerator.success(workspaceService.update(request),HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace 탈퇴", description = "WorkSpace를 탈퇴함")
    @PostMapping("/unlink")
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceMemberResponse>> unlink(@RequestHeader("Token") String token, @RequestBody WorkSpaceDeleteRequest request) {
        return ApiResponseGenerator.success(workspaceService.unlink(token, request),HttpStatus.OK, MessageCode.SUCCESS);
    }

}
