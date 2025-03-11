package io.lottery.mapper;

import io.lottery.entity.SsqBonusDetailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SsqBonusDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SsqBonusDetailEntity record);

    int insertBatch(@Param("SsqBonusDetailList") List<SsqBonusDetailEntity> record);

    List<SsqBonusDetailEntity> selectByPrimaryKey(@Param("idList") List<String> id);
    List<SsqBonusDetailEntity> selectByCode(@Param("idList") List<String> codes);

    List<SsqBonusDetailEntity> selectAll();

    int updateByPrimaryKey(SsqBonusDetailEntity record);
}