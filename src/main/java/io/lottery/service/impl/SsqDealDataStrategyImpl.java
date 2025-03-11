package io.lottery.service.impl;

import cn.hutool.core.util.IdUtil;
import io.lottery.bo.DataInformationSubBo;
import io.lottery.constant.GameCodeConstant;
import io.lottery.entity.SsqBonusDetailEntity;
import io.lottery.entity.SsqHistoryEntity;
import io.lottery.service.SsqBonusDetailService;
import io.lottery.service.SsqHistoryService;
import io.lottery.strategy.DealDataStrategy;
import io.lottery.strategy.DealDataStrategyFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.lottery.utils.EntityMapper;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SsqDealDataStrategyImpl implements DealDataStrategy, InitializingBean {

    @Resource
    private DealDataStrategyFactory dealDataStrategyFactory;

    @Resource
    private SsqHistoryService ssqHistoryService;

    @Resource
    private SsqBonusDetailService ssqBonusDetailService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execDealData(List<DataInformationSubBo> result) {

        List<SsqHistoryEntity> ssqHistoryEntities = new ArrayList<>();
        List<SsqBonusDetailEntity> ssqBonusDetailEntities = new ArrayList<>();
        result.forEach(item->{
            Map<String,Object> entityMap;
            SsqHistoryEntity ssqHistoryEntity = null;
            try{
                entityMap = EntityMapper.convertToMap(item);
                ssqHistoryEntity = EntityMapper.mapperToEntity(entityMap, SsqHistoryEntity.class);
            }catch (Exception e){
                e.printStackTrace();
            }
            AtomicInteger count = new AtomicInteger(1);
            Map<String, Integer> redBallMap = new HashMap<>();
            Arrays.stream(item.getRed().split(",")).collect(Collectors.toList()).forEach(redBall->{
                redBallMap.put("redBall"+count,Integer.valueOf(redBall));
                count.getAndIncrement();
            });
            //反射获取key然后赋值
            SsqHistoryEntity finalSsqHistoryEntity = ssqHistoryEntity;
            redBallMap.forEach((key, value) -> {
                try {
                    Field field = SsqHistoryEntity.class.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(finalSsqHistoryEntity, value);
                } catch (NoSuchFieldException | IllegalAccessException  e){
                    e.printStackTrace();
                }
            });
            assert finalSsqHistoryEntity != null;
            finalSsqHistoryEntity.setBlueBall1(Integer.valueOf(item.getBlue()));
            finalSsqHistoryEntity.setGameName("双色球");
            finalSsqHistoryEntity.setGameCode(GameCodeConstant.SSQ);
            finalSsqHistoryEntity.setBulletinDate(item.getDate());
            finalSsqHistoryEntity.setInsertDate(LocalDateTime.now());
            //TODO:将red字符串拆开放进对应的ball的位置
            ssqHistoryEntities.add(finalSsqHistoryEntity);
            item.getPrizeGrades().forEach(prizeGradesBo -> {
                SsqBonusDetailEntity ssqBonusDetailEntity = new SsqBonusDetailEntity();
                ssqBonusDetailEntity.setId(IdUtil.getSnowflakeNextIdStr());
                BeanUtils.copyProperties(prizeGradesBo,ssqBonusDetailEntity);
                ssqBonusDetailEntity.setInsertDate(LocalDateTime.now());
                ssqBonusDetailEntity.setCode(item.getCode());
                ssqBonusDetailEntity.setGameCode(GameCodeConstant.SSQ);
                ssqBonusDetailEntities.add(ssqBonusDetailEntity);
            });
        });
        try {
            Integer ssqHistoryNum = ssqHistoryService.insertBatch(ssqHistoryEntities);
            Integer ssqBonusNum = ssqBonusDetailService.insertBatch(ssqBonusDetailEntities);
            log.info("历史开奖信息入库{}条,历史中奖信息入库{}条",ssqHistoryNum,ssqBonusNum);
        }catch (Exception e){
            e.printStackTrace();
            log.error("数据入库失败");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dealDataStrategyFactory.register(GameCodeConstant.SSQ,this);
    }
}
