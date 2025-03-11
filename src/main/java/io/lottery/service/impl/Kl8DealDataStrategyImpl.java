package io.lottery.service.impl;

import cn.hutool.core.util.IdUtil;
import io.lottery.bo.DataInformationSubBo;
import io.lottery.constant.GameCodeConstant;
import io.lottery.entity.Kl8BonusDetailEntity;
import io.lottery.entity.Kl8HistoryEntity;
import io.lottery.service.Kl8BonusDetailService;
import io.lottery.service.Kl8HistoryService;
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
public class Kl8DealDataStrategyImpl implements DealDataStrategy, InitializingBean {

    @Resource
    private DealDataStrategyFactory dealDataStrategyFactory;

    @Resource
    private Kl8HistoryService kl8HistoryService;

    @Resource
    private Kl8BonusDetailService kl8BonusDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execDealData(List<DataInformationSubBo> result) {
        List<Kl8HistoryEntity> kl8HistoryEntities = new ArrayList<>();
        List<Kl8BonusDetailEntity> kl8BonusDetailEntities = new ArrayList<>();
        result.forEach(item->{
            Map<String,Object> entityMap;
            Kl8HistoryEntity kl8HistoryEntity = null;
            try{
                entityMap = EntityMapper.convertToMap(item);
                kl8HistoryEntity = EntityMapper.mapperToEntity(entityMap, Kl8HistoryEntity.class);
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
            Kl8HistoryEntity finalKl8HistoryEntity = kl8HistoryEntity;
            redBallMap.forEach((key, value) -> {
                try {
                    Field field = Kl8HistoryEntity.class.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(finalKl8HistoryEntity, value);
                } catch (NoSuchFieldException | IllegalAccessException  e){
                    e.printStackTrace();
                }
            });
            assert finalKl8HistoryEntity != null;
            //TODO:美观可以反射赋值
            finalKl8HistoryEntity.setGameName("快乐8");
            finalKl8HistoryEntity.setGameCode(GameCodeConstant.KL8);
            finalKl8HistoryEntity.setBulletinDate(item.getDate());
            finalKl8HistoryEntity.setInsertDate(LocalDateTime.now());
            //将red字符串拆开放进对应的ball的位置
            kl8HistoryEntities.add(finalKl8HistoryEntity);
            item.getPrizeGrades().forEach(prizeGradesBo -> {
                Kl8BonusDetailEntity kl8BonusDetailEntity = new Kl8BonusDetailEntity();
                kl8BonusDetailEntity.setId(IdUtil.getSnowflakeNextIdStr());
                BeanUtils.copyProperties(prizeGradesBo,kl8BonusDetailEntity);
                kl8BonusDetailEntity.setInsertDate(LocalDateTime.now());
                kl8BonusDetailEntity.setCode(item.getCode());
                kl8BonusDetailEntity.setGameCode(GameCodeConstant.KL8);
                kl8BonusDetailEntities.add(kl8BonusDetailEntity);
            });
        });
        try {
            Integer kl8HistoryNum = kl8HistoryService.insertBatch(kl8HistoryEntities);
            Integer kl8BonusNum = kl8BonusDetailService.insertBatch(kl8BonusDetailEntities);
            log.info("历史开奖信息入库{}条,历史中奖信息入库{}条",kl8HistoryNum,kl8BonusNum);
        }catch (Exception e){
            e.printStackTrace();
            log.error("数据入库失败");
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dealDataStrategyFactory.register(GameCodeConstant.KL8,this);
    }
}
