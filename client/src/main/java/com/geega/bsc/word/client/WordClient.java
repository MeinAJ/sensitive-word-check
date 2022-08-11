package com.geega.bsc.word.client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WordClient {

    private NetworkClient networkClient;

    public WordClient() {
        this.networkClient = new NetworkClient("127.0.0.1", 8888);
    }

}
