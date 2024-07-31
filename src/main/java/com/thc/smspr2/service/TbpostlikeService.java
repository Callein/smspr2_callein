package com.thc.smspr2.service;

import com.thc.smspr2.dto.TbpostlikeDto;

public interface TbpostlikeService {
    TbpostlikeDto.StatusResDto isLiked(TbpostlikeDto.StatusReqDto param);
    TbpostlikeDto.ToggleResDto toggleLike(TbpostlikeDto.ToggleReqDto param);
}
