package com.ydl.iec.iec104.server.handler;

import com.ydl.iec.iec104.core.Iec104ThreadLocal;
import com.ydl.iec.util.Iec104Util;
import com.ydl.iec.iec104.core.Decoder104;
import com.ydl.iec.iec104.message.MessageDetail;
import com.ydl.iec.util.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;


/**
 * 解码器
 * @author Admin
 *
 */
public class DataDecoder extends ByteToMessageDecoder {
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] data = new byte[in.readableBytes()];
		in.readBytes(data);
		short send = Iec104Util.getSend(ByteUtil.getByte(data, 2, 4));
		Iec104ThreadLocal.getControlPool().setAccept(send);
		MessageDetail ruleDetail104 = Decoder104.encoder(data);
		out.add(ruleDetail104);
	}
}
