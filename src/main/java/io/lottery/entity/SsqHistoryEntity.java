package io.lottery.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class SsqHistoryEntity implements Serializable {
    private String code;

    private String gameCode;

    private String gameName;

    private String sales;

    private String week;

    private String poolMoney;

    private String bulletinDate;

    private Integer redBall1;

    private Integer redBall2;

    private Integer redBall3;

    private Integer redBall4;

    private Integer redBall5;

    private Integer redBall6;

    private Integer blueBall1;

    private Integer blueBall2;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime insertDate;

    private String content;
}