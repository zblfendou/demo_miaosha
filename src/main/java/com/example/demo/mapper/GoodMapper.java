package com.example.demo.mapper;

import com.example.demo.model.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface GoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    int insertSelective(Good record);

    Good selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Good record);

    int updateByPrimaryKey(Good record);

    List<Good> getGoods(Good condition);

    Good getGood(Good condition);

    int reduceStock(Good g);

}
