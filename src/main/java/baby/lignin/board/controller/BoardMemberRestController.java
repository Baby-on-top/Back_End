package baby.lignin.board.controller;

import baby.lignin.board.model.request.BoardMemberDeleteRequest;
import baby.lignin.board.model.request.BoardMemberInviteRequest;
import baby.lignin.board.model.response.BoardMemberResponse;
import baby.lignin.board.service.BoardMemberService;
import baby.lignin.board.support.ApiResponse;
import baby.lignin.board.support.ApiResponseGenerator;
import baby.lignin.board.support.MessageCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/invite")
public class BoardMemberRestController {
    private final BoardMemberService boardMemberService;

    @Operation(summary = "보드에 유저 초대(추가)하기", description = "유저에 대한 정보를 보드에 추가해준다..")
    @PostMapping
    public ApiResponse<ApiResponse.SuccessBody<BoardMemberResponse>> invite(@RequestBody BoardMemberInviteRequest request) {
        return ApiResponseGenerator.success(boardMemberService.inviteBoardMember(request), HttpStatus.OK, MessageCode.RESOURCE_CREATED);
    }

    @Operation(summary = "보드에 유저 탈퇴 하기", description = "현재 유저가 현재 보드에서 탈퇴한다.")
    @DeleteMapping
    public ApiResponse<ApiResponse.SuccessBody<Void>> delete(@RequestHeader("Token") String token, @RequestBody BoardMemberDeleteRequest request) {
        boardMemberService.deleteBoardMember(token, request);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
    }
}
