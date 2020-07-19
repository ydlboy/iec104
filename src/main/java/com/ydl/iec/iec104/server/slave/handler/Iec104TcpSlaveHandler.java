package com.ydl.iec.iec104.server.slave.handler;


import com.ydl.iec.iec104.core.CachedThreadPool;
import com.ydl.iec.iec104.core.ControlManageUtil;
import com.ydl.iec.iec104.core.Iec104ThreadLocal;
import com.ydl.iec.iec104.message.MessageDetail;
import com.ydl.iec.iec104.server.handler.ChannelHandlerImpl;
import com.ydl.iec.iec104.server.handler.DataHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * 
* @ClassName: Iec104TcpSlaveHandler  
* @Description: 从机处理类 
* @author YDL 
* @date 2020年5月13日
 */
public class Iec104TcpSlaveHandler extends SimpleChannelInboundHandler<MessageDetail> {
	
	private DataHandler dataHandler;
	

	public Iec104TcpSlaveHandler(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

    /**
     * 新连接
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Iec104ThreadLocal.setControlPool(new ControlManageUtil(ctx).setFrameAmountMax(Iec104ThreadLocal.getIec104Conig().getFrameSumMax()));
        /**
         * 启动
         */
        Iec104ThreadLocal.getControlPool().startSendFrameTask();
        if (dataHandler != null) {
            Runnable runnable = () -> {
                try {
                    dataHandler.handlerAdded(new ChannelHandlerImpl(ctx));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            CachedThreadPool.getCachedThreadPool().execute(runnable);
        }
    }

    /**
     * 断开连接
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    }
    
    /**
     * 收到消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageDetail ruleDetail104) throws Exception {
    	if (dataHandler != null) {
            Runnable runnable = () -> {
                try {
                    dataHandler.channelRead(new ChannelHandlerImpl(ctx), ruleDetail104);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            CachedThreadPool.getCachedThreadPool().execute(runnable);
    	}
    }

    /**
     * 连接
     * 
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { 
        Channel incoming = ctx.channel();
        System.err.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }
    
    /**
     * 关闭
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { 
        Channel incoming = ctx.channel();
        System.err.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
        Channel incoming = ctx.channel();
        System.err.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
