package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbnoticeDto;
import com.thc.smspr2.service.TbnoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-2. 알림 API 안내",
        description = "알림 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbnotice")
@RestController
public class TbnoticeRestController {

    private final TbnoticeService tbnoticeService;
    public TbnoticeRestController(TbnoticeService tbnoticeService) {
        this.tbnoticeService = tbnoticeService;
    }

    @Operation(summary = "알림 생성",
            description = "알림 생성 컨트롤러 <br />"
                    + "@param TbnoticeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbnoticeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbnoticeDto.CreateResDto> create(@Valid @RequestBody TbnoticeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbnoticeService.create(param));
    }


    @Operation(summary = "알림 수정",
            description = "알림 수정 컨트롤러 <br />"
                    + "@param TbnoticeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbnoticeDto.CreateResDto> update(@Valid @RequestBody TbnoticeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.update(param));
    }

    @Operation(summary = "알림 상세 조회",
            description = "알림 상세 조회 컨트롤러 <br />"
                    + "@param TbnoticeDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbnoticeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.detail(param));
    }
    @Operation(summary = "알림 목록 전체 조회",
            description = "알림 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbnoticeDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbnoticeDto.DetailResDto>> list(@Valid TbnoticeDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.list(param));
    }

    @Operation(summary = "알림 목록 페이지 조회",
            description = "알림 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbnoticeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbnoticeDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.pagedList(param));
    }
    @Operation(summary = "알림 목록 스크롤 조회",
            description = "알림 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbnoticeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbnoticeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbnoticeDto.DetailResDto>> mlist(@Valid TbnoticeDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbnoticeService.scrollList(param));
    }

}
