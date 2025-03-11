package io.lottery.service.impl;

import io.lottery.entity.SsqHistoryEntity;
import io.lottery.mapper.SsqHistoryMapper;
import io.lottery.service.SsqHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SsqHistoryServiceImpl implements SsqHistoryService {

    @Resource
    private SsqHistoryMapper ssqHistoryMapper;

    @Override
    public int insertBatch(List<SsqHistoryEntity> records) {
        return ssqHistoryMapper.insertBatch(records);
    }

    @Override
    public List<SsqHistoryEntity> selectAll() {
        return ssqHistoryMapper.selectAll();
    }
}
