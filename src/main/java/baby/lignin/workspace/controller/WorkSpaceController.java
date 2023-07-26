package baby.lignin.workspace.controller;

import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.board.model.request.BoardEditRequest;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkSpaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import baby.lignin.workspace.service.WorkspaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import baby.lignin.support.ApiResponse;
import baby.lignin.support.ApiResponseGenerator;
import baby.lignin.support.MessageCode;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        return ApiResponseGenerator.success(workspaceService.findAllList(), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace 조회", description = "내가 속한 Workspace를 조회함")
    @GetMapping()
    public ApiResponse<ApiResponse.SuccessBody<List<WorkSpaceResponse>>> findList(@RequestHeader("Token") String token) throws Exception {
        return ApiResponseGenerator.success(workspaceService.findMyList(token), HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 워크 스페이스 생성
    @Operation(summary = "WorkSpace 생성", description = "WorkSpace를 생성함")
    @PostMapping()
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceResponse>> create(@RequestHeader("Token") String token, @RequestPart(value = "file") MultipartFile multipartFile, @RequestPart(value = "info") WorkSpaceCreateRequest request) {
        return ApiResponseGenerator.success(workspaceService.create(token, multipartFile, request), HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 워크 스페이스 목록 조회

    @Operation(summary = "WorkSpace 삭제", description = "만든 WorkSpace를 삭제함")
    @DeleteMapping()
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceResponse>> delete(WorkSpaceDeleteRequest request) throws Exception {
        return ApiResponseGenerator.success(workspaceService.delete(request.getWorkspaceId()), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace 수정", description = "WorkSpace를 수정함")
    @PutMapping()
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceResponse>> update(@RequestPart(value = "file") MultipartFile multipartFile, @RequestPart(value = "info") WorkSpaceUpdateRequest request) throws Exception {
        return ApiResponseGenerator.success(workspaceService.update(request, multipartFile), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace 탈퇴", description = "가입한 WorkSpace를 탈퇴함")
    @PostMapping("/unlink")
    public ApiResponse<ApiResponse.SuccessBody<WorkSpaceMemberResponse>> unlink(@RequestHeader("Token") String token, @RequestBody WorkSpaceDeleteRequest request) {
        return ApiResponseGenerator.success(workspaceService.unlink(token, request), HttpStatus.OK, MessageCode.SUCCESS);
    }


    @Operation(summary = "WorkSpace 초대링크", description = "WorkSpace 초대링크")
    @GetMapping("/invite/{workspaceId}")
    public String inviteLink(@PathVariable Long workspaceId, HttpServletResponse response) {
        Cookie cookie = new Cookie("inviteWorkspaceId", Long.toString(workspaceId));
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setMaxAge(5 * 60);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return "redirect:http://localhost:3000/invite-check";
    }

    @Operation(summary = "WorkSpace 초대", description = "WorkSpace 초대")
    @GetMapping("/invite")
    public ApiResponse<ApiResponse.SuccessBody<Void>> invite(@RequestHeader("Token") String token, Long workspaceId, HttpServletResponse response) {
        WorkSpaceMemberResponse memberResponse = workspaceService.invite(token, workspaceId);
        Cookie cookie = new Cookie("inviteWorkspaceId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "WorkSpace에 속한 사용자 정보", description = "WorkSpace에 속한 사용자들을 조회")
    @GetMapping("/join")
    public ApiResponse<ApiResponse.SuccessBody<List<MemberResponse>>> invite(Long workspaceId) throws Exception {
        return ApiResponseGenerator.success(workspaceService.memberList(workspaceId), HttpStatus.OK, MessageCode.SUCCESS);
    }


}
