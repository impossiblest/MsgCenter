package com.baidu.ee.msg;

/**
 * 消息接口
 * @author Jord
 *
 */
public abstract interface IMsg {
	/**
	 * 发送消息接口
	 */
	public abstract boolean sendMsg();
	/**
	 * 返回消息类型
	 * @return
	 */
	public abstract String getMsgType();
}
