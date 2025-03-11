package io.lottery.mapper;

import io.lottery.entity.Kl8BonusDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Kl8BonusDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(Kl8BonusDetailEntity record);

    int insertBatch(@Param("kl8PrizeGradesList") List<Kl8BonusDetailEntity> record);

    Kl8BonusDetailEntity selectByPrimaryKey(String id);

    List<Kl8BonusDetailEntity> selectAll();

    int updateByPrimaryKey(Kl8BonusDetailEntity record);
}