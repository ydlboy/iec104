package com.ydl.iec.iec104.server.handler;

import com.ydl.iec.iec104.message.MessageDetail;

/**
 * 
 * @ClassName:  DataHandler   
 * @Description:  数据处理
 * @author: YDL
 * @date:   2020年5月19日 上午11:27:04
 */
public interface DataHandler {

	/**
	 * 
	* @Title: handlerAdded
	* @Description: 建立连接
	* @param ctx
	* @throws Exception
	 */
	void handlerAdded(ChannelHandler ctx) throws Exception;
	
	/**
	 * 
	* @Title: channelRead0
	* @Description: 收到消息
	* @param ctx
	* @param ruleDetail104
	* @throws Exception
	 */
	void channelRead(ChannelHandler ctx, MessageDetail ruleDetail104) throws Exception;
}
