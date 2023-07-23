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

    @GetMapping()
    public ApiResponse<ApiResponse.SuccessBody<List<BoardResponse>>> browse(@RequestHeader("Token") String token, BoardBrowseRequest request) {
        return ApiResponseGenerator.success(boardService.getBoards(token, request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @PostMapping
    public ApiResponse<ApiResponse.SuccessBody<BoardResponse>> add(@RequestPart(value = "file") MultipartFile multipartFile, @RequestHeader("Token") String token, @RequestPart(value="info") BoardAddRequest request) {
        return ApiResponseGenerator.success(boardService.generateBoard(multipartFile, token, request), HttpStatus.CREATED, MessageCode.RESOURCE_CREATED);
    }

    @PatchMapping
    public ApiResponse<ApiResponse.SuccessBody<BoardResponse>> edit(@RequestBody BoardEditRequest request) {
        return ApiResponseGenerator.success(boardService.changeBoardInfo(request), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @DeleteMapping
    public ApiResponse<ApiResponse.SuccessBody<Void>> delete(@RequestBody BoardDeleteRequest request) {
        boardService.deleteBoard(request);
        return ApiResponseGenerator.success(HttpStatus.OK, MessageCode.RESOURCE_DELETED);
    }
}