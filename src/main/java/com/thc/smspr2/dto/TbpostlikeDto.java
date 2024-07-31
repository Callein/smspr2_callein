package com.thc.smspr2.dto;

import com.thc.smspr2.domain.Tbpostlike;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbpostlikeDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        private String tbpostId;

        @Schema(description = "tbuserId", example="")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;

        public Tbpostlike toEntity(){
            return Tbpostlike.of(tbpostId, tbuserId);
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
        private boolean liked;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;

        @Schema(description = "tbuserId", example="")
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example="")
        @Size(max=400)
        private String content;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;

        @Schema(description = "tbuserId", example="")
        private String tbuserId;
        @Schema(description = "content", example="")
        private String content;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbpostId", example="")
        private String tbpostId;
        @Schema(description = "tbuserId", example="")
        private String tbuserId;
    }

    @Getter
    @Builder
    public static class DeleteReqDto {
        private String tbpostId;
        private String tbuserId;
    }

    @Getter
    @Builder
    public static class StatusReqDto{
        private String tbpostId;
        private String tbuserId;
    }
    @Getter
    @Builder
    public static class StatusResDto{
        private Boolean isLiked;
    }
}
