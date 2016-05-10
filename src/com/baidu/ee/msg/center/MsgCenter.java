package com.baidu.ee.msg.center;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.factory.ServerFactory;
import com.baidu.ee.msg.server.IMsgServer;

/**
 *��Ϣ����<br>
 *������Ϣ����,ָ�ɸ���Ӧ����Ϣ������Server<br>
 *һ�߽����û�����Ϣ,һ�߿����߳�ָ����Ϣ����Ӧ�ķ���������<p>
 * @author Jord
 *
 */
public class MsgCenter {
	
	//��¼log
	private Logger log = Logger.getLogger("MsgCenter");
	
	private int sendNumPerMin = 20;
	private static MsgCenter singleton = null;
	private MsgCenter() {}
	
	private BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private Thread th = new Thread(new MsgCenterThread());
	/********************************************************/
	{
		//�ߴ��ȡ
		//���ô��̱߳����߳����ȼ���
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	/********************************************************/
	/**
	 * ������Ϣ����
	 * @param msg
	 * @throws InterruptedException
	 */
	public void send(IMsg msg) throws InterruptedException{
		queue.put(msg);
	}
	
	public void setSendNumPerMin(int sendNumPerMin){
		this.sendNumPerMin = sendNumPerMin;
	}
	/**
	 * ����
	 * @return
	 */
	public static MsgCenter getInstance(){
		if(singleton==null){
			synchronized(MsgCenter.class){
				if(singleton==null){
					singleton = new MsgCenter();
				}
			}
		}
		return singleton;
	}
	/**
	 * �ڲ���,������Ϣ����
	 * @author mozhuoda
	 *
	 */
	private class MsgCenterThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(200);//200ms�������ʱ��
					IMsg msg = queue.take();
					dispatch(msg);//����,ָ�ɸ���Ӧ����ϢServer
				} catch (InterruptedException e) {
					log.warning(e.getMessage());
				}
			}
		}
		/**
		 * ��Ϣ����<br>
		 * ������Ϣ��ָ�ɸ���Ӧ����ϢServer
		 * @param msg
		 * @throws InterruptedException 
		 */
		private void dispatch(IMsg msg) throws InterruptedException{
			String name = msg.getClass().getSimpleName();
			IMsgServer msgServer = ServerFactory.getInstance(name);
			msgServer.setSendNumPerMin(sendNumPerMin);//����ÿ����M��������
			msgServer.sendMsg(msg);		
		}
	}
}
