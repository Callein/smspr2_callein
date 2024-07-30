package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpostlike;
import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.repository.TbpostRepository;
import com.thc.smspr2.repository.TbpostlikeRepository;
import com.thc.smspr2.service.TbpostlikeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbpostlikeServiceImpl implements TbpostlikeService {

    @Autowired
    private TbpostRepository tbpostRepository;

    @Autowired
    private TbpostlikeRepository tbpostlikeRepository;

    @Transactional
    public TbpostlikeDto.CreateResDto likePost(TbpostlikeDto.CreateReqDto param){
        return tbpostlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Transactional
    public void unlikePost(TbpostlikeDto.DeleteReqDto param) {
        Tbpostlike like = tbpostlikeRepository.findByTbpostIdAndTbuserId(param.getTbpostId(), param.getTbuserId());
        if (like != null) {
            tbpostlikeRepository.delete(like);
        } else {
            throw new RuntimeException("Like not found");
        }
    }

    @Override
    public boolean isLiked(String postId, String userId) {
        return tbpostlikeRepository.existsByTbpostIdAndTbuserId(postId, userId);
    }

    @Transactional
    public TbpostlikeDto.CreateResDto toggleLike(TbpostlikeDto.CreateReqDto param) {
        boolean isLiked = isLiked(param.getTbpostId(), param.getTbuserId());
        if (isLiked) {
            TbpostlikeDto.DeleteReqDto deleteReqDto = TbpostlikeDto.DeleteReqDto.builder()
                    .tbpostId(param.getTbpostId())
                    .tbuserId(param.getTbuserId())
                    .build();
            unlikePost(deleteReqDto);
            return null;  // 좋아요 취소했을 때 반환값 처리
        } else {
            return likePost(param);
        }
    }
}
