package io.lottery.controller;

import io.lottery.entity.LuckNum;
import io.lottery.req.GetLuckReq;
import io.lottery.service.SportLotteryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/SportLottery")
@Validated
@Tag(name = "体彩" ,description = "SportLottery")
public class SportsLotteryController {

    @Resource
    private SportLotteryService sportLotteryService;


    @Operation(operationId = "luck", summary = "分析幸运数字")
    @GetMapping(value = "/v1/sportLottery/GetHappy8LotteryWinInformation")
    private void getLuckNum(Integer group,String luckNum,Integer selectorNum){
        sportLotteryService.getLuckNum(group,luckNum,selectorNum);
    }

    @Operation(operationId = "luck", summary = "分析幸运数字")
    @PostMapping(value = "/v1/sportLottery/getLuckNum")
    private void getLuckNum(@Valid @RequestBody GetLuckReq req){
        sportLotteryService.getLuckNum(req);
    }
}
