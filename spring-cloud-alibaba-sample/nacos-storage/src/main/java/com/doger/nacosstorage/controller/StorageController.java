package com.doger.nacosstorage.controller;

import com.doger.nacosstorage.dto.APIResponse;
import com.doger.nacosstorage.entity.Storage;
import com.doger.nacosstorage.service.StorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @RequestMapping("add")
    public APIResponse add(@RequestBody Storage storage) {
        if (StringUtils.isEmpty(storage.getCommodityCode())) {
            return APIResponse.fail("产品号为空");
        }
        if (storage.getCount() == null) {
            return APIResponse.fail("数量为空");
        }
        return storageService.add(storage.getCommodityCode(), storage.getCount());
    }

    @RequestMapping("sub")
    public APIResponse sub(@RequestBody Storage storage) {
        log.info("StorageController sub");
        if (StringUtils.isEmpty(storage.getCommodityCode())) {
            return APIResponse.fail("产品号为空");
        }
        if (storage.getCount() == null) {
            return APIResponse.fail("数量为空");
        }
        return storageService.sub(storage.getCommodityCode(), storage.getCount());
    }
}
