package io.lottery.service.impl;

import io.lottery.entity.Kl8HistoryEntity;
import io.lottery.mapper.Kl8HistoryMapper;
import io.lottery.service.Kl8HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class Kl8HistoryServiceImpl implements Kl8HistoryService {

    @Resource
    private Kl8HistoryMapper kl8HistoryMapper;


    @Override
    public int insertBatch(List<Kl8HistoryEntity> records) {
        return kl8HistoryMapper.insertBatch(records);
    }
}
