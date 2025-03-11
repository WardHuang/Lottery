package io.lottery.strategy;

import cn.hutool.core.lang.Assert;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DealDataStrategyFactory {

    private static Map<String,DealDataStrategy> dealDataStrategyMap = new ConcurrentHashMap<>();

    public DealDataStrategy getDealDataStrategy(String dealDataType){
        return dealDataStrategyMap.get(dealDataType);
    }

    public void register(String dealDataType,DealDataStrategy dealDataStrategy){
        Assert.notNull(dealDataType,"orderType isn`t null");
        dealDataStrategyMap.put(dealDataType,dealDataStrategy);
    }
}
