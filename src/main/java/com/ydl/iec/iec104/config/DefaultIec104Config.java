package com.ydl.iec.iec104.config;

import lombok.Data;

/**
 * 默认的配置
 */
@Data
public class DefaultIec104Config extends Iec104Config {

    public DefaultIec104Config() {
        setFrameSumMax((short) 1);
        setTerminnalAddress((short) 1);
    }
}
