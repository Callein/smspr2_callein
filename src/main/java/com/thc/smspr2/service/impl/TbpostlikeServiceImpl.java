package com.thc.smspr2.service.impl;

import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.repository.TbpostlikeRepository;
import com.thc.smspr2.service.TbpostlikeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TbpostlikeServiceImpl implements TbpostlikeService {
    private final TbpostlikeRepository tbpostlikeRepository;

    public TbpostlikeServiceImpl(TbpostlikeRepository tbpostlikeRepository) {
        this.tbpostlikeRepository = tbpostlikeRepository;
    }

    @Transactional
    public TbpostlikeDto.ToggleResDto toggleLike(TbpostlikeDto.ToggleReqDto param) {
        if (
                isLiked(
                    TbpostlikeDto.StatusReqDto.builder()
                        .tbpostId(param.getTbpostId())
                        .tbuserId(param.getTbuserId())
                        .build()
                ).getIsLiked()
        ) {
            return unlikePost(
                    TbpostlikeDto.DeleteServDto.builder()
                            .tbpostId(param.getTbpostId())
                            .tbuserId(param.getTbuserId())
                            .build()
            );
        } else {
            return likePost(param);
        }
    }

    @Override
    public TbpostlikeDto.StatusResDto isLiked(TbpostlikeDto.StatusReqDto param) {
        return TbpostlikeDto.StatusResDto.builder()
                .isLiked(tbpostlikeRepository.existsByTbpostIdAndTbuserId(param.getTbpostId(), param.getTbuserId()))
                .build();
    }

    @Transactional
    public TbpostlikeDto.ToggleResDto likePost(TbpostlikeDto.ToggleReqDto param){
        TbpostlikeDto.ToggleResDto toggleResDto = tbpostlikeRepository.save(param.toEntity()).toToggleResDto();
        toggleResDto.setLiked(true);
        return toggleResDto;
    }
    @Transactional
    public TbpostlikeDto.ToggleResDto unlikePost(TbpostlikeDto.DeleteServDto param) {
        tbpostlikeRepository.delete(
                tbpostlikeRepository.findByTbpostIdAndTbuserId(param.getTbpostId(), param.getTbuserId())
        );
        return TbpostlikeDto.ToggleResDto.builder()
                .tbpostId(param.getTbpostId())
                .liked(false)
                .build();
    }
}
