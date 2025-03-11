package io.lottery.controller;

import io.lottery.req.BulletinInformationReq;
import io.lottery.resp.BulletinInformationResp;
import io.lottery.resp.DataInformationResp;
import io.lottery.service.GetSsqDataService;
import io.lottery.service.LotteryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/Lottery")
@Validated
@Tag(name = "福彩" ,description = "Lottery")
public class LotteryController {

    @Resource
    private LotteryService lotteryService;

    @Resource
    private GetSsqDataService getSsqDataService;

    @Operation(operationId = "get lottery info", summary = "从福彩接口获取快乐8历史中奖信息")
    @GetMapping(value = "/v1/lottery/GetHappy8LotteryWinInformation")
    private DataInformationResp getHappy8LotteryWinInformation(BulletinInformationReq req){
        return lotteryService.getHappy8LotteryWinInformation(req);
    }

    @Operation(operationId = "all lottery info", summary = "从福彩同步开奖信息")
    @GetMapping(value = "/v1/lottery/syncBulletinInformation")
    private void GetSsqDataService(){
        getSsqDataService.getAllSsqData();
    }

    @Operation(operationId = "get lottery info", summary = "查询项目内存储的信息")
    @GetMapping(value = "/v1/lottery/getBulletinData")
    private BulletinInformationResp getBulletinData(BulletinInformationReq req){
         return lotteryService.queryDrawNotice(req);
    }
}
