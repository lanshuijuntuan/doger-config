package com.doger.nacosorder.service;


import com.doger.nacosorder.dto.APIResponse;
import com.doger.nacosorder.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("nacos-storage")
public interface StorageService {

    @RequestMapping("/storage/add")
    APIResponse add(Storage storage);

    @RequestMapping("/storage/sub")
    APIResponse sub(Storage storage);
}
