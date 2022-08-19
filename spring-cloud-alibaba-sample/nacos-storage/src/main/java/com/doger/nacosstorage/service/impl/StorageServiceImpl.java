package com.doger.nacosstorage.service.impl;

import com.doger.nacosstorage.dao.StorageMapper;
import com.doger.nacosstorage.dto.APIResponse;
import com.doger.nacosstorage.entity.Storage;
import com.doger.nacosstorage.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public APIResponse add(String commodityCode, Integer count) {
        if(count.intValue()<=0){
            return APIResponse.fail("数量非法");
        }
        Storage storage=storageMapper.findByCodeStorage(commodityCode);
        if(storage==null){
            return APIResponse.fail("未找到库存信息");
        }
        storage.setCount(storage.getCount()+count);
        storageMapper.updateByPrimaryKey(storage);
        return APIResponse.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public APIResponse sub(String commodityCode, Integer count) {
        if(count.intValue()<=0){
            return APIResponse.fail("数量非法");
        }
        Storage storage=storageMapper.findByCodeStorage(commodityCode);
        if(storage==null){
            return APIResponse.fail("未找到库存信息");
        }
        if(count.compareTo(storage.getCount())>0){
            return APIResponse.fail("库存不足");
        }
        storage.setCount(storage.getCount()-count);
        storageMapper.updateByPrimaryKey(storage);
        return APIResponse.ok();
    }
}
