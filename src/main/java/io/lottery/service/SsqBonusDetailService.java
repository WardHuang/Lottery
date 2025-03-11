package io.lottery.service;

import io.lottery.entity.SsqBonusDetailEntity;
import io.lottery.entity.SsqHistoryEntity;

import java.util.List;

public interface SsqBonusDetailService {

    int insertBatch(List<SsqBonusDetailEntity> records);

    List<SsqBonusDetailEntity> selectByPrimaryKey(List<String> id);
    List<SsqBonusDetailEntity> selectByCode(List<String> codes);
}
