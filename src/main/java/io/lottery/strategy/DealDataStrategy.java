package io.lottery.strategy;

import io.lottery.bo.DataInformationSubBo;

import java.util.List;

public interface DealDataStrategy {

    void execDealData(List<DataInformationSubBo> result);
}
