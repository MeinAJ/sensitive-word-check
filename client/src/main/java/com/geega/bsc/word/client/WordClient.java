package com.geega.bsc.word.client;

import com.alibaba.fastjson2.JSON;
import com.geega.bsc.word.client.message.SyncSendRequest;
import com.geega.bsc.word.client.message.SyncSendResponse;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class WordClient {

    private NetworkClient networkClient;

    public WordClient() {
        this.networkClient = new NetworkClient("127.0.0.1", 8888);
    }

    public Set<String> check(String context) {
        SyncSendRequest syncSendRequest = new SyncSendRequest(context);
        SyncSendResponse syncSendResponse = networkClient.check(syncSendRequest);
        byte[] body = syncSendResponse.getBody();
        if (body != null) {
            String resultString = new String(body, StandardCharsets.UTF_8);
            return new HashSet<>(JSON.parseArray(resultString, String.class));
        }
        return null;
    }
}
