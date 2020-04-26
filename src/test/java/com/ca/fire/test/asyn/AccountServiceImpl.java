package com.ca.fire.test.asyn;

import java.util.concurrent.CompletableFuture;

public class AccountServiceImpl implements AccountService {
    @Override
    public CompletableFuture<Void> add(int account, int amount) {
        System.out.println("account" + account);
        System.out.println("account" + (account + amount));
        return null;
    }
}
