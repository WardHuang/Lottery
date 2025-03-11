package io.lottery.service.impl;

import io.lottery.entity.SsqBonusDetailEntity;
import io.lottery.mapper.SsqBonusDetailMapper;
import io.lottery.service.SsqBonusDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class SsqBonusDetailServiceImpl implements SsqBonusDetailService {

    @Resource
    private SsqBonusDetailMapper ssqBonusDetailMapper;


    @Override
    public int insertBatch(List<SsqBonusDetailEntity> records) {
        return ssqBonusDetailMapper.insertBatch(records);
    }

    @Override
    public List<SsqBonusDetailEntity> selectByPrimaryKey(List<String> ids) {
        return ssqBonusDetailMapper.selectByPrimaryKey(ids);
    }

    @Override
    public List<SsqBonusDetailEntity> selectByCode(List<String> codes) {
        return ssqBonusDetailMapper.selectByCode(codes);
    }
}
