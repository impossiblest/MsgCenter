package com.baidu.ee.msg.server;

import com.baidu.ee.msg.IMsg;
/**
 * ��Ϣ�������ӿ�
 * @author Jord
 *
 */
public interface IMsgServer {
	/**
	 * ������������Ϣ�ӿ�<br>
	 * �ڴ��Զ�����Ϣ������Ϣ����Queue����
	 * @param msg
	 * @return
	 * @throws InterruptedException
	 */
	public boolean sendMsg(IMsg msg) throws InterruptedException;
	
	/**
	 * ����ÿ���Ӵ�����Ϣ����,Ĭ��Ϊ30/min
	 * @param m
	 */
	public void setM(int m);
}
