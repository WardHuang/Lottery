package io.lottery.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LuckNum implements Serializable {

    private Integer id;

    private Integer num;

    private String luckNum;

}