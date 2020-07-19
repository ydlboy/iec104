package com.ydl.iec.iec104.server.master;


import com.ydl.iec.iec104.config.DefaultIec104Config;
import com.ydl.iec.iec104.config.Iec104Config;
import com.ydl.iec.iec104.core.Iec104ThreadLocal;
import com.ydl.iec.iec104.server.Iec104Master;
import com.ydl.iec.iec104.server.handler.DataHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 
 * @ClassName:  Iec104TcpClientMaster   
 * @Description: 104 TCP 客户端 主机
 * @author: YDL
 * @date:   2020年5月19日 上午10:44:35
 */
public class Iec104TcpClientMaster implements Iec104Master {

	private int port;
	private String host;
	private DataHandler dataHandler;
	private Iec104Config iec104Config;

	public Iec104TcpClientMaster(String host, int port) {
		this.port = port;
		this.host = host;
	}

	@Override
	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
	    Bootstrap bs = new Bootstrap();
	    bs.group(bossGroup)
	      .channel(NioSocketChannel.class)
	      .option(ChannelOption.SO_KEEPALIVE, true)
	      .handler(new Iec104ClientInitializer().setDataHandler(dataHandler).setIec104Config(iec104Config));
	    // 客户端开启
	    Channel channel  = bs.connect(host, port).sync().channel();
	    channel.closeFuture().sync();
	}

	@Override
	public Iec104Master setDataHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
		return this;
	}

	@Override
	public Iec104Master setConfig(Iec104Config iec104Config) {
		this.iec104Config = iec104Config;
		return this;
	}
}
