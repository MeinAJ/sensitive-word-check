package com.geega.bsc.word.client.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class Sync {

    private final CountDownLatch sync;

    public Sync() {
        this.sync = new CountDownLatch(1);
    }

    public boolean await(long waitMs) {
        try {
            return this.sync.await(waitMs, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("等待异常", e);
        }
        return false;
    }

    public void countDown() {
        this.sync.countDown();
    }

}
