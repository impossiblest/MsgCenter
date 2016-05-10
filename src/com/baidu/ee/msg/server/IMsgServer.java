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
	 * �ڴ��Զ�����Ϣ������Ϣ����Queue����<br>
	 * �Լ������߳�,������Ϣ��֤����Ҫ�󡢷��Ͳ���
	 * @param msg
	 * @return
	 * @throws InterruptedException
	 */
	public boolean sendMsg(IMsg msg) throws InterruptedException;
	
	/**
	 * ����ÿ���Ӵ�����Ϣ����,Ĭ��Ϊ20/min
	 * @param sendNumPerMin
	 */
	public void setSendNumPerMin(int sendNumPerMin);
}
