package com.baidu.ee.msg.vo;

/**
 * ��Ϣvo
 * @author Jord
 *
 */
public class MsgVo {

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
	 * ���캯��
	 * @param from
	 * @param content
	 * @param to
	 */
	public MsgVo(String from, String content, String to) {
		super();
		this.from = from;
		this.content = content;
		this.to = to;
	}
	/**
	 * Ĭ�Ϲ��캯��
	 */
	public MsgVo(){}
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
	
}
