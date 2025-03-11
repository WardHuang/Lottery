package io.lottery.mapper;

import io.lottery.entity.SsqHistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SsqHistoryMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(SsqHistoryEntity record);

    int insertBatch(@Param("SsqHistoryList") List<SsqHistoryEntity> record);

    SsqHistoryEntity selectByPrimaryKey(Integer code);

    List<SsqHistoryEntity> selectAll();

    int updateByPrimaryKey(SsqHistoryEntity record);
}