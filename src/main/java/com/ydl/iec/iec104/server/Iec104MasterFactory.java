package com.ydl.iec.iec104.server;

import com.ydl.iec.iec104.server.master.Iec104TcpClientMaster;

/**
 * 主站 工厂类
 * @ClassName:  Iec104MasterFactory   
 * @Description: IEC104规约主站
 * @author: YDL
 * @date:   2020年5月19日 上午10:22:59
 */
public class Iec104MasterFactory {

 

	/**
	* @Title: createTcpClientMaster
	* @Description: 创建一个TCM客户端的104主站
	* @param host 从机地址
	* @param port 端口
	* @return
	 */
	public static  Iec104Master createTcpClientMaster(String host, int port) {
		return new Iec104TcpClientMaster(host, port);
	}
}
