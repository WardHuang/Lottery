package io.lottery.service;

import io.lottery.entity.SsqHistoryEntity;

import java.util.List;

public interface SsqHistoryService {

    int insertBatch(List<SsqHistoryEntity> records);

    List<SsqHistoryEntity> selectAll();
}
