package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpostlike;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.mapper.TbpostlikeMapper;
import com.thc.smspr2.repository.TbpostlikeRepository;
import com.thc.smspr2.service.TbpostlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostlikeServiceImpl implements TbpostlikeService {

    private final TbpostlikeRepository tbpostlikeRepository;
    private final TbpostlikeMapper tbpostlikeMapper;
    public TbpostlikeServiceImpl(
            TbpostlikeRepository tbpostlikeRepository
            ,TbpostlikeMapper tbpostlikeMapper
    ) {
        this.tbpostlikeRepository = tbpostlikeRepository;
        this.tbpostlikeMapper = tbpostlikeMapper;
    }

    @Override
    public boolean exist(TbpostlikeDto.CreateReqDto param){
        Tbpostlike tbpostlike = tbpostlikeRepository.findByTbpostIdAndTbuserId(param.getTbpostId(),param.getTbuserId());
        return tbpostlike != null;
    }
    @Override
    public TbpostlikeDto.CreateResDto toggle(TbpostlikeDto.CreateReqDto param){
        Tbpostlike tbpostlike = tbpostlikeRepository.findByTbpostIdAndTbuserId(param.getTbpostId(),param.getTbuserId());
        if(tbpostlike == null){
            return tbpostlikeRepository.save(param.toEntity()).toCreateResDto();
        } else {
            return delete(TbpostlikeDto.UpdateReqDto.builder().id(tbpostlike.getId()).build());
        }
    }

    /**/

    @Override
    public TbpostlikeDto.CreateResDto create(TbpostlikeDto.CreateReqDto param){
        return tbpostlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbpostlikeDto.CreateResDto update(TbpostlikeDto.UpdateReqDto param){
        Tbpostlike tbpostlike = tbpostlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbpostlike.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpostlike.setProcess(param.getProcess());
        }
        tbpostlikeRepository.save(tbpostlike);
        return tbpostlike.toCreateResDto();
    }
    @Override
    public TbpostlikeDto.CreateResDto delete(TbpostlikeDto.UpdateReqDto param){
        Tbpostlike tbpostlike = tbpostlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbpostlikeRepository.delete(tbpostlike);
        return TbpostlikeDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbpostlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbpostlikeDto.DetailResDto selectResDto = tbpostlikeMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbpostlikeDto.DetailResDto> list(TbpostlikeDto.ListReqDto param){
        return detailList(tbpostlikeMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostlikeDto.PagedListReqDto param) {
        int[] res = param.init(tbpostlikeMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbpostlikeMapper.pagedList(param)));
    }
    @Override
    public List<TbpostlikeDto.DetailResDto> scrollList(TbpostlikeDto.ScrollListReqDto param){
        param.init();
        return detailList(tbpostlikeMapper.scrollList(param));
    }

    public List<TbpostlikeDto.DetailResDto> detailList(List<TbpostlikeDto.DetailResDto> list){
        List<TbpostlikeDto.DetailResDto> newList = new ArrayList<>();
        for(TbpostlikeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
