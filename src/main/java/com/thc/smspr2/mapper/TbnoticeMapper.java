package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbnoticeDto;

import java.util.List;
import java.util.Map;

public interface TbnoticeMapper {
    Map<String, Object> detail(Map<String, Object> param);
    List<Map<String, Object>> list(Map<String, Object> param);
    TbnoticeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbnoticeDto.DetailResDto> list(TbnoticeDto.ListReqDto param);

    List<TbnoticeDto.DetailResDto> scrollList(TbnoticeDto.ScrollListReqDto param);
    List<TbnoticeDto.DetailResDto> pagedList(TbnoticeDto.PagedListReqDto param);
    int pagedListCount(TbnoticeDto.PagedListReqDto param);
}
