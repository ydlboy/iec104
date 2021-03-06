package com.ydl.iec.iec104.server.master;

import com.ydl.iec.iec104.config.DefaultIec104Config;
import com.ydl.iec.iec104.config.Iec104Config;
import com.ydl.iec.iec104.core.Iec104ThreadLocal;
import com.ydl.iec.iec104.server.handler.*;
import com.ydl.iec.iec104.server.master.handler.Iec104ClientHandler;
import com.ydl.iec.iec104.server.master.handler.SysUframeClientHandler;
import com.ydl.iec.iec104.server.slave.Iec104ServerInitializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 
* @ClassName: Iec104ServerInitializer  
* @Description: 104协议 处理链 
* @author YDL 
* @date 2020年5月13日
 */
@Setter
@Accessors(chain = true)
public class Iec104ClientInitializer extends ChannelInitializer<SocketChannel> {


	private DataHandler dataHandler;

	private Iec104Config iec104Config;
	
	/**
	 * 初始化处理链
	 */
	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		if (iec104Config == null) {
			Iec104ThreadLocal.setIec104Config(iec104Config);
		} else {
			Iec104ThreadLocal.setIec104Config(new DefaultIec104Config());
		}
		ChannelPipeline pipeline = ch.pipeline();
		// 沾包拆包工具
		pipeline.addLast("unpack", new Unpack104Handler());
		// 数据检查工具
		pipeline.addLast("check", new Check104Handler());
//		/拦截 U帧处理器 
		pipeline.addLast("uframe", new SysUframeClientHandler());
		//拦截 S帧处理器 
		pipeline.addLast("sframe", new SysSframeHandler());
		//编码器
		pipeline.addLast("byteencoder", new BytesEncoder());
		//编码器
		pipeline.addLast("encoder", new DataEncoder());

//		 解码器
		pipeline.addLast("decoder", new DataDecoder());
		pipeline.addLast("handler", new Iec104ClientHandler(dataHandler));
	}
}
