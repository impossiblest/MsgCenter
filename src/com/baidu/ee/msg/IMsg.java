package com.baidu.ee.msg;

/**
 * ��Ϣ�ӿ�
 * @author Jord
 *
 */
public abstract interface IMsg {
	/**
	 * ������Ϣ�ӿ�
	 */
	public abstract boolean sendMsg();
	/**
	 * ������Ϣ����
	 * @return
	 */
	public abstract String getMsgType();
}
