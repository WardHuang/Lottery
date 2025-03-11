package io.lottery.service;

import io.lottery.entity.Kl8HistoryEntity;
import io.lottery.entity.SsqHistoryEntity;

import java.util.List;

public interface Kl8HistoryService {

    int insertBatch(List<Kl8HistoryEntity> records);
}
