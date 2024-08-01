package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpost;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;
import com.thc.smspr2.dto.TbpostfileDto;
import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.mapper.TbpostMapper;
import com.thc.smspr2.repository.TbpostRepository;
import com.thc.smspr2.service.TbpostService;
import com.thc.smspr2.service.TbpostfileService;
import com.thc.smspr2.service.TbpostlikeService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final TbpostRepository tbpostRepository;
    private final TbpostMapper tbpostMapper;
    private final TbpostfileService tbpostfileService;
    private final TbpostlikeService tbpostlikeService;

    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            , TbpostMapper tbpostMapper
            , TbpostfileService tbpostfileService
            , TbpostlikeService tbpostlikeService
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
        this.tbpostfileService = tbpostfileService;
        this.tbpostlikeService = tbpostlikeService;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param){
        TbpostDto.CreateResDto createResDto = tbpostRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbpostfileUrls().size();i++){
            tbpostfileService.create(
                    TbpostfileDto.CreateReqDto.builder()
                            .tbpostId(createResDto.getId())
                            .type(param.getTbpostfileTypes().get(i))
                            .url(param.getTbpostfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbpost.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpost.setProcess(param.getProcess());
        }

        if(param.getTbuserId() != null){
            tbpost.setTbuserId(param.getTbuserId());
        }
        if(param.getTitle() != null){
            tbpost.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbpost.setContent(param.getContent());
        }
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }

    @Override
    public TbpostDto.DetailResDto detail(DefaultDto.DetailReqDto param){

        TbpostDto.DetailResDto selectResDto = get(param);
        int countread = selectResDto.getCountread();
        Tbpost tbpost = tbpostRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));

        // 조회수를 1 올림, 중복체크는 따로 하지 않았다.
        tbpost.setCountread(++countread);
        tbpostRepository.save(tbpost);

        selectResDto.setTbpostfiles(
                tbpostfileService.list(TbpostfileDto.ListReqDto.builder().tbpostId(selectResDto.getId()).build())
        );


        return selectResDto;
    }

    public TbpostDto.DetailResDto get(DefaultDto.DetailReqDto param){
        TbpostDto.DetailResDto selectResDto = tbpostMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbpostfiles(
                tbpostfileService.list(TbpostfileDto.ListReqDto.builder().tbpostId(selectResDto.getId()).build())
        );

        // TbuserId 가 null 이 아닐 경우 좋아요 여부 설정.
        if(param.getTbuserId() != null){
            selectResDto.setLiked(
                    tbpostlikeService.isLiked(
                            TbpostlikeDto.StatusReqDto.builder()
                                    .tbpostId(selectResDto.getId())
                                    .tbuserId(selectResDto.getTbuserId())
                                    .build()
                    ).getIsLiked()
            );
        }

        /*
        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getReqTbuserId() != null){
            boolean liked = tbpostlikeService.exist(TbpostlikeDto.CreateReqDto.builder().tbpostId(selectResDto.getId()).tbuserId(param.getReqTbuserId()).build());
            selectResDto.setLiked(liked);
        }
        */
        return selectResDto;
    }

    @Override
    public List<TbpostDto.DetailResDto> list(TbpostDto.ListReqDto param){
        return  detailList(
                TbpostDto.DetailListServDto.builder()
                        .list(tbpostMapper.list(param))
                        .build()
        );
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param){
        int[] returnSize = param.init(tbpostMapper.pagedListCount(param));
        return param.afterBuild(
                returnSize,
                detailList(
                        TbpostDto.DetailListServDto.builder()
                                .list(tbpostMapper.pagedList(param))
                                .build()
                )
        );
    }

    @Override
    public List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListReqDto param){
        param.init();
        return detailList(
                TbpostDto.DetailListServDto.builder()
                        .list(tbpostMapper.scrollList(param))
                        .currentTbuserId(param.getTbuserId())
                        .build()
        );
    }

    public List<TbpostDto.DetailResDto> detailList(TbpostDto.DetailListServDto param){
        List<TbpostDto.DetailResDto> newList = new ArrayList<>();
        String currentTbuserId = param.getCurrentTbuserId();
        for(TbpostDto.DetailResDto each : param.getList()){
            newList.add(
                    get(
                            DefaultDto.DetailReqDto.builder()
                            .id(each.getId())
                            .tbuserId(currentTbuserId)
                            .build()
                    )
            );
//        for(TbpostDto.DetailResDto each : list){
//            //newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).tbuserId(tbuserId).build()));
////            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
//            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
//        }
        return newList;
    }
}
