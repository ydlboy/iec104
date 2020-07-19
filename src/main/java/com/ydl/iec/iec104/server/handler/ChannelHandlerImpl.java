package com.ydl.iec.iec104.server.handler;

import com.ydl.iec.iec104.message.MessageDetail;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @ClassName:  ChannelHandlerImpl   
 * @Description: 实现一个自定义发现消息的类
 * @author: YDL
 * @date:   2020年5月19日 上午11:47:16
 */
public class ChannelHandlerImpl implements  ChannelHandler {
	
	private ChannelHandlerContext ctx;
	
	public ChannelHandlerImpl(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void writeAndFlush(MessageDetail ruleDetail104) {
		ctx.channel().writeAndFlush(ruleDetail104);
	}

}
