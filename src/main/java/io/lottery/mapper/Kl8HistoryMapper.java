package io.lottery.mapper;

import io.lottery.entity.Kl8HistoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Kl8HistoryMapper {
    int deleteByPrimaryKey(Integer code);

    int insert(Kl8HistoryEntity record);

    int insertBatch(@Param("Kl8HistoryList") List<Kl8HistoryEntity> record);

    Kl8HistoryEntity selectByPrimaryKey(Integer code);

    List<Kl8HistoryEntity> selectAll();

    int updateByPrimaryKey(Kl8HistoryEntity record);
}