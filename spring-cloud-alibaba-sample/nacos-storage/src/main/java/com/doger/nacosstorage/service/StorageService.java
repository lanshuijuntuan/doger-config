package com.doger.nacosstorage.service;

import com.doger.nacosstorage.dto.APIResponse;

public interface StorageService {

    APIResponse add(String commodityCode, Integer count);

    APIResponse sub(String commodityCode,Integer count);
}
