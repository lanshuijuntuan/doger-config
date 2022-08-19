package com.doger.nacosorder.service;


import com.doger.nacosorder.dto.APIResponse;
import com.doger.nacosorder.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("nacos-account")
public interface AccountService {

    @RequestMapping("/account/add")
    APIResponse add(Account account);

    @RequestMapping("/account/sub")
    APIResponse sub(Account account);
}
