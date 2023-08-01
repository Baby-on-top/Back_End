package baby.lignin.workspace.controller;


import baby.lignin.support.ApiResponse;
import baby.lignin.support.ApiResponseGenerator;
import baby.lignin.support.MessageCode;
import baby.lignin.workspace.model.request.UrlLongRequest;
import baby.lignin.workspace.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "UrlShortener", description = "URL 축소")
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class InviteUrlController {

    private final UrlService urlService;


    @Operation(summary = "WorkSpace shortener 생성", description = "WorkSpace 초대 링크 생성")
    @PostMapping("url")
    public ApiResponse<ApiResponse.SuccessBody<String>> url(@RequestBody UrlLongRequest shortUrl) {
        return ApiResponseGenerator.success(urlService.convertToShortUrl(shortUrl), HttpStatus.OK, MessageCode.SUCCESS);
    }

    @Operation(summary = "short Url 접속")
    @GetMapping("{shortUrl}")
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        System.out.println("here");
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }


}
