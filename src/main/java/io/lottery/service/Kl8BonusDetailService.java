package io.lottery.service;

import io.lottery.entity.Kl8BonusDetailEntity;
import io.lottery.entity.SsqHistoryEntity;

import java.util.List;

public interface Kl8BonusDetailService {

    int insertBatch(List<Kl8BonusDetailEntity> records);
}
