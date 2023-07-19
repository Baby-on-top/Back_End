package baby.lignin.board.controller;

import baby.lignin.board.model.request.BoardInviteRequest;
import baby.lignin.board.model.response.BoardMemberResponse;
import baby.lignin.board.service.BoardMemberService;
import baby.lignin.board.support.ApiResponse;
import baby.lignin.board.support.ApiResponseGenerator;
import baby.lignin.board.support.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board/invite")
public class BoardMemberRestController {
    private final BoardMemberService boardMemberService;
    @PostMapping
    public ApiResponse<ApiResponse.SuccessBody<BoardMemberResponse>> invite(@RequestBody BoardInviteRequest request) {
        return ApiResponseGenerator.success(boardMemberService.inviteBoard(request), HttpStatus.OK, MessageCode.RESOURCE_CREATED);
    }
}
