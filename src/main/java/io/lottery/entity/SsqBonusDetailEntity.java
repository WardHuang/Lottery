package io.lottery.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SsqBonusDetailEntity implements Serializable {
    private String id;

    private String code;

    private String type;

    private String typeNum;

    private String typeMoney;

    private LocalDateTime insertDate;

    private String gameCode;
}