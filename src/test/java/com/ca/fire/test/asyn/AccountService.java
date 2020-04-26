package com.ca.fire.test.asyn;

import java.util.concurrent.CompletableFuture;

public interface AccountService {

    /**
     * 变更账户金额
     *
     * @param account 账户 ID
     * @param amount  增加的金额，负值为减少
     */

    CompletableFuture<Void> add(int account, int amount);
}
