package com.geega.bsc.word.client.message;

import com.geega.bsc.word.client.sync.Sync;
import lombok.Builder;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 数据+客户端唯一ID
 *
 * @author Jun.An3
 * @date 2022/08/13
 */
@Builder
public class SyncSendRequest extends Sync implements Serializable {

    private ByteBuffer body;

    public SyncSendRequest(String context) {
        this.body = processBody(context);
    }

    private ByteBuffer processBody(String context) {
        byte[] id = UUID.randomUUID().toString().replaceAll("-", "").getBytes(StandardCharsets.UTF_8);
        byte[] body = context.getBytes(StandardCharsets.UTF_8);
        int capacity = 4 + id.length + body.length;
        ByteBuffer requestBody = ByteBuffer.allocate(capacity);
        requestBody.putInt(capacity);
        requestBody.put(id);
        requestBody.put(body);
        return requestBody;
    }
}
