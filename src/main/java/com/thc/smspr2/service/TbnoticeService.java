package com.thc.smspr2.service;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbnoticeDto;

import java.util.List;

public interface TbnoticeService {
    /**/
    TbnoticeDto.CreateResDto create(TbnoticeDto.CreateReqDto param);
    TbnoticeDto.CreateResDto update(TbnoticeDto.UpdateReqDto param);
    TbnoticeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbnoticeDto.DetailResDto> list(TbnoticeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbnoticeDto.PagedListReqDto param);
    List<TbnoticeDto.DetailResDto> scrollList(TbnoticeDto.ScrollListReqDto param);
}
