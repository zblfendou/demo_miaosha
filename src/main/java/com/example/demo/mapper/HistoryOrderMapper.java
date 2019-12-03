package com.example.demo.mapper;

import com.example.demo.model.HistoryOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HistoryOrder record);

    int insertSelective(HistoryOrder record);

    HistoryOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HistoryOrder record);

    int updateByPrimaryKey(HistoryOrder record);
}
