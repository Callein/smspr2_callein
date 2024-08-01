package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbpost;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class TbpostDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        //사용자에게 입력 받는 값이 아닙니다!! 주의!!
        @Schema(description = "tbuserId", example="")
        /*@NotNull
        @NotEmpty*/
        private String tbuserId;

        @Schema(description = "title", example="")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String title;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;

        @Schema(description = "tbpostfileTypes", example="")
        private List<String> tbpostfileTypes;
        @Schema(description = "tbpostfileUrls", example="")
        private List<String> tbpostfileUrls;

        public Tbpost toEntity(){
            return Tbpost.of(tbuserId, title, content);
        }
    }
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;

        @Schema(description = "title", example="")
        @Size(max=400)
        private String title;
        @Schema(description = "content", example="")
        @Size(max=4000)
        private String content;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;

        @Schema(description = "title", example="")
        private String title;
        @Schema(description = "content", example="")
        private String content;

        @Schema(description = "tbuserName", example="")
        private String tbuserName;
        @Schema(description = "tbuserNick", example="")
        private String tbuserNick;
        @Schema(description = "tbuserImg", example="")
        private String tbuserImg;

        @Schema(description = "tbpostfiles", example="")
        private List<TbpostfileDto.DetailResDto> tbpostfiles;

        @Schema(description = "countread", example = "")
        private Integer countread;
        @Schema(description = "liked", example = "")
        private boolean liked;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "title", example="")
        private String title;
    }

    @Schema
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailListServDto{
        @Schema(description = "list", example = "")
        private List<TbpostDto.DetailResDto> list;
        @Schema(description = "currentTbuserId", example = "")
        private String currentTbuserId;
    }
}
