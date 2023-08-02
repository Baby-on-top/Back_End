package baby.lignin.board.controller;

import baby.lignin.board.model.request.BoardAddRequest;
import baby.lignin.board.model.request.BoardBrowseRequest;
import baby.lignin.board.model.request.BoardDeleteRequest;
import baby.lignin.board.model.request.BoardEditRequest;
import baby.lignin.board.model.response.BoardResponse;
import baby.lignin.board.service.BoardService;
import baby.lignin.board.support.ApiResponse;
import baby.lignin.board.support.ApiResponseGenerator;
import baby.lignin.board.support.MessageCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardRestController {

    private final BoardService boardService;

    @Operation(summary = "보드 정보 요청", description = "현재 유저가 있는 워크스페이스의 유저가 가지고 있는 보드 정보들 가져오기.")
    @GetMapping()
    public ApiResponse<ApiResponse.SuccessBody<List<BoardResponse>>> browse(@RequestHeader("Token") String token, BoardBrowseRequest request) {
        return ApiResponseGenerator.success(boardService.getBoards(token, request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "보드 만들기", description = "유저가 이미지와 보드 정보, 이미지를 넣어서 보드를 만든다.")
    @PostMapping
    public ApiResponse<ApiResponse.SuccessBody<BoardResponse>> add(@RequestHeader("Token") String token, @RequestPart(value = "file") MultipartFile multipartFile, @RequestPart(value="info") BoardAddRequest request) throws Exception {
        return ApiResponseGenerator.success(boardService.generateBoard(token, multipartFile, request), HttpStatus.CREATED, MessageCode.RESOURCE_CREATED);
    }

    @Operation(summary = "보드 수정하기", description = "유저가 이미지와 보드 정보, 이미지를 넣어서 보드를 수정한다.")
    @PatchMapping
    public ApiResponse<ApiResponse.SuccessBody<BoardResponse>> edit(@RequestPart(value = "file") MultipartFile multipartFile, @RequestPart(value="info") BoardEditRequest request) {
        return ApiResponseGenerator.success(boardService.changeBoardInfo(multipartFile, request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "보드 삭제하기", description = "보드를 선택해서 삭제하기.")
    @DeleteMapping
    public ApiResponse<ApiResponse.SuccessBody<Void>> delete(@RequestBody BoardDeleteRequest request) {
        boardService.deleteBoard(request);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
    }
}