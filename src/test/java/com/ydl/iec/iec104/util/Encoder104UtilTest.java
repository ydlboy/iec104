package com.ydl.iec.iec104.util;

import com.ydl.iec.util.Iec104Util;
import com.ydl.iec.iec104.core.Decoder104;
import com.ydl.iec.iec104.core.Encoder104;
import com.ydl.iec.iec104.message.MessageDetail;
import com.ydl.iec.iec104.message.MessageInfo;
import com.ydl.iec.iec104.enums.QualifiersEnum;
import com.ydl.iec.iec104.enums.TypeIdentifierEnum;
import com.ydl.iec.iec104.enums.UControlEnum;
import com.ydl.iec.util.ByteUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class Encoder104UtilTest {
	
	private MessageDetail originalObj;
	private String name;
	
	public Encoder104UtilTest(Object originalObj, String name) {
		this.originalObj = (MessageDetail) originalObj;
		this.name = name; 
	}
	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection primeNumbers() {
		return Arrays.asList(new Object[][] {
			{getGeneralCallRuleDetail104(), "总召唤指令"},
			{getEndGeneralCallRuleDetail104(), "结束总召唤指令"},
			{getYesGeneralCallRuleDetail104(), "总召唤确认指令"},
			{getInitRuleDetail104(), "初始化指令"},
			{getInitYesRuleDetail104(), "初始化确认指令"},
			{getInitEndRuleDetail104(), "初始化结束指令"},
			{getSRuleDetail104(), "S帧指令"},
		});
	}
	
	@Test
	public void testEncoder() throws IOException {
		byte[] bytes = Encoder104.encoder(originalObj);
		System.err.println(name + " 16:" + ByteUtil.byteArrayToHexString(bytes) +  " length " + bytes.length);
		MessageDetail oldObj = Decoder104.encoder(bytes);
		judge(originalObj, oldObj);
//		assertEquals(name + " 长度和预计十六进制输出不匹配", Util.byteArrayToHexString(bytes).length(), originalObj.getHexString().length());
		assertEquals(name + "和预计十六进制输出不匹配", ByteUtil.byteArrayToHexString(bytes), originalObj.getHexString());
	}
	
	
	/**
	 *  
	 *     类型标识 : 召唤命令
	 * @return
	 */
	private static MessageDetail getGeneralCallRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.generalCall;
		 //SQ=0 length =1
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 0;
		// 发送序号
		short send = 0;
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 6;
		// true：1 ; false ： 0
		boolean isTest = false;
		// true:0 false;1
		boolean isPN = true;
		
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		
		QualifiersEnum qualifiers = QualifiersEnum.generalCallQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPN, transferReason,
				terminalAddress, messageAddress, messages, null, null);
//    
//		68 0E 0000 0000  64(类型标识) 01(可变结构限定词)0600(传输原因)0100(公共地址)0000(信息体地址)0020
		ruleDetail104.setHexString("680E0000000064010600010000000020");
		return ruleDetail104;
	}
	
	
	private static MessageDetail getYesGeneralCallRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.generalCall; 
		 //SQ=0 length =1
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 0;
		// 发送序号
		short send = 0;
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 7;
		// true：1 ; false ： 0
		boolean isTest = false;
		// true:0 false;1
		boolean isPN = true;
		
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		
		QualifiersEnum qualifiers = QualifiersEnum.generalCallQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPN, transferReason,
				terminalAddress, messageAddress, messages, null, null);
//    
//		68 0E 0000 0000  64(类型标识) 01(可变结构限定词)0600(传输原因)0100(公共地址)0000(信息体地址)0020
		ruleDetail104.setHexString("680E0000000064010700010000000020");
		return ruleDetail104;
	}
	
	/**
	 * 	结束
	 * @return
	 */
	private static MessageDetail getEndGeneralCallRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.generalCall; 
		 //SQ=0 length =1
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 1;
		// 发送序号
		short send = 4;
//		int control = 0x08000200;
		
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 0x0A;
		// true：1 ; false ： 0
		boolean isTest = false;
		// true:0 false;1
		boolean isPN = true;
		
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		
		QualifiersEnum qualifiers = QualifiersEnum.generalCallQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPN, transferReason,
				terminalAddress, messageAddress, messages, null, null);
//      68 0E 0800 0200 64 01 0A00 0100 000000 20
//		68 0E 0800 0200 64 01 0A00 0100 000000 20
		ruleDetail104.setHexString("680E0800020064010A00010000000020");
		return ruleDetail104;
	}
	
	/**
	 * 初始化
	 * @return
	 */
	private static MessageDetail getInitRuleDetail104() {
		byte[] control = ByteUtil.intToByteArray(UControlEnum.STARTDT.getValue());
		MessageDetail ruleDetail104 = new MessageDetail(control);
		//6804 07(START命令) 00 0000
		ruleDetail104.setHexString("680407000000");
		return ruleDetail104;
	}
	
	/**
	 * 初始化确认
	 * @return
	 */
	private static MessageDetail getInitYesRuleDetail104() {
		byte[] control = ByteUtil.intToByteArray((UControlEnum.STARTDT_YES.getValue()));
		MessageDetail ruleDetail104 = new MessageDetail(control);
		//6804 07(START命令) 00 0000
		ruleDetail104.setHexString("68040B000000");
		return ruleDetail104;
	}
	
	/**
	 * 初始化完成
	 * @return
	 */
	private static MessageDetail getInitEndRuleDetail104() {
		TypeIdentifierEnum typeIdentifierEnum = TypeIdentifierEnum.initEnd; 
		 //SQ=0 length =1
		int sq = 0;
		boolean isContinuous = sq == 0 ? false : true;
		// 接收序号
		short accept = 0;
		// 发送序号
		short send = 0;
		byte[] control = Iec104Util.getIcontrol(accept, send);
		// 传输原因
		short transferReason = 4;
		// true：1 ; false ： 0
		boolean isTest = false;
		// true:0 false;1
		boolean isPN = true;
		
		short terminalAddress = 1;
		// 消息地址 总召唤地址为0
		int messageAddress = 0;
		
		QualifiersEnum qualifiers = QualifiersEnum.localMmanualResetQualifiers;
		List<MessageInfo> messages = new ArrayList<>();
		MessageInfo message = new MessageInfo();
		message.setQualifiers(qualifiers);
		message.setMessageInfos(new byte[] {});
		
		messages.add(message);
		MessageDetail ruleDetail104 = new MessageDetail(control, typeIdentifierEnum, isContinuous, isTest, isPN, transferReason,
				terminalAddress, messageAddress, messages, null, null);
//		68 0E 0000 0000  64(类型标识) 01(可变结构限定词)0600(传输原因)0100(公共地址)0000(信息体地址)0020
		ruleDetail104.setHexString("680E0000000046010400010000000001");
		return ruleDetail104;
	}
	
	/**
	 * S 帧
	 * @return
	 */
	private static MessageDetail getSRuleDetail104() {
		short accept = 1; // 01 00 02 00
//  	接收序号是1
//		byte[] control = ByteUtil.intToByteArray(0x1000200);
		byte[] control = Iec104Util.getScontrol(accept);
		MessageDetail ruleDetail104 = new MessageDetail(control);
		//6804 0100() 0200
		ruleDetail104.setHexString("680401000200");
		return ruleDetail104;
	} 
	
	
	
	
	
	public void judge(MessageDetail originalObj, MessageDetail oldObj) {
		assertEquals(name + ": start :", originalObj.getStart(), oldObj.getStart());
		assertEquals(name + ": apuuLength :", originalObj.getApuuLength(), oldObj.getApuuLength());
		
		int index = 0;
		while (index < originalObj.getControl().length) {
			assertEquals(name + ": control :", originalObj.getControl()[index], oldObj.getControl()[index++]);
		}
		assertEquals(name + ": typeIdentifier :", originalObj.getTypeIdentifier(), oldObj.getTypeIdentifier());
		assertEquals(name + ": isContinuous :", originalObj.isContinuous(), oldObj.isContinuous());
		assertEquals(name + ": measgLength :", originalObj.getMeasgLength(), oldObj.getMeasgLength());
		assertEquals(name + ": transferReason :", originalObj.getTransferReason(), oldObj.getTransferReason());
		assertEquals(name + ": terminalAddress :", originalObj.getTerminalAddress(), oldObj.getTerminalAddress());
		assertEquals(name + ": messageAddress :", originalObj.getMessageAddress(), oldObj.getMessageAddress());
		assertEquals(name + ": qualifiers :", originalObj.getQualifiers(), oldObj.getQualifiers());
		assertEquals(name + ": timeScale :", originalObj.getTimeScale(), oldObj.getTimeScale());
		assertEquals(name + ": messageAddress :", originalObj.getMessageAddress(), oldObj.getMessageAddress());
		assertEquals(name + ": messageAddress :", originalObj.getMessageAddress(), oldObj.getMessageAddress());
		assertEquals(name + ": messageAddress :", originalObj.getMessageAddress(), oldObj.getMessageAddress());
		judgeMessages(originalObj, oldObj);
		System.err.println(name + " 指令测试结束");
	}

	private void judgeMessages(MessageDetail originalObj, MessageDetail oldObj) {
		assertEquals(name + ": messagesSize :", originalObj.getMessages().size(), oldObj.getMessages().size());
		int index = 0;
		while (index < originalObj.getMessages().size()) {
			MessageInfo originalMessagesObj = originalObj.getMessages().get(index);
			MessageInfo oldMessageObj = oldObj.getMessages().get(index);
			
			assertEquals(name + ": Messages timeScale :", originalMessagesObj.getTimeScale(), oldMessageObj.getTimeScale());
			Assert.assertEquals(name + ": Messages qualifiers :", originalMessagesObj.getQualifiers(), oldMessageObj.getQualifiers());
			assertEquals(name + ": Messages timeScale :", originalMessagesObj.getMessageInfos().length, oldMessageObj.getMessageInfos().length);
			int messageInfoIndex = 0;
			while (messageInfoIndex < originalMessagesObj.getMessageInfos().length) {
				assertEquals(name + ": Messages messageInfos :", originalMessagesObj.getMessageInfos()[messageInfoIndex], oldMessageObj.getMessageInfos()[messageInfoIndex]);
				messageInfoIndex++;
			}
			index++;
		}
	}
}
