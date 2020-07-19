package com.ydl.iec.iec104.server.slave;

import com.ydl.iec.iec104.config.DefaultIec104Config;
import com.ydl.iec.iec104.config.Iec104Config;
import com.ydl.iec.iec104.core.Iec104ThreadLocal;
import com.ydl.iec.iec104.server.Iec104Slave;
import com.ydl.iec.iec104.server.handler.*;
import com.ydl.iec.iec104.server.slave.handler.Iec104TcpSlaveHandler;
import com.ydl.iec.iec104.server.slave.handler.SysUframeServerHandler;
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
public class Iec104ServerInitializer extends ChannelInitializer<SocketChannel> {

	private DataHandler dataHandler;


	private Iec104Config iec104Config;

	/**
	 * 初始化处理链
	 */
	@Override
	public void initChannel(SocketChannel ch) {
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
		// byte[] 编码器
		pipeline.addLast("bytesEncoder", new BytesEncoder());
		
		//编码器 将对象编码成 字节码
		pipeline.addLast("encoder", new DataEncoder());
		
		//拦截 U帧处理器 
		pipeline.addLast("uframe", new SysUframeServerHandler());
		//拦截 S帧处理器 
		pipeline.addLast("sframe", new SysSframeHandler());
		//解码器
		pipeline.addLast("decoder", new DataDecoder());
		// 具体的处理器
		pipeline.addLast("handler", new Iec104TcpSlaveHandler(dataHandler));
	}
}
