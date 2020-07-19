package com.ydl.iec.iec104.server;

import com.ydl.iec.iec104.server.slave.Iec104TcpServerSlave;

/**
 * 
 * @ClassName:  Iec104SlaveFactory   
 * @Description:  104从机工厂
 * @author: YDL
 * @date:   2020年5月19日 上午10:41:39
 */
public class Iec104SlaveFactory {

	/**
	 * 
	* @Title: createTcpServerSlave  
	* @Description:  生产一个 iec104 协议TCP传输方式服务端做从机服务
	* @param  port 端口 从机端口
	* @return Iec104Slave
	 */
	public static  Iec104Slave createTcpServerSlave(int port) {
		return new Iec104TcpServerSlave(port);
	}
}
