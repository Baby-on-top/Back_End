package baby.lignin.widget.controller;

import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.support.ApiResponse;
import baby.lignin.support.ApiResponseGenerator;
import baby.lignin.support.MessageCode;
import baby.lignin.widget.model.request.*;
import baby.lignin.widget.model.response.WidgetResponse;
import baby.lignin.widget.service.WidgetService;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.request.WorkspaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import baby.lignin.workspace.service.WorkspaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Tag(name = "Widget", description = "Widget API")
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/widget")
public class WidgetController {

    private final WidgetService widgetService;

    // 위젯 목록 조회
    @Operation(summary = "해당 room widget 조회", description = "widget 조회함")
    @GetMapping("/{roomId}")
    public ApiResponse<ApiResponse.SuccessBody<List<WidgetResponse>>> findList(@PathVariable Long roomId) {
        return ApiResponseGenerator.success(widgetService.findWidegetList(roomId), HttpStatus.OK, MessageCode.SUCCESS);
    }

    // 위젯 추가
    @Operation(summary = "widget 추가", description = "widget 추가함")
    @PostMapping()
    public ApiResponse<ApiResponse.SuccessBody<WidgetResponse>> addWidget(@RequestBody WidgetAddRequest request) {
        return ApiResponseGenerator.success(widgetService.addWidget(request), HttpStatus.OK, MessageCode.SUCCESS);
    }


    // 위젯 수정 (좌표, 타이틀, 썸네일)
    @Operation(summary = "widget 좌표 수정", description = "widget 좌표 수정")
    @PutMapping("/xy")
    public ApiResponse<ApiResponse.SuccessBody<WidgetResponse>> updateXYWidget(@RequestBody WidgetXYRequest request) throws Exception {
        return ApiResponseGenerator.success(widgetService.updateXyWidget(request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "widget 타이틀 수정", description = "widget 타이틀 수정")
    @PutMapping("/title")
    public ApiResponse<ApiResponse.SuccessBody<WidgetResponse>> updateTitleWidget(@RequestBody WidgetTitleRequest request) throws Exception {
        return ApiResponseGenerator.success(widgetService.updateTitleWidget(request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "widget 썸네일 수정", description = "widget 썸네일 수정")
    @PutMapping("/image")
    public ApiResponse<ApiResponse.SuccessBody<WidgetResponse>> updateImageWidget(@RequestBody WidgetImageRequest request) throws Exception {
        return ApiResponseGenerator.success(widgetService.updateImageWidget(request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "widget 삭제", description = "widget 삭제")
    @DeleteMapping()
    public ApiResponse<ApiResponse.SuccessBody<WidgetResponse>> deleteWidget(@RequestBody WidgetDeleteRequest request) throws Exception {
        return ApiResponseGenerator.success(widgetService.deleteWidget(request), HttpStatus.OK, MessageCode.SUCCESS);
    }


}
