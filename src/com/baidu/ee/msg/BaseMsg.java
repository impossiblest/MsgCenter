package com.baidu.ee.msg;

import com.baidu.ee.msg.vo.MsgVo;

/**
 * ��Ϣ�常��
 * @author Jord
 *
 */
public abstract class BaseMsg {
	/**
	 * ��Ϣ������
	 */
	private String from;
	/**
	 * ��Ϣ����
	 */
	private String content;
	/**
	 * ��Ϣ������
	 */
	private String to;
	
	/**
	 * ��Ϣ����
	 */
	protected String msgType;
	
	
	/**
	 * ���캯��
	 */
	public BaseMsg(MsgVo vo) {
		this.setFrom(vo.getFrom());
		this.setTo(vo.getTo());
		this.setContent(vo.getContent());
	}
	//Ĭ�Ϲ��캯��
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
	 * ������Ϣ����
	 * @return
	 */
	public abstract boolean sendMsg();
	@Override
	public String toString() {
		return "��Ϣ����:" + msgType + "\n ������:"+from+ "\n������: " + to
				+ "\n����:" + content + "\n";
	}
}
