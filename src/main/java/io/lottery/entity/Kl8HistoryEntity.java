package io.lottery.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class Kl8HistoryEntity implements Serializable {
    private String code;

    private String gameCode;

    private String gameName;

    private Integer redBall1;

    private Integer redBall2;

    private Integer redBall3;

    private Integer redBall4;

    private Integer redBall5;

    private Integer redBall6;

    private Integer redBall7;

    private Integer redBall8;

    private Integer redBall9;

    private Integer redBall10;

    private Integer redBall11;

    private Integer redBall12;

    private Integer redBall13;

    private Integer redBall14;

    private Integer redBall15;

    private Integer redBall16;

    private Integer redBall17;

    private Integer redBall18;

    private Integer redBall19;

    private Integer redBall20;

    private String sales;

    private String poolMoney;

    private LocalDateTime insertDate;

    private String bulletinDate;

    private String week;

    private String content;
}