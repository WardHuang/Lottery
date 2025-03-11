package io.lottery.service;

import io.lottery.req.BulletinInformationReq;
import io.lottery.resp.BulletinInformationResp;
import io.lottery.resp.DataInformationResp;

public interface LotteryService {

    /**
     * 获取快乐8历史中奖信息
     */
    DataInformationResp getHappy8LotteryWinInformation(BulletinInformationReq req);

    BulletinInformationResp queryDrawNotice(BulletinInformationReq req) ;
}
