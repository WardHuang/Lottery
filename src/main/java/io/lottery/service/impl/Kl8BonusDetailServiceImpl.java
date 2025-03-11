package io.lottery.service.impl;

import io.lottery.entity.Kl8BonusDetailEntity;
import io.lottery.mapper.Kl8BonusDetailMapper;
import io.lottery.service.Kl8BonusDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class Kl8BonusDetailServiceImpl implements Kl8BonusDetailService {

    @Resource
    private Kl8BonusDetailMapper kl8BonusDetailMapper;


    @Override
    public int insertBatch(List<Kl8BonusDetailEntity> records) {
        return kl8BonusDetailMapper.insertBatch(records);
    }
}
