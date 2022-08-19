package com.doger.nacosaccount.service.impl;

import com.doger.nacosaccount.dao.AccountMapper;
import com.doger.nacosaccount.dto.APIResponse;
import com.doger.nacosaccount.entity.Account;
import com.doger.nacosaccount.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public APIResponse add(String userId,Integer amt) {
        if(amt.intValue()<=0){
            return APIResponse.fail("金额非法");
        }
        Account account=accountMapper.findByUserId(userId);
        if(account==null){
            return APIResponse.fail("未找到账户");
        }
        account.setMoney(account.getMoney()+amt);
        accountMapper.updateByPrimaryKey(account);
        return APIResponse.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public APIResponse sub(String userId,Integer amt) {
        if(amt.intValue()<=0){
            return APIResponse.fail("金额非法");
        }
        Account account=accountMapper.findByUserId(userId);
        if(account==null){
            return APIResponse.fail("未找到账户");
        }
        if(amt.compareTo(account.getMoney())>0){
            return APIResponse.fail("余额不足");
        }
        account.setMoney(account.getMoney()-amt);
        accountMapper.updateByPrimaryKey(account);
        return APIResponse.ok();

    }
}
