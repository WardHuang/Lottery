package io.lottery.task;

import io.lottery.strategy.DealDataStrategy;
import io.lottery.strategy.DealDataStrategyFactory;

import javax.annotation.Resource;

public class DataTask implements Runnable{


    @Resource
    private DealDataStrategyFactory dealDataStrategyFactory;


    @Override
    public void run() {
//        DealDataStrategy dealDataStrategy = dealDataStrategyFactory.getDealDataStrategy(req.getName());
//        dealDataStrategy.execDealData(resp.getResult());
    }
}
