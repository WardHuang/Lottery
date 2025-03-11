package io.lottery.req;

import io.lottery.entity.LuckNum;
import lombok.Data;

import java.util.List;

@Data
public class GetLuckReq {

    private Integer selectorNum;

    private List<LuckNum> luckNums;
}
