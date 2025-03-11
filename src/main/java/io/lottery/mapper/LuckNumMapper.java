package io.lottery.mapper;

import io.lottery.entity.LuckNum;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LuckNumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LuckNum record);

    int insertBatch(List<LuckNum> record);

    LuckNum selectByPrimaryKey(Integer id);

    List<LuckNum> selectAll();

    int updateByPrimaryKey(LuckNum record);
}