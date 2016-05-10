package com.baidu.ee.msg.center;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.factory.ServerFactory;
import com.baidu.ee.msg.server.IMsgServer;

/**
 *消息中心<br>
 *根据消息类型,指派给相应的消息服务器Server<br>
 *一边接收用户的消息,一边开启线程指派消息给相应的服务器处理<p>
 * @author Jord
 *
 */
public class MsgCenter {
	
	//记录log
	private Logger log = Logger.getLogger("MsgCenter");
	
	private int sendNumPerMin = 20;
	private static MsgCenter singleton = null;
	private MsgCenter() {}
	
	private BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private Thread th = new Thread(new MsgCenterThread());
	/********************************************************/
	{
		//边存边取
		//设置次线程比主线程优先级低
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	/********************************************************/
	/**
	 * 接收消息队列
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
	 * 单例
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
	 * 内部类,处理消息调度
	 * @author mozhuoda
	 *
	 */
	private class MsgCenterThread implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(200);//200ms处理调度时间
					IMsg msg = queue.take();
					dispatch(msg);//调度,指派给相应的消息Server
				} catch (InterruptedException e) {
					log.warning(e.getMessage());
				}
			}
		}
		/**
		 * 消息调度<br>
		 * 解析信息，指派给相应的消息Server
		 * @param msg
		 * @throws InterruptedException 
		 */
		private void dispatch(IMsg msg) throws InterruptedException{
			String name = msg.getClass().getSimpleName();
			IMsgServer msgServer = ServerFactory.getInstance(name);
			msgServer.setSendNumPerMin(sendNumPerMin);//设置每分钟M条处理量
			msgServer.sendMsg(msg);		
		}
	}
}
