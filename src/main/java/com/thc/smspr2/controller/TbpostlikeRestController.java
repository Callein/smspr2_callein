package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.service.TbpostlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbpostlikeDto.CreateResDto> toggle(@Valid @RequestBody TbpostlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostlikeService.toggle(param));
    }

    /**/

    @Operation(summary = "게시글 좋아요 생성",
            description = "게시글 좋아요 생성 컨트롤러 <br />"
                    + "@param TbpostlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbpostlikeDto.CreateResDto> create(@Valid @RequestBody TbpostlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostlikeService.create(param));
    }

    @Operation(summary = "게시글 좋아요 수정",
            description = "게시글 좋아요 수정 컨트롤러 <br />"
                    + "@param TbpostlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbpostlikeDto.CreateResDto> update(@Valid @RequestBody TbpostlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostlikeService.update(param));
    }

    @Operation(summary = "게시글 좋아요 삭제",
            description = "게시글 좋아요 삭제 컨트롤러 <br />"
                    + "@param TbpostlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbpostlikeDto.CreateResDto> delete(@Valid @RequestBody TbpostlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostlikeService.delete(param));
    }

    @Operation(summary = "게시글 좋아요 상세 조회",
            description = "게시글 좋아요 상세 조회 컨트롤러 <br />"
                    + "@param TbpostlikeDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbpostlikeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostlikeService.detail(param));
    }
    @Operation(summary = "게시글 좋아요 목록 전체 조회",
            description = "게시글 좋아요 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbpostlikeDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbpostlikeDto.DetailResDto>> list(@Valid TbpostlikeDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostlikeService.list(param));
    }

    @Operation(summary = "게시글 좋아요 목록 페이지 조회",
            description = "게시글 좋아요 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbpostlikeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbpostlikeDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostlikeService.pagedList(param));
    }
    @Operation(summary = "게시글 좋아요 목록 스크롤 조회",
            description = "게시글 좋아요 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbpostlikeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbpostlikeDto.DetailResDto>> mlist(@Valid TbpostlikeDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostlikeService.scrollList(param));
    }

}
