package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbnotice;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbnoticeDto;
import com.thc.smspr2.mapper.TbnoticeMapper;
import com.thc.smspr2.repository.TbnoticeRepository;
import com.thc.smspr2.service.TbnoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbnoticeServiceImpl implements TbnoticeService {

    private final TbnoticeRepository tbnoticeRepository;
    private final TbnoticeMapper tbnoticeMapper;
    public TbnoticeServiceImpl(
            TbnoticeRepository tbnoticeRepository,
            TbnoticeMapper tbnoticeMapper
    ) {
        this.tbnoticeRepository = tbnoticeRepository;
        this.tbnoticeMapper = tbnoticeMapper;
    }


    @Override
    public TbnoticeDto.CreateResDto create(TbnoticeDto.CreateReqDto param){
        return tbnoticeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbnoticeDto.CreateResDto update(TbnoticeDto.UpdateReqDto param){
        Tbnotice tbnotice = tbnoticeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbnotice.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbnotice.setProcess(param.getProcess());
        }

        if(param.getTitle() != null){
            tbnotice.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbnotice.setContent(param.getContent());
        }
        tbnoticeRepository.save(tbnotice);
        return tbnotice.toCreateResDto();
    }

//    @Override
//    public Map<String, Object> detail(Map<String, Object> param){
//        //메퍼에 부탁해보세요!! 2024-08-01 까지!!
//        Map<String, Object> map_tbnotice = tbnoticeMapper.detail(param);
//        return map_tbnotice;
//    }
    @Override
    public TbnoticeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbnoticeDto.DetailResDto selectResDto = tbnoticeMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }

        return selectResDto;
    }
//    @Override
//    public List<Map<String, Object>> list(Map<String, Object> param){
//
//        //검색어도 한번 넣어봅시다.
//        /*param.put("title", "123");*/
//
//        List<Map<String, Object>> list_tbnotice = tbnoticeMapper.list(param);
//        return list_tbnotice;
//    }

    @Override
    public List<TbnoticeDto.DetailResDto> list(TbnoticeDto.ListReqDto param){
        return detailList(tbnoticeMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbnoticeDto.PagedListReqDto param){
        int[] returnSize = param.init(tbnoticeMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbnoticeMapper.pagedList(param)));
    }
    @Override
    public List<TbnoticeDto.DetailResDto> scrollList(TbnoticeDto.ScrollListReqDto param){
        param.init();
        return detailList(tbnoticeMapper.scrollList(param));
    }
    //
    public List<TbnoticeDto.DetailResDto> detailList(List<TbnoticeDto.DetailResDto> list){
        List<TbnoticeDto.DetailResDto> newList = new ArrayList<>();
        for(TbnoticeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
