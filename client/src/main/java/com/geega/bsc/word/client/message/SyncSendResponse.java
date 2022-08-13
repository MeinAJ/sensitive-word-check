package com.geega.bsc.word.client.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Jun.An3
 * @date 2022/08/13
 */
@Data
public class SyncSendResponse implements Serializable {

    private byte[] body;

}
