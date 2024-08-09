package com.thc.smspr2.domain;

import com.thc.smspr2.dto.TbpostlikeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
}
        ,uniqueConstraints = {
        @UniqueConstraint(
                name = "UQ_tbpostlike_tbpost_id_tbuser_id"
                ,columnNames = {"tbpostId", "tbuserId"}
        )}
)
@Entity
public class Tbpostlike extends AuditingFields {

    @Setter @Column(nullable = false) private String tbpostId;
    @Setter @Column(nullable = false) private String tbuserId;

    protected Tbpostlike(){}
    private Tbpostlike(String tbpostId, String tbuserId) {
        this.tbpostId = tbpostId;
        this.tbuserId = tbuserId;
    }
    public static Tbpostlike of(String tbpostId, String tbuserId) {
        return new Tbpostlike(tbpostId, tbuserId);
    }

    public TbpostlikeDto.CreateResDto toCreateResDto() {
        return TbpostlikeDto.CreateResDto.builder().id(this.getId()).build();
    }
}
