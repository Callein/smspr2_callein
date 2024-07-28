package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbnoticeDto;
import com.thc.smspr2.dto.TbpostDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Tbnotice extends AuditingFields{
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = false) private String cate;
    @Setter @Column(nullable = true, length=10000) @Lob private String content; // 본문

    protected Tbnotice(){}
    private Tbnotice(String title, String cate, String content) {
        this.title = title;
        this.cate = cate;
        this.content = content;
    }

    public static Tbnotice of(String title, String cate, String content) {
        return new Tbnotice(title, cate, content);
    }

    public TbnoticeDto.CreateResDto toCreateResDto() {
        return TbnoticeDto.CreateResDto.builder().id(this.getId()).build();
    }


}
