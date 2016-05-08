package com.baidu.ee.msg;

import com.baidu.ee.msg.vo.MsgVo;

/**
 * 消息体父类
 * @author Jord
 *
 */
public abstract class BaseMsg {
	/**
	 * 消息发信人
	 */
	private String from;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 消息收信人
	 */
	private String to;
	
	/**
	 * 消息类型
	 */
	protected String msgType;
	
	
	/**
	 * 构造函数
	 */
	public BaseMsg(MsgVo vo) {
		this.setFrom(vo.getFrom());
		this.setTo(vo.getTo());
		this.setContent(vo.getContent());
	}
	//默认构造函数
	public BaseMsg(){}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}


	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	/**
	 * 抽象发消息方法
	 * @return
	 */
	public abstract boolean sendMsg();
	@Override
	public String toString() {
		return "消息类型:" + msgType + "\n 发信人:"+from+ "\n收信人: " + to
				+ "\n内容:" + content + "\n";
	}
}
