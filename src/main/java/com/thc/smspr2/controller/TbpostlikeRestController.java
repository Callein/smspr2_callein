package com.thc.smspr2.controller;

import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.service.TbpostlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "1-1_3. 게시글 좋아요 API 안내",
        description = "게시글 좋아요 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpostlike")
@RestController
public class TbpostlikeRestController {
    private final TbpostlikeService tbpostlikeService;
    public TbpostlikeRestController(TbpostlikeService tbpostlikeService) {
        this.tbpostlikeService = tbpostlikeService;
    }

    @Operation(summary = "게시글 좋아요 토글",
            description = "게시글 좋아요 토글 컨트롤러 <br />"
                    + "@param TbpostlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbpostlikeDto.ToggleResDto> toggleLike(@Valid @RequestBody TbpostlikeDto.ToggleReqDto param) {
        TbpostlikeDto.ToggleResDto response = tbpostlikeService.toggleLike(param);
        return ResponseEntity.ok(response);
    }
}
