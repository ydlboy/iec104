package com.ydl.iec.iec104.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * 数组编码器
 * @author Admin
 *
 */
public class BytesEncoder extends MessageToByteEncoder<byte[]> {

	
	@Override
	protected void encode(ChannelHandlerContext ctx, byte[] msg, ByteBuf out) throws Exception {
		out.writeBytes(msg);
	}

}
