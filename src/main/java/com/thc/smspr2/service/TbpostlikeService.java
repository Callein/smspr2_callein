package com.thc.smspr2.service;

import com.thc.smspr2.dto.TbpostlikeDto;

public interface TbpostlikeService {
    TbpostlikeDto.CreateResDto likePost(TbpostlikeDto.CreateReqDto param);
    void unlikePost(TbpostlikeDto.DeleteReqDto param);
    boolean isLiked(String postId, String userId);
    TbpostlikeDto.CreateResDto toggleLike(TbpostlikeDto.CreateReqDto param);
}
