package com.doger.nacosaccount.service;

import com.doger.nacosaccount.dto.APIResponse;

public interface AccountService {

    APIResponse add(String userId,Integer amt);

    APIResponse sub(String userId,Integer amt);
}
