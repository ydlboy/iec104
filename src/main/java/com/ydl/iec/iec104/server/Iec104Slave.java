package com.ydl.iec.iec104.server;

import com.ydl.iec.iec104.config.Iec104Config;
import com.ydl.iec.iec104.server.handler.DataHandler;

/**
 * 从站抽象类
 */
public interface Iec104Slave {
	/**
	 * 
	* @Title: run
	* @Description: 启动主机
	* @throws Exception
	 */
	void run() throws Exception;
	
	
	/**
	 * 
	* @Title: setDataHandler
	* @Description: 设置数据处理类
	* @param dataHandler
	 */
	Iec104Slave setDataHandler(DataHandler dataHandler);


	/**
	 * 设置配置文件
	 * @param iec104Config
	 * @return
	 */
	Iec104Slave setConfig(Iec104Config iec104Config);
}
