package io.lottery.strategy;

import java.util.List;

public interface DealDataTStrategy<T> {

    void execDealTData(List<T> result);
}
