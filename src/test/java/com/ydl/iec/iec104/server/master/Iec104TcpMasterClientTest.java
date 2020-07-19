package com.ydl.iec.iec104.server.master;


import com.ydl.iec.iec104.config.Iec104Config;
import com.ydl.iec.iec104.server.Iec104MasterFactory;
import com.ydl.iec.iec104.server.slave.SysDataHandler;
import org.junit.Test;

public class Iec104TcpMasterClientTest {

	@Test
	public void test() throws  Exception {
		Iec104Config iec104Config  = new Iec104Config();
		iec104Config.setFrameSumMax((short) 1);
		iec104Config.setTerminnalAddress((short) 1);
		Iec104MasterFactory.createTcpClientMaster("127.0.0.1", 2404).setDataHandler(new SysDataHandler()).setConfig(iec104Config).run();
        Thread.sleep(1000000);
	}

}
