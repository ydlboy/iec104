package com.ydl.iec.iec104.config;

import lombok.Data;

/**
 * 104规约的配置
 */
@Data
public class Iec104Config {

    /**
     * 接收到帧的数量到该值就要发一个确认帧
     */
    private short frameSumMax;


    /**
     * 终端地址
     */
    private short terminnalAddress;
}
