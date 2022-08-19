package com.doger.nacosstorage.dao;

import com.doger.nacosstorage.entity.Storage;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    Storage findByCodeStorage(@Param("code") String code);
}