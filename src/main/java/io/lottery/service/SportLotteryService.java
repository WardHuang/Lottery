package io.lottery.service;

import io.lottery.req.GetLuckReq;

public interface SportLotteryService {

    void getLuckNum(Integer group,String luckNum,Integer selectorNum);
    void getLuckNum(GetLuckReq req);
}
