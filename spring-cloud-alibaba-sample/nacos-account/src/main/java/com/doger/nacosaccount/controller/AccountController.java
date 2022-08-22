package com.doger.nacosaccount.controller;


import com.doger.nacosaccount.dto.APIResponse;
import com.doger.nacosaccount.entity.Account;
import com.doger.nacosaccount.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RequestMapping("/account")
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping("add")
    public APIResponse add(@RequestBody Account account){
        log.info("AccountController add");
        if(StringUtils.isEmpty(account.getUserId())){
            return APIResponse.fail("账户号为空");
        }
        if(account.getMoney()==null){
            return APIResponse.fail("金额为空");
        }
        return accountService.add(account.getUserId(),account.getMoney());
    }


    @RequestMapping("sub")
    public APIResponse sub(@RequestBody Account account){
        log.info("AccountController sub");
        if(StringUtils.isEmpty(account.getUserId())){
            return APIResponse.fail("账户号为空");
        }
        if(account.getMoney()==null){
            return APIResponse.fail("金额为空");
        }
        return accountService.sub(account.getUserId(),account.getMoney());
    }
}
