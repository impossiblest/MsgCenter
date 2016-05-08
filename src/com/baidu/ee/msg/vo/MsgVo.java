package com.baidu.ee.msg.vo;

/**
 * 消息vo
 * @author Jord
 *
 */
public class MsgVo {

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
	 * 构造函数
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
	 * 默认构造函数
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
