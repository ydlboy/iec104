package com.ydl.iec.iec104.server.slave;


import com.ydl.iec.iec104.config.Iec104Config;
import com.ydl.iec.iec104.server.Iec104SlaveFactory;
import org.junit.Test;

/**
 * 
* @ClassName: Iec104TcpServerSlaveTest  
* @Description: 测试 iec104 协议TCP传输方式服务端做从机服务
* @author YDL 
* @date 2020年5月13日
 */
public class Iec104TcpServerSlaveTest {

	/**
	 * 
	* @Title: test   
	* @Description: 测试 iec104 协议TCP传输方式服务端做从机服务
	* @param @throws Exception 
	* @return void   
	* @throws
	 */
	@Test
	public void test() throws Exception {
		Iec104Config iec104Config  = new Iec104Config();
		iec104Config.setFrameSumMax((short) 1);
		iec104Config.setTerminnalAddress((short) 1);
		Iec104SlaveFactory.createTcpServerSlave(2404).setDataHandler(new SysDataHandler()).setConfig(iec104Config).run();
//        Thread.sleep(1000000);
	}

}
