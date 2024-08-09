package com.thc.smspr2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.BeanUtils;

public class DefaultDto {
    @Builder
    @Schema
    @Getter
    @Setter
    public static class FileResDto{
        private String url;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseDto {
        String empty; // 이게 없으면 빌더가 잘 안되어요 ㅠㅠ
        public BaseDto afterBuild(BaseDto param) {
            //param => reqDto 를 넣어주면!!
            BeanUtils.copyProperties(param, this);
            //this 인 서비스 디티오를 돌려줍니다! 안에 있는 모든 필드값 카피도 해줍니다!
            return this;
        }
    }

    /**/

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends BaseDto {
        @Schema(description = "id", example="")
        @NotNull
        @NotEmpty
        private String id;

        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailReqDto extends BaseDto {
        @Schema(description = "id", example="")
        @NotNull
        @NotEmpty
        private String id;
        /*
        //이거는 로그인 한 사람이 쓰는 정보
        @Schema(description = "tbuserId", example="")
        private String tbuserId;*/
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailServDto extends DetailReqDto{
        @Schema(description = "isAdmin", example="")
        private boolean isAdmin;
        @Schema(description = "reqTbuserId", example="")
        private String reqTbuserId;
    }

    //여기는 빌더 붙이면 에러 나요!! 조심!!
    @Schema
    @Getter
    @Setter
    public static class DetailResDto{
        @Schema(description = "id", example="")
        private String id;
        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;
        @Schema(description = "createdAt", example="")
        private String createdAt;
        @Schema(description = "modifiedAt", example="")
        private String modifiedAt;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends BaseDto {
        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends BaseDto {

        @Schema(description = "callpage", example="")
        private Integer callpage;
        @Schema(description = "perpage", example="")
        private Integer perpage;
        @Schema(description = "orderby", example="")
        private String orderby;
        @Schema(description = "orderway", example="")
        private String orderway;

        //원래는 고객한테 받으면 안되는 정보!
        @Schema(description = "offset", example="")
        private Integer offset;

        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;

        public int[] init(int listsize){
            if(orderby == null || orderby.isEmpty()){
                orderby = "created_at";
            }
            if(orderway == null || orderway.isEmpty()){
                orderway = "desc";
            }
            if(perpage == null || perpage < 1){
                //한번에 조회할 글 갯수
                perpage = 10;
            }
            if(callpage == null){
                //호출하는 페이지
                callpage = 1;
            }
            if(callpage < 1){
                callpage = 1;
            }

            int pagesize = listsize / perpage;
            if(listsize % perpage > 0){
                pagesize++;
            }
            if(callpage > pagesize){
                callpage = pagesize;
            }
            offset = (callpage - 1) * perpage;
            int[] res = {listsize, pagesize};
            return res;
        }
        public PagedListResDto afterBuild(int[] resSize, Object list){
            return PagedListResDto.builder()
                    .callpage(callpage)
                    .perpage(perpage)
                    .orderby(orderby)
                    .orderway(orderway)
                    .listsize(resSize[0])
                    .pagesize(resSize[1])
                    .list(list)
                    .build();
        }
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListResDto{

        @Schema(description = "callpage", example="")
        private Integer callpage;
        @Schema(description = "perpage", example="")
        private Integer perpage;
        @Schema(description = "orderby", example="")
        private String orderby;
        @Schema(description = "orderway", example="")
        private String orderway;

        @Schema(description = "listsize", example="")
        private Integer listsize;
        @Schema(description = "pagesize", example="")
        private Integer pagesize;

        @Schema(description = "list", example="")
        private Object list;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends BaseDto {

        @Schema(description = "cursor", example="")
        private String cursor;
        @Schema(description = "perpage", example="")
        private Integer perpage;
        @Schema(description = "orderby", example="")
        private String orderby;
        @Schema(description = "orderway", example="")
        private String orderway;

        @Schema(description = "deleted", example="")
        private String deleted;
        @Schema(description = "process", example="")
        private String process;

        //이거는 로그인 한 사람이 쓰는 정보
        @Schema(description = "tbuserId", example="")
        private String tbuserId;

        public void init(){
            if(orderby == null || orderby.isEmpty()){
                orderby = "created_at";
            }
            if(orderway == null || orderway.isEmpty()) {
                orderway = "desc";
            }
            if(perpage == null || perpage < 1){
                //한번에 조회할 글 갯수
                perpage = 10;
            }
        }
    }
}
